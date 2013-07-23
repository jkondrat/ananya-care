package org.motechproject.care.reporting.migration.service;

import org.motechproject.care.reporting.migration.util.CommcareAPIHttpClient;
import org.motechproject.care.reporting.migration.util.MotechAPIHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CaseMigrationTask extends MigrationTask {

    private CommcareAPIHttpClient commcareAPIHttpClient;
    private MotechAPIHttpClient motechAPIHttpClient;
    private static final Logger logger = LoggerFactory.getLogger(CaseMigrationTask.class);

    @Autowired
    public CaseMigrationTask(MigrationBatchProcessor migrationBatchProcessor, CommcareAPIHttpClient commcareAPIHttpClient, MotechAPIHttpClient motechAPIHttpClient) {
        super(migrationBatchProcessor);
        this.commcareAPIHttpClient = commcareAPIHttpClient;
        this.motechAPIHttpClient = motechAPIHttpClient;
    }

    @Override
    public void migrate(String id) {
        logger.info(String.format("Migrating Case: %s", id));
        List<CommcareResponseWrapper> caseResponses = commcareAPIHttpClient.fetchCase(id);
        for (CommcareResponseWrapper caseResponse : caseResponses) {
            motechAPIHttpClient.postCase(caseResponse);
        }
        logger.info(String.format("Migrating completed for Case: %s", id));
    }
}
