package org.motechproject.care.reporting.migration.task;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.motechproject.care.reporting.migration.MigratorArguments;
import org.motechproject.care.reporting.migration.common.CommcareResponseWrapper;
import org.motechproject.care.reporting.migration.common.Page;
import org.motechproject.care.reporting.migration.common.PaginatedResponse;
import org.motechproject.care.reporting.migration.common.PaginatedResponseMeta;
import org.motechproject.care.reporting.migration.common.ResponseParser;
import org.motechproject.care.reporting.migration.statistics.MigrationStatisticsCollector;
import org.motechproject.care.reporting.migration.util.CommcareAPIHttpClient;
import org.motechproject.care.reporting.migration.util.CommcareDataUtil;
import org.motechproject.care.reporting.migration.util.MotechAPIHttpClient;
import org.unitils.reflectionassert.ReflectionAssert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.motechproject.care.reporting.migration.common.Constants.*;

public class FormMigrationTaskTest {

    @Mock
    private CommcareAPIHttpClient commcareAPIHttpClient;
    @Mock
    private MotechAPIHttpClient motechAPIHttpClient;
    @Mock
    private ResponseParser parser;
    @Mock
    private MigratorArguments migratorArguments;
    @Mock
    private MigrationStatisticsCollector statisticsCollector;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void shouldFetchWithParameters() {
        DateTime now = DateTime.now();
        Map<String, Object> optionsMap = new HashMap<>();
        optionsMap.put(TYPE, "namespace");
        optionsMap.put(VERSION, "version");
        optionsMap.put(START_DATE, now.toDate().toString());
        optionsMap.put(END_DATE, now.toDate().toString());
        optionsMap.put(LIMIT, 100);
        optionsMap.put(OFFSET, 2000);

        MigrationTask formMigrationTask = new FormMigrationTask(commcareAPIHttpClient, motechAPIHttpClient, parser, statisticsCollector, new CommcareDataUtil());
        when(migratorArguments.getOptions()).thenReturn(optionsMap);

        Map<String, String> expectedNameValuePairs = new HashMap<>();
        expectedNameValuePairs.put(FORM_NAMESPACE, "namespace");
        expectedNameValuePairs.put(FORM_VERSION, "version");
        expectedNameValuePairs.put(FORM_START_DATE, now.toDate().toString());
        expectedNameValuePairs.put(FORM_END_DATE, now.toDate().toString());
        expectedNameValuePairs.put(LIMIT, String.valueOf(100));
        expectedNameValuePairs.put(OFFSET, String.valueOf(2000));

        PaginatedResponse paginatedResult = new PaginatedResponse(new JsonArray(), new PaginatedResponseMeta(null, null, null, 0));
        when(parser.parse("someresponse")).thenReturn(paginatedResult);
        when(commcareAPIHttpClient.fetchForms(any(Map.class), any(Page.class))).thenReturn("someresponse");

        formMigrationTask.migrate(migratorArguments);

        ArgumentCaptor<Map> parameterCaptor = ArgumentCaptor.forClass(Map.class);
        verify(commcareAPIHttpClient).fetchForms(parameterCaptor.capture(), any(Page.class));
        ReflectionAssert.assertLenientEquals(expectedNameValuePairs, parameterCaptor.getValue());

        verify(statisticsCollector).addRecordsDownloaded(0);
        verify(statisticsCollector).addRecordsUploaded(0);
    }

