package org.motechproject.care.service;

import org.apache.log4j.Logger;
import org.motechproject.care.domain.CareCaseTask;
import org.motechproject.care.repository.AllCareCaseTasks;
import org.motechproject.casexml.gateway.CommcareCaseGateway;
import org.motechproject.commons.date.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;

@Service
public class CareCaseTaskService {

    private AllCareCaseTasks allCareCaseTasks;
    private CommcareCaseGateway commcareCaseGateway;
    private Properties ananyaCareProperties;

    Logger logger = Logger.getLogger(CareCaseTaskService.class);

    @Autowired
    public CareCaseTaskService(AllCareCaseTasks allCareCaseTasks, CommcareCaseGateway commcareCaseGateway, Properties ananyaCareProperties) {
        this.allCareCaseTasks = allCareCaseTasks;
        this.commcareCaseGateway = commcareCaseGateway;
        this.ananyaCareProperties = ananyaCareProperties;
    }

    public void close(String clientCaseId, String milestoneName) {
        logger.info(String.format("Closing case for Client Case Id: %s; Milestone Name: %s", clientCaseId, milestoneName));
        CareCaseTask careCaseTask = allCareCaseTasks.findByClientCaseIdAndMilestoneName(clientCaseId, milestoneName);
        if(careCaseTask == null|| !careCaseTask.getOpen()) {
            logger.info(String.format("Valid care case not found for Client Case Id: %s; Milestone Name: %s", clientCaseId, milestoneName));
            return;
        }
        logger.info(String.format("Sending close case to Commcare for Client Case Id: %s; Milestone Name: %s", clientCaseId, milestoneName));
        careCaseTask.setOpen(false);
        careCaseTask.setCurrentTime(DateUtil.now().toString());
        allCareCaseTasks.update(careCaseTask);
        String commcareUrl = ananyaCareProperties.getProperty("commcare.hq.url");
        String commcareUsername = ananyaCareProperties.getProperty("commcare.hq.username");
        String commcarePassword = ananyaCareProperties.getProperty("commcare.hq.password");
        commcareCaseGateway.closeCase(commcareUrl, careCaseTask.toCaseTask(), commcareUsername, commcarePassword);
    }

    public void closeAll(String clientCaseId, String milestoneName) {
        logger.info(String.format("Closing all cases for Client Case Id: %s; Milestone Name: %s", clientCaseId, milestoneName));
        List<CareCaseTask> careCaseTasks = allCareCaseTasks.findTasksByClientCaseIdAndMilestoneName(clientCaseId, milestoneName);
        String commcareUrl = ananyaCareProperties.getProperty("commcare.hq.url");
        String commcareUsername = ananyaCareProperties.getProperty("commcare.hq.username");
        String commcarePassword = ananyaCareProperties.getProperty("commcare.hq.password");
        for(CareCaseTask task : careCaseTasks) {
            if (task != null && task.getOpen()) {
                logger.info(String.format("Sending close case to Commcare for Task Id: %s; Milestone Name: %s", task.getId(), milestoneName));
                task.setOpen(false);
                task.setCurrentTime(DateUtil.now().toString());
                allCareCaseTasks.update(task);
                commcareCaseGateway.closeCase(commcareUrl, task.toCaseTask(), commcareUsername, commcarePassword);
            }
        }
    }
}
