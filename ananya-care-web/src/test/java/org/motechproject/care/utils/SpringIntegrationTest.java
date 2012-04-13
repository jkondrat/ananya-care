package org.motechproject.care.utils;

import org.ektorp.BulkDeleteDocument;
import org.ektorp.CouchDbConnector;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Properties;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:applicationContext-Web.xml")
public abstract class SpringIntegrationTest {

    @Qualifier("ananyaCareDbConnector")
    @Autowired
    protected CouchDbConnector ananyaCareDbConnector;


    @Qualifier("ananyaCareProperties")
    @Autowired
    protected Properties ananyaCareProperties;

    protected ArrayList<BulkDeleteDocument> toDelete;

    @Before
    public void before() {
        toDelete = new ArrayList<BulkDeleteDocument>();
    }

    @After
    public void after() {
        ananyaCareDbConnector.executeBulk(toDelete);
    }

    protected void markForDeletion(Object document) {
        toDelete.add(BulkDeleteDocument.of(document));
    }

    protected String getAppServerPort() {
        return ananyaCareProperties.getProperty("app.server.port");
    }

    protected String getAppServerHostUrl() {
        return "http://localhost:" + getAppServerPort();
    }
}