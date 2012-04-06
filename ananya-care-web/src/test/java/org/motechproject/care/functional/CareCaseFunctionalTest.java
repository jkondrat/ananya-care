package org.motechproject.care.functional;

import junit.framework.Assert;
import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Test;
import org.motechproject.care.domain.Mother;
import org.motechproject.care.repository.AllMothers;
import org.motechproject.care.repository.SpringIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;

public class CareCaseFunctionalTest extends SpringIntegrationTest{
    @Autowired
    private AllMothers allMothers;

    @After
    public void after(){
        allMothers.removeAll();
    }

    @Test
    public void shouldCreateMother() throws IOException {
        File file = new File(getClass().getResource("/sampleMotherCase.xml").getPath());
        String body = FileUtils.readFileToString(file);

        new RestTemplate().postForLocation(getAppServerHostUrl() + "/ananya-care/care/process", body);

        Mother motherFromDb = allMothers.findByCaseId("8055b3ec-bec6-46cc-9e72-435ebc4eaec1");
        Assert.assertEquals("d823ea3d392a06f8b991e9e49394ce45",motherFromDb.getGroupId());
        Assert.assertEquals("d823ea3d392a06f8b991e9e4933348bd",motherFromDb.getFlwId());
        Assert.assertEquals("NEERAJ",motherFromDb.getName());
        Assert.assertEquals(DateTime.parse("2012-10-20"),motherFromDb.getEdd());
        Assert.assertEquals(DateTime.parse("2012-01-01"),motherFromDb.getTt1Date());
        Assert.assertEquals(false,motherFromDb.isLastPregTt());
        Assert.assertTrue(motherFromDb.isActive());
    }

    @Test
    public void shouldUpdateMother() throws IOException {
        RestTemplate restTemplate = new RestTemplate();

        File file = new File(getClass().getResource("/sampleMotherCase.xml").getPath());
        String body = FileUtils.readFileToString(file);
        restTemplate.postForLocation(getAppServerHostUrl() + "/ananya-care/care/process", body);

        file = new File(getClass().getResource("/sampleMotherCaseForUpdate.xml").getPath());
        body = FileUtils.readFileToString(file);
        restTemplate.postForLocation(getAppServerHostUrl() + "/ananya-care/care/process", body);

        Mother motherFromDb = allMothers.findByCaseId("8055b3ec-bec6-46cc-9e72-435ebc4eaec1");
        Assert.assertEquals("d823ea3d392a06f8b991e9e49394ce45",motherFromDb.getGroupId());
        Assert.assertEquals("d823ea3d392a06f8b991e9e4933348bd",motherFromDb.getFlwId());
        Assert.assertEquals("NEERAJ",motherFromDb.getName());
        Assert.assertEquals(DateTime.parse("2012-10-20"),motherFromDb.getEdd());
        Assert.assertEquals(DateTime.parse("2012-10-21"),motherFromDb.getAdd());
        Assert.assertEquals(DateTime.parse("2012-01-01"),motherFromDb.getTt1Date());
        Assert.assertEquals(false,motherFromDb.isLastPregTt());
        Assert.assertEquals(DateTime.parse("2012-01-02"),motherFromDb.getTt2Date());
        Assert.assertFalse(motherFromDb.isActive());
    }

    @Test
    public void shouldCloseMother() throws IOException {
        RestTemplate restTemplate = new RestTemplate();

        File file = new File(getClass().getResource("/sampleMotherCase.xml").getPath());
        String body = FileUtils.readFileToString(file);
        restTemplate.postForLocation(getAppServerHostUrl() + "/ananya-care/care/process", body);
        Mother motherFromDb = allMothers.findByCaseId("8055b3ec-bec6-46cc-9e72-435ebc4eaec1");
        Assert.assertTrue(motherFromDb.isActive());

        file = new File(getClass().getResource("/sampleMotherCaseForClose.xml").getPath());
        body = FileUtils.readFileToString(file);
        restTemplate.postForLocation(getAppServerHostUrl() + "/ananya-care/care/process", body);

        motherFromDb = allMothers.findByCaseId("8055b3ec-bec6-46cc-9e72-435ebc4eaec1");
        Assert.assertFalse(motherFromDb.isActive());
    }
}
