package org.motechproject.care.integration.schedule;

import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.care.repository.AllChildren;
import org.motechproject.care.request.CareCase;
import org.motechproject.care.schedule.service.MilestoneType;
import org.motechproject.care.schedule.vaccinations.ExpirySchedule;
import org.motechproject.care.service.ChildService;
import org.motechproject.care.service.ChildVaccinationProcessor;
import org.motechproject.care.service.builder.ChildCareCaseBuilder;
import org.motechproject.care.service.schedule.ChildCareService;
import org.motechproject.care.service.schedule.VaccinationService;
import org.motechproject.care.utils.CaseUtils;
import org.motechproject.care.utils.SpringIntegrationTest;
import org.motechproject.scheduletracking.api.domain.EnrollmentStatus;
import org.motechproject.scheduletracking.api.service.EnrollmentRecord;
import org.motechproject.scheduletracking.api.service.EnrollmentsQuery;
import org.motechproject.scheduletracking.api.service.ScheduleTrackingService;
import org.motechproject.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class ChildCareIntegrationTest extends SpringIntegrationTest {

    @Autowired
    private ChildCareService childCareService;
    @Autowired
    private ScheduleTrackingService scheduleTrackingService;
    @Autowired

    private AllChildren allChildren;
    private String caseId;
    private ChildService childService;

    @After
    public void tearDown() {
        allChildren.removeAll();
    }

    @Before
    public void setUp(){
        caseId = CaseUtils.getUniqueCaseId();
        List<VaccinationService> ttServices = Arrays.asList((VaccinationService) childCareService);
        ChildVaccinationProcessor childVaccinationProcessor = new ChildVaccinationProcessor(ttServices);
        childService = new ChildService(allChildren, childVaccinationProcessor);
    }

    @Test
    public void shouldVerifyChildCareScheduleCreationWhenChildIsRegistered() {
        String childCareScheduleName = ExpirySchedule.ChildCare.getName();
        LocalDate dob = DateUtil.today().plusMonths(4);

        CareCase careCase=new ChildCareCaseBuilder().withCaseId(caseId).withDOB(dob.toString()).build();
        childService.process(careCase);
        markScheduleForUnEnrollment(caseId, childCareScheduleName);
        EnrollmentsQuery query = new EnrollmentsQuery()
                .havingExternalId(caseId)
                .havingState(EnrollmentStatus.ACTIVE)
                .havingSchedule(childCareScheduleName);

        EnrollmentRecord enrollment = scheduleTrackingService.searchWithWindowDates(query).get(0);

        assertEquals(MilestoneType.ChildCare.toString(), enrollment.getCurrentMilestoneName());
        assertEquals(DateUtil.newDateTime(dob), enrollment.getReferenceDateTime().withTimeAtStartOfDay());
        assertEquals(DateUtil.newDateTime(dob), enrollment.getStartOfDueWindow().withTimeAtStartOfDay());
        assertEquals(DateUtil.newDateTime(dob.plusMonths(24)), enrollment.getStartOfLateWindow().withTimeAtStartOfDay());
    }
}
