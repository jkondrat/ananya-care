package org.motechproject.care.service.router.action;

import junit.framework.Assert;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.motechproject.care.domain.CareCaseTask;
import org.motechproject.care.domain.Child;
import org.motechproject.care.repository.AllCareCaseTasks;
import org.motechproject.care.repository.AllChildren;
import org.motechproject.care.request.CaseType;
import org.motechproject.casexml.domain.CaseTask;
import org.motechproject.casexml.gateway.CommcareCaseGateway;
import org.motechproject.scheduletracking.api.domain.Milestone;
import org.motechproject.scheduletracking.api.domain.MilestoneAlert;
import org.motechproject.scheduletracking.api.events.MilestoneEvent;
import org.motechproject.commons.date.util.DateUtil;

import java.util.Properties;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AlertChildActionTest {

    @Mock
    private CommcareCaseGateway commcareCaseGateway;
    @Mock
    private AllChildren allChildren;
    @Mock
    private AllCareCaseTasks allCareCaseTasks;
    @Mock
    private Properties ananyaCareProperties;

    private AlertChildAction alertChildAction;


    @Before
    public void setUp() {
        initMocks(this);
        this.alertChildAction = new AlertChildAction(allChildren, commcareCaseGateway, allCareCaseTasks, ananyaCareProperties);
    }

    @Test
    public void shouldSendChildVaccinationAlertToGateway() {
        String scheduleName = "Measles Vaccination";
        String childCaseId = "0A8MF30IJWI0FJW3JFW0J0W3A8";
        String motherCaseId = "motherCaseId";
        String milestoneName = "Measles";
        String groupId = "groupId";
        String flwId = "FLW1234";
        String childName = "Sita";
        DateTime now = DateUtil.now();
        DateTime dob = now.minusWeeks(10);

        Milestone milestone = new Milestone(milestoneName, weeks(15), weeks(20), null, null);
        MilestoneAlert milestoneAlert = MilestoneAlert.fromMilestone(milestone, dob);
        MilestoneEvent milestoneEvent = new MilestoneEvent(childCaseId, scheduleName, milestoneAlert, "due", dob, null);

        Child client = new Child(childCaseId, null, flwId, childName, groupId, dob, null, null, null, motherCaseId, null, null, null, null,null,null,null,null,null,null,null,null,null,true);
        when(allChildren.findByCaseId(childCaseId)).thenReturn(client);
        String commCareUrl = "commCareUrl";
        String motechUserId = "motechUserId";
        when(ananyaCareProperties.getProperty("commcare.hq.url")).thenReturn(commCareUrl);
        when(ananyaCareProperties.getProperty("motech.user.id")).thenReturn(motechUserId);
        alertChildAction.invoke(milestoneEvent);


        ArgumentCaptor<CaseTask> argumentCaptor = ArgumentCaptor.forClass(CaseTask.class);
        verify(commcareCaseGateway).submitCase(eq(commCareUrl),argumentCaptor.capture());
        CaseTask task = argumentCaptor.getValue();

        assertNotNull(task.getTaskId());
        assertNotNull(task.getCurrentTime());
        assertEquals(milestoneName, task.getCaseName());
        assertEquals(now.plusWeeks(5).toString("yyyy-MM-dd"), task.getDateEligible());
        assertEquals(now.plusWeeks(25).toString("yyyy-MM-dd"), task.getDateExpires());
        assertEquals(groupId, task.getOwnerId());
        assertEquals("measles", task.getTaskId());
        assertEquals(childCaseId,task.getClientCaseId());
        assertEquals(CaseType.Child.getType(),task.getClientCaseType());
        assertEquals(motechUserId,task.getMotechUserId());
    }

    @Test
    public void shouldSaveTaskToDB() {
        String scheduleName = "Measles Vaccination";
        String childCaseId = "0A8MF30IJWI0FJW3JFW0J0W3A8";
        String motherCaseId = "motherCaseId";
        String milestoneName = "Measles";
        String groupId = "groupId";
        String flwId = "FLW1234";
        String childName = "Sita";
        DateTime now = DateUtil.now();
        DateTime dob = now.minusWeeks(10);

        Milestone milestone = new Milestone(milestoneName, weeks(15), weeks(20), null, null);
        MilestoneAlert milestoneAlert = MilestoneAlert.fromMilestone(milestone, dob);
        MilestoneEvent milestoneEvent = new MilestoneEvent(childCaseId, scheduleName, milestoneAlert, "due", dob, null);

        Child client = new Child(childCaseId, null, flwId, childName, groupId, dob, null, null, null, motherCaseId, null, null, null, null,null,null,null,null,null,null,null,null,null,true);
        when(allChildren.findByCaseId(childCaseId)).thenReturn(client);
        String motechUserId = "motechUserId";
        when(ananyaCareProperties.getProperty("motech.user.id")).thenReturn(motechUserId);

        alertChildAction.invoke(milestoneEvent);

        ArgumentCaptor<CareCaseTask> captor = ArgumentCaptor.forClass(CareCaseTask.class);
        verify(allCareCaseTasks).add(captor.capture());

        CareCaseTask task = captor.getValue();
        Assert.assertNotNull(task.getCurrentTime());
        assertEquals(milestoneName, task.getMilestoneName());
        assertEquals("task", task.getCaseType());
        assertEquals(now.plusWeeks(5).toString("yyyy-MM-dd"), task.getDateEligible());
        assertEquals(now.plusWeeks(25).toString("yyyy-MM-dd"), task.getDateExpires());
        assertEquals("measles",task.getTaskId());
        assertEquals(groupId, task.getOwnerId());
        assertEquals(childCaseId,task.getClientCaseId());
        assertEquals(CaseType.Child.getType(),task.getClientCaseType());
        assertEquals(motechUserId,task.getMotechUserId());
    }

    @Test
    public void shouldSendChildVaccinationAlertToGatewayWhenEligibleDateIsBeforeToday() {
        String scheduleName = "Measles Vaccination";
        String childCaseId = "0A8MF30IJWI0FJW3JFW0J0W3A8";
        String motherCaseId = "motherCaseId";
        String milestoneName = "Measles";
        String groupId = "groupId";
        String flwId = "FLW1234";
        String childName = "Sita";
        DateTime now = DateUtil.now();
        DateTime dob = now.minusWeeks(10);

        Milestone milestone = new Milestone(milestoneName, weeks(5), weeks(40), null, null);
        MilestoneAlert milestoneAlert = MilestoneAlert.fromMilestone(milestone, dob);
        MilestoneEvent milestoneEvent = new MilestoneEvent(childCaseId, scheduleName, milestoneAlert, "due", dob, null);

        Child client = new Child(childCaseId, null, flwId,childName, groupId, dob, null, null, null, motherCaseId, null, null, null, null,null,null,null,null,null,null,null,null,null,true);
        when(allChildren.findByCaseId(childCaseId)).thenReturn(client);
        alertChildAction.invoke(milestoneEvent);

        ArgumentCaptor<CaseTask> argumentCaptor = ArgumentCaptor.forClass(CaseTask.class);
        verify(commcareCaseGateway).submitCase(anyString(), argumentCaptor.capture());
        CaseTask task = argumentCaptor.getValue();

        assertEquals(now.toString("yyyy-MM-dd"), task.getDateEligible());
        assertEquals(now.plusWeeks(35).toString("yyyy-MM-dd"), task.getDateExpires());
    }

    @Test
    public void shouldTerminateTheExpiryDateToMinimumOfExpiryDateAnd2YearsAgeCompletion() {
        String scheduleName = "Measles Vaccination";
        String childCaseId = "0A8MF30IJWI0FJW3JFW0J0W3A8";
        String motherCaseId = "motherCaseId";
        String milestoneName = "Measles";
        String groupId = "groupId";
        String flwId = "FLW1234";
        String childName = "Sita";
        DateTime now = DateUtil.now();
        DateTime dob = now.minusMonths(2);

        Milestone milestone = new Milestone(milestoneName, months(20), months(10), null, null);
        MilestoneAlert milestoneAlert = MilestoneAlert.fromMilestone(milestone, dob);
        MilestoneEvent milestoneEvent = new MilestoneEvent(childCaseId, scheduleName, milestoneAlert, "due", dob, null);

        Child client = new Child(childCaseId, null, flwId,childName, groupId, dob, null, null, null, motherCaseId, null, null, null, null,null,null,null,null,null,null,null,null,null,true);
        when(allChildren.findByCaseId(childCaseId)).thenReturn(client);
        alertChildAction.invoke(milestoneEvent);

        ArgumentCaptor<CaseTask> argumentCaptor = ArgumentCaptor.forClass(CaseTask.class);
        verify(commcareCaseGateway).submitCase(anyString(), argumentCaptor.capture());
        CaseTask task = argumentCaptor.getValue();

        assertEquals(dob.plusMonths(20).toString("yyyy-MM-dd"), task.getDateEligible());
        assertEquals(dob.plusMonths(24).toString("yyyy-MM-dd"), task.getDateExpires());
    }

    @Test
    public void shouldNotSendChildVaccinationAlertToGatewayWhenEligibleDateIsAfter2YearsOfAge() {
        String scheduleName = "Measles Vaccination";
        String childCaseId = "0A8MF30IJWI0FJW3JFW0J0W3A8";
        String motherCaseId = "motherCaseId";
        String milestoneName = "Measles";
        String groupId = "groupId";
        String flwId = "FLW1234";
        String childName = "Sita";
        DateTime now = DateUtil.now();
        DateTime dob = now.minusWeeks(10);

        Milestone milestone = new Milestone(milestoneName, weeks(110), weeks(10), null, null);
        MilestoneAlert milestoneAlert = MilestoneAlert.fromMilestone(milestone, dob);
        MilestoneEvent milestoneEvent = new MilestoneEvent(childCaseId, scheduleName, milestoneAlert, "due", dob, null);

        Child client = new Child(childCaseId, null, flwId,childName, groupId, dob, null, null, null, motherCaseId, null, null, null, null,null,null,null,null,null,null,null,null,null,true);
        when(allChildren.findByCaseId(childCaseId)).thenReturn(client);
        alertChildAction.invoke(milestoneEvent);

        verify(commcareCaseGateway, never()).submitCase(anyString(), any(CaseTask.class));
    }

    @Test
    public void shouldNotSendChildVaccinationAlertToGatewayWhenExpiryDateIsBeforeToday() {
        String scheduleName = "Measles Vaccination";
        String childCaseId = "0A8MF30IJWI0FJW3JFW0J0W3A8";
        String motherCaseId = "motherCaseId";
        String milestoneName = "Measles";
        String groupId = "groupId";
        String flwId = "FLW1234";
        String childName = "Sita";
        DateTime now = DateUtil.now();
        DateTime dob = now.minusMonths(1);
        DateTime startOfSchedule = now.minusMonths(25);

        Milestone milestone = new Milestone(milestoneName, months(9), months(15), null, null);
        MilestoneAlert milestoneAlert = MilestoneAlert.fromMilestone(milestone, startOfSchedule);
        MilestoneEvent milestoneEvent = new MilestoneEvent(childCaseId, scheduleName, milestoneAlert, "due", startOfSchedule, null);

        Child client = new Child(childCaseId, null, flwId,childName, groupId, dob, null, null, null, motherCaseId, null, null, null, null,null,null,null,null,null,null,null,null,null,true);
        when(allChildren.findByCaseId(childCaseId)).thenReturn(client);
        alertChildAction.invoke(milestoneEvent);

        verify(commcareCaseGateway, never()).submitCase(anyString(), any(CaseTask.class));
    }

    @Test
    public void shouldNotSendChildVaccinationAlertIfChildIsInactive() {
        String scheduleName = "Measles Vaccination";
        String childCaseId = "0A8MF30IJWI0FJW3JFW0J0W3A8";
        String motherCaseId = "motherCaseId";
        String milestoneName = "Measles";
        String groupId = "groupId";
        String flwId = "FLW1234";
        String childName = "Sita";
        DateTime now = DateUtil.now();
        DateTime dob = now.minusWeeks(10);

        Milestone milestone = new Milestone(milestoneName, weeks(15), weeks(20), null, null);
        MilestoneAlert milestoneAlert = MilestoneAlert.fromMilestone(milestone, dob);
        MilestoneEvent milestoneEvent = new MilestoneEvent(childCaseId, scheduleName, milestoneAlert, "due", dob, null);

        Child client = new Child(childCaseId, null, flwId, childName, groupId, dob, null, null, null, motherCaseId, null, null, null, null,null,null,null,null,null,null,null,null,null,true);
        client.setClosedByCommcare(true);
        when(allChildren.findByCaseId(childCaseId)).thenReturn(client);
        alertChildAction.invoke(milestoneEvent);

        verify(commcareCaseGateway, never()).submitCase(anyString(), any(CaseTask.class));
    }

    public static Period weeks(int numberOfWeeks) {
        return new Period(0, 0, numberOfWeeks, 0, 0, 0, 0, 0);
    }

    public static Period months(int numberOfMonths) {
        return new Period(0, numberOfMonths, 0, 0, 0, 0, 0, 0);
    }


}
