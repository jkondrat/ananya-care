package org.motechproject.care.service.schedule.listener;

import org.apache.log4j.Logger;
import org.motechproject.care.domain.CareCaseTask;
import org.motechproject.care.domain.Client;
import org.motechproject.care.repository.AllCareCaseTasks;
import org.motechproject.care.repository.AllMothers;
import org.motechproject.care.service.util.TaskIdMapper;
import org.motechproject.casexml.gateway.CommcareCaseGateway;
import org.motechproject.model.MotechEvent;
import org.motechproject.scheduletracking.api.domain.MilestoneAlert;
import org.motechproject.scheduletracking.api.events.MilestoneEvent;
import org.motechproject.scheduletracking.api.events.constants.EventSubjects;
import org.motechproject.server.event.annotations.MotechListener;
import org.motechproject.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CareAlertServiceListener {

    private CommcareCaseGateway commcareCaseGateway;
    private AllCareCaseTasks allCareCaseTasks;
    private AllMothers allMothers;

    Logger logger = Logger.getLogger(CareAlertServiceListener.class);
    private static TaskIdMapper taskIdMapper = new TaskIdMapper();

    @Autowired
    public CareAlertServiceListener(CommcareCaseGateway commcareCaseGateway, AllMothers motherRepository, AllCareCaseTasks allCareCaseTasks) {
        this.commcareCaseGateway = commcareCaseGateway;
        this.allMothers = motherRepository;
        this.allCareCaseTasks = allCareCaseTasks;
    }

    @MotechListener(subjects = {EventSubjects.MILESTONE_ALERT})
    public void handleEvent(MotechEvent event){
        MilestoneEvent msEvent = new MilestoneEvent(event);
        String externalId = msEvent.getExternalId();
        MilestoneAlert milestoneAlert = msEvent.getMilestoneAlert();
        Client client = allMothers.findByCaseId(externalId);

        CareCaseTask careCasetask = createCasetask(externalId, milestoneAlert.getMilestoneName(), milestoneAlert.getDueDateTime().toString("yyyy-MM-dd"), milestoneAlert.getLateDateTime().toString("yyyy-MM-dd"), client.getGroupId(),client.getFlwId(), client.getCaseType());
        allCareCaseTasks.add(careCasetask);
        commcareCaseGateway.submitCase(careCasetask.toCaseTask());

    }


    private CareCaseTask createCasetask(String caseId, String caseName, String dateEligible, String dateExpires, String ownerId,String userId, String caseType) {
        String taskId = taskIdMapper.get(caseName);
        String currentTime = DateUtil.now().toString();
        return new CareCaseTask(caseName, ownerId, caseId, userId, currentTime, taskId, dateEligible, dateExpires, caseType, caseId);
    }
}
