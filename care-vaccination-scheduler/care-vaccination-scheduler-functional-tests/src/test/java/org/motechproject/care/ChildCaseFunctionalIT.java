package org.motechproject.care;

import junit.framework.Assert;
import org.antlr.stringtemplate.StringTemplate;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.casexml.builder.ResponseMessageBuilder;
import org.motechproject.care.domain.Child;
import org.motechproject.care.repository.AllChildren;
import org.motechproject.care.schedule.vaccinations.ChildVaccinationSchedule;
import org.motechproject.care.schedule.vaccinations.ExpirySchedule;
import org.motechproject.care.service.CareCaseService;
import org.motechproject.care.service.ChildService;
import org.motechproject.care.service.MotherService;
import org.motechproject.care.utils.StringTemplateHelper;
import org.motechproject.commons.date.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@ContextConfiguration("classpath*:applicationAnanyaCareTest.xml")
public class ChildCaseFunctionalIT extends SpringIntegrationTest {

    @Autowired
    private MotherService motherService;

    @Autowired
    private ChildService childService;

    @Autowired
    private ResponseMessageBuilder responseMessageBuilder;

    @Autowired
    private AllChildren allChildren;

    String motherCaseId;
    DateTime dob;
    CareCaseService careCaseService;


    @Before
    public void setUp(){
        careCaseService = new CareCaseService(motherService, childService);
        careCaseService.setResponseMessageBuilder(responseMessageBuilder);
        motherCaseId = UUID.randomUUID().toString();
        dob = DateUtil.newDateTime(DateUtil.today());
    }

    @Test
    public void shouldCreateChild() throws IOException {
        String uniqueCaseId= UUID.randomUUID().toString();

        postChildXmlToMotechCare(uniqueCaseId, "/caseXmls/childRegistrationCaseXml.st");

        Child childFromDb = allChildren.findByCaseId(uniqueCaseId);
        Assert.assertNotNull(childFromDb);
        markForDeletion(childFromDb);

        Assert.assertEquals("d823ea3d392a06f8b991e9e49394ce45", childFromDb.getGroupId());
        Assert.assertEquals("e819879aaf53a3787e0fd88993ac105d", childFromDb.getFlwId());
        Assert.assertEquals("RAM",childFromDb.getName());
        Assert.assertEquals(dob, childFromDb.getDOB());
        Assert.assertEquals(motherCaseId, childFromDb.getMotherCaseId());
        Assert.assertTrue(childFromDb.isActive());

        markScheduleForUnEnrollment(uniqueCaseId, ChildVaccinationSchedule.Bcg.getName());
        markScheduleForUnEnrollment(uniqueCaseId, ChildVaccinationSchedule.Measles.getName());
        markScheduleForUnEnrollment(uniqueCaseId, ChildVaccinationSchedule.Vita.getName());
        markScheduleForUnEnrollment(uniqueCaseId, ExpirySchedule.ChildCare.getName());

        Assert.assertEquals("Bcg", trackingService.getEnrollment(uniqueCaseId, ChildVaccinationSchedule.Bcg.getName()).getCurrentMilestoneName());
        Assert.assertEquals("Vita", trackingService.getEnrollment(uniqueCaseId, ChildVaccinationSchedule.Vita.getName()).getCurrentMilestoneName());
        Assert.assertEquals("Measles", trackingService.getEnrollment(uniqueCaseId, ChildVaccinationSchedule.Measles.getName()).getCurrentMilestoneName());
        Assert.assertEquals("Child Care", trackingService.getEnrollment(uniqueCaseId, ExpirySchedule.ChildCare.getName()).getCurrentMilestoneName());
    }

    @Test
    public void shouldUpdateChild() throws IOException {
        String uniqueCaseId= UUID.randomUUID().toString();

        postChildXmlToMotechCare(uniqueCaseId, "/caseXmls/childRegistrationCaseXml.st");
        postChildXmlToMotechCare(uniqueCaseId, "/caseXmls/childUpdateCaseXml.st");
        Child childFromDb = allChildren.findByCaseId(uniqueCaseId);

        Assert.assertNotNull(childFromDb);
        markForDeletion(childFromDb);

        Assert.assertEquals("d823ea3d392a06f8b991e9e49394ce45", childFromDb.getGroupId());
        Assert.assertEquals("e819879aaf53a3787e0fd88993ac105d", childFromDb.getFlwId());
        Assert.assertEquals("RAM",childFromDb.getName());
        Assert.assertEquals(dob, childFromDb.getDOB());
        Assert.assertEquals(motherCaseId, childFromDb.getMotherCaseId());
        Assert.assertEquals(DateTime.parse("2012-12-04") , childFromDb.getBcgDate());
        Assert.assertEquals(DateTime.parse("2012-12-05") , childFromDb.getMeaslesDate());
        Assert.assertTrue(childFromDb.isActive());

        markScheduleForUnEnrollment(uniqueCaseId, ChildVaccinationSchedule.Bcg.getName());
        markScheduleForUnEnrollment(uniqueCaseId, ChildVaccinationSchedule.Measles.getName());
        markScheduleForUnEnrollment(uniqueCaseId, ChildVaccinationSchedule.Vita.getName());
        markScheduleForUnEnrollment(uniqueCaseId, ExpirySchedule.ChildCare.getName());

        assertNull(trackingService.getEnrollment(uniqueCaseId, ChildVaccinationSchedule.Bcg.getName()));
        assertNull(trackingService.getEnrollment(uniqueCaseId, ChildVaccinationSchedule.Measles.getName()));
        assertEquals("Vita", trackingService.getEnrollment(uniqueCaseId, ChildVaccinationSchedule.Vita.getName()).getCurrentMilestoneName());
        Assert.assertEquals("Child Care", trackingService.getEnrollment(uniqueCaseId, ExpirySchedule.ChildCare.getName()).getCurrentMilestoneName());
    }

    private void postChildXmlToMotechCare(String uniqueCaseId, String xmlFileName) throws IOException {
        StringTemplate stringTemplate = StringTemplateHelper.getStringTemplate(xmlFileName);
        stringTemplate.setAttribute("caseId",uniqueCaseId);
        stringTemplate.setAttribute("dobDate",DateUtil.newDate(dob).toString());
        stringTemplate.setAttribute("motherCaseId",motherCaseId);

        careCaseService.processCase(new HttpEntity(stringTemplate.toString()));
    }
}
