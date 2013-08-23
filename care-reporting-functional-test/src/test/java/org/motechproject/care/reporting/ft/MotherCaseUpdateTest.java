package org.motechproject.care.reporting.ft;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.care.reporting.ft.pages.MotechEndpoint;
import org.motechproject.care.reporting.ft.utils.PropertyFile;
import org.motechproject.care.reporting.ft.utils.RecordUpdatedCondition;
import org.motechproject.care.reporting.ft.utils.ReflectionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.motechproject.care.reporting.ft.utils.AssertionUtils.assertContainsAll;

public class MotherCaseUpdateTest extends BaseTestCase {

    private String groupId;
    private String caseId;
    private String createdByFlwId;
    private String updatedByFlwId;
    private String closeByFlwId;
    private String serverDateModified;
    private String serverDateModifiedIST;

    private Map<String, String> placeholderMap = new HashMap<>();
    private Map<String, String> header = new HashMap<>();

    @Before
    public void setUp() {
        caseId = UUID.randomUUID().toString();
        groupId = UUID.randomUUID().toString().replaceAll("-", "");
        createdByFlwId = UUID.randomUUID().toString().replaceAll("-", "");
        updatedByFlwId = UUID.randomUUID().toString().replaceAll("-", "");
        closeByFlwId = UUID.randomUUID().toString().replaceAll("-", "");
        serverDateModified = "2013-07-20T10:45:50.908Z";
        serverDateModifiedIST = "2013-07-20 16:15:50.908";

        placeholderMap.put("caseId", caseId);
        placeholderMap.put("ownerId", groupId);
        placeholderMap.put("userId", createdByFlwId);
        placeholderMap.put("serverDateModifiedIST", serverDateModifiedIST);

        header.put("server-modified-on",serverDateModified);
    }

    @After
    public void tearDown() {
        reportingDatabase().deleteMother(caseId);
        reportingDatabase().deleteFLW(createdByFlwId);
        reportingDatabase().deleteFLW(updatedByFlwId);
        reportingDatabase().deleteFLW(closeByFlwId);
        reportingDatabase().deleteGroup(groupId);
    }

    @Test
    public void createUpdateAndCloseMotherCase() {
        createCase();
        updateCase();
        closeCase();
    }

    private void createCase() {

        MotechEndpoint motechEndpoint = new MotechEndpoint();
        int response = motechEndpoint.postCase(constructRequestTemplateUrl("mother_case_create"), placeholderMap, header);
        assertEquals(200, response);

        Map<String, Object> actualFlw = reportingDatabase().flw.waitAndGet(createdByFlwId);
        PropertyFile expectedFLWValues = new PropertyFile(constructExpectedUrl("reporting/flw"), placeholderMap);
        assertContainsAll(expectedFLWValues.properties(), ReflectionUtils.serializeMap(actualFlw));

        Map<String, Object> actualGroup = reportingDatabase().flwGroup.waitAndGet(groupId);
        PropertyFile expectedGroupValues = new PropertyFile(constructExpectedUrl("reporting/group"), placeholderMap);
        assertContainsAll(expectedGroupValues.properties(), ReflectionUtils.serializeMap(actualGroup));

        placeholderMap.put("userDbId", actualFlw.get("id").toString());
        placeholderMap.put("ownerDbId", actualGroup.get("id").toString());

        Map<String, Object> motherCase = reportingDatabase().motherCase.waitAndGet(caseId);
        PropertyFile expectedFormValues = new PropertyFile(constructExpectedUrl("reporting/mother_case_create"), placeholderMap);
        assertContainsAll(expectedFormValues.properties(), ReflectionUtils.serializeMap(motherCase));

        assertNull(reportingDatabase().flwGroupMap.find(actualFlw.get("id")));
    }

    private void updateCase() {
        placeholderMap.put("userId", updatedByFlwId);

        long currentTime = System.currentTimeMillis();

        MotechEndpoint motechEndpoint = new MotechEndpoint();
        int response = motechEndpoint.postCase(constructRequestTemplateUrl("mother_case_update"), placeholderMap, header);
        assertEquals(200, response);

        Map<String, Object> actualFlw = reportingDatabase().flw.waitAndGet(updatedByFlwId);
        PropertyFile expectedFLWValues = new PropertyFile(constructExpectedUrl("reporting/flw"), placeholderMap);
        assertContainsAll(expectedFLWValues.properties(), ReflectionUtils.serializeMap(actualFlw));

        Map<String, Object> actualGroup = reportingDatabase().flwGroup.waitAndGet(groupId);
        PropertyFile expectedGroupValues = new PropertyFile(constructExpectedUrl("reporting/group"), placeholderMap);
        assertContainsAll(expectedGroupValues.properties(), ReflectionUtils.serializeMap(actualGroup));

        placeholderMap.put("userDbId", actualFlw.get("id").toString());
        placeholderMap.put("ownerDbId", actualGroup.get("id").toString());

        Map<String, Object> motherCase = reportingDatabase().motherCase.waitAndGet(caseId, new RecordUpdatedCondition(currentTime));
        PropertyFile expectedFormValues = new PropertyFile(constructExpectedUrl("reporting/mother_case_update"), placeholderMap);
        assertContainsAll(expectedFormValues.properties(), ReflectionUtils.serializeMap(motherCase));

        assertNull(reportingDatabase().flwGroupMap.find(actualFlw.get("id")));
    }

    private void closeCase() {
        placeholderMap.put("userId", closeByFlwId);

        long currentTime = System.currentTimeMillis();

        MotechEndpoint motechEndpoint = new MotechEndpoint();
        int response = motechEndpoint.postCase(constructRequestTemplateUrl("mother_case_close"), placeholderMap, header);
        assertEquals(200, response);

        Map<String, Object> actualFlw = reportingDatabase().flw.waitAndGet(closeByFlwId);
        PropertyFile expectedFLWValues = new PropertyFile(constructExpectedUrl("reporting/flw"), placeholderMap);
        assertContainsAll(expectedFLWValues.properties(), ReflectionUtils.serializeMap(actualFlw));

        Map<String, Object> actualGroup = reportingDatabase().flwGroup.waitAndGet(groupId);
        PropertyFile expectedGroupValues = new PropertyFile(constructExpectedUrl("reporting/group"), placeholderMap);
        assertContainsAll(expectedGroupValues.properties(), ReflectionUtils.serializeMap(actualGroup));

        placeholderMap.put("userDbId", actualFlw.get("id").toString());
        placeholderMap.put("ownerDbId", actualGroup.get("id").toString());

        Map<String, Object> motherCase = reportingDatabase().motherCase.waitAndGet(caseId, new RecordUpdatedCondition(currentTime));
        PropertyFile expectedFormValues = new PropertyFile(constructExpectedUrl("reporting/mother_case_close"), placeholderMap);
        assertContainsAll(expectedFormValues.properties(), ReflectionUtils.serializeMap(motherCase));

        assertNull(reportingDatabase().flwGroupMap.find(actualFlw.get("id")));
    }

    @Override
    protected String getTestIdentifier() {
        return "motherCaseUpdate";
    }
}