    @Test
    public void shouldFetchFormsWithParameters() {
        String formResponse1 = "response1";
        JsonArray jsonResponse1 = getJsonFormArray("2013-10-30", 1);
        when(parser.parse(formResponse1)).thenReturn(new PaginatedResponse(jsonResponse1, new PaginatedResponseMeta(new Page(0, 100), new Page(100, 100), null, 100)));
        String formResponse2 = "response2";
        JsonArray jsonResponse2 = getJsonFormArray("2013-12-13", 1);
        when(parser.parse(formResponse2)).thenReturn(new PaginatedResponse(jsonResponse2, new PaginatedResponseMeta(new Page(0, 100), null, null, 100)));
        when(commcareAPIHttpClient.fetchForms(anyMap(), any(Page.class))).thenReturn(formResponse1).thenReturn(formResponse2).thenReturn(null);
        MigrationTask formMigrationTask = new FormMigrationTask(commcareAPIHttpClient, motechAPIHttpClient, parser, statisticsCollector, new CommcareDataUtil());

        formMigrationTask.migrate(migratorArguments);

        ArgumentCaptor<Map> parameterCaptor = ArgumentCaptor.forClass(Map.class);
        ArgumentCaptor<Page> optionCaptor = ArgumentCaptor.forClass(Page.class);
        ArgumentCaptor<CommcareResponseWrapper> formReponseCaptor = ArgumentCaptor.forClass(CommcareResponseWrapper.class);

        verify(commcareAPIHttpClient, times(2)).fetchForms(parameterCaptor.capture(), optionCaptor.capture());
        List<Page> actualOptions = optionCaptor.getAllValues();
        ReflectionAssert.assertReflectionEquals(new Page(0, 100), actualOptions.get(0));
        ReflectionAssert.assertReflectionEquals(new Page(100, 100), actualOptions.get(1));

        verify(motechAPIHttpClient, times(2)).postForm(formReponseCaptor.capture());
        List<CommcareResponseWrapper> actualForms = formReponseCaptor.getAllValues();
        assertEquals("2013-10-30", actualForms.get(0).getHeaders().get("received-on"));
        assertEquals("2013-12-13", actualForms.get(1).getHeaders().get("received-on"));

        ArgumentCaptor<Integer> recordsDownloadedCountCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Integer> recordsUploadedCountCaptor = ArgumentCaptor.forClass(Integer.class);

        verify(statisticsCollector, times(2)).addRecordsDownloaded(recordsDownloadedCountCaptor.capture());
        verify(statisticsCollector, times(2)).addRecordsUploaded(recordsUploadedCountCaptor.capture());
        List<Integer> recordsDownloadedCounts = recordsDownloadedCountCaptor.getAllValues();
        List<Integer> recordsUploadedCounts = recordsUploadedCountCaptor.getAllValues();
        assertEquals(Integer.valueOf(1), recordsDownloadedCounts.get(0));
        assertEquals(Integer.valueOf(1), recordsDownloadedCounts.get(1));
        assertEquals(Integer.valueOf(1), recordsUploadedCounts.get(0));
        assertEquals(Integer.valueOf(1), recordsUploadedCounts.get(1));
    }

    @Test
    public void shouldPostMultipleTimesForMultipleForms() {
        String formResponse1 = "response1";
        JsonArray jsonResponse1 = getJsonFormArray("2013-10-30", 2);
        when(parser.parse(formResponse1)).thenReturn(new PaginatedResponse(jsonResponse1, new PaginatedResponseMeta(null, null, null, 0)));
        when(commcareAPIHttpClient.fetchForms(anyMap(), any(Page.class))).thenReturn(formResponse1).thenReturn(null);
        MigrationTask formMigrationTask = new FormMigrationTask(commcareAPIHttpClient, motechAPIHttpClient, parser, statisticsCollector, new CommcareDataUtil());
        formMigrationTask.migrate(migratorArguments);

        ArgumentCaptor<CommcareResponseWrapper> formReponseCaptor = ArgumentCaptor.forClass(CommcareResponseWrapper.class);
        verify(motechAPIHttpClient, times(2)).postForm(formReponseCaptor.capture());


        verify(statisticsCollector).addRecordsDownloaded(2);
        verify(statisticsCollector).addRecordsUploaded(2);
    }

    private JsonArray getJsonFormArray(String receivedOn, int sizeOfArray) {
        JsonArray forms = new JsonArray();
        JsonElement element = new JsonParser().parse(getFormJson(receivedOn));
        for (int i = 1; i <= sizeOfArray; i++) {
            forms.add(element);
        }
        return forms;
    }

    private String getFormJson(final String receivedOn) {
        return "{\n" +
                "    \"archived\": false,\n" +
                "    \"form\": {\n" +
                "        \"#type\": \"data\",\n" +
                "        \"@name\": \"New Beneficiary\"\n" +
                "    },\n" +
                "    \"id\": \"d0e54970-0ec7-45d4-87ef-21eaa504cf91\",\n" +
                "    \"md5\": \"OBSOLETED\",\n" +
                "    \"metadata\": {\n" +
                "        \"@xmlns\": \"http://openrosa.org/jr/xforms\",\n" +
                "        \"appVersion\": \"@xmlns:http://commcarehq.org/xforms, #text:v2.0.0alpha (990ba3-e6e3c5-unvers-2.1.0-Nokia/S40-native-input) #48 b:2012-Jul-26 r:2012-Jul-28\",\n" +
                "        \"deprecatedID\": null,\n" +
                "        \"deviceID\": \"BHN2E3BXVDP274W2ZJ74ASH31\",\n" +
                "        \"instanceID\": \"d0e54970-0ec7-45d4-87ef-21eaa504cf91\",\n" +
                "        \"timeEnd\": \"2013-02-11T15:42:58\",\n" +
                "        \"timeStart\": \"2013-02-11T15:41:09\",\n" +
                "        \"userID\": \"89fda0284e008d2e0c980fb13f98a6d5\",\n" +
                "        \"username\": \"8969815368\"\n" +
                "    },\n" +
                "    \"received_on\": \"" + receivedOn + "\",\n" +
                "    \"resource_uri\": \"\",\n" +
                "    \"type\": \"data\",\n" +
                "    \"uiversion\": \"1\",\n" +
                "    \"version\": \"1\"\n" +
                "}\n";

    }

}
