package org.motechproject.commcare.parser;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;
import org.motechproject.commcare.domain.Case;

import java.io.*;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: pchandra
 * Date: 3/24/12
 * Time: 6:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class CommcareCaseParserTest extends TestCase {

    @Test
    public void testShouldParseCaseAttributesCorrectly() throws FileNotFoundException {
        CommcareCaseParser parser = new CommcareCaseParser();
        Case aCase = parser.parseCase(caseXml());
        Assert.assertEquals("3F2504E04F8911D39A0C0305E82C3301",aCase.getCaseId());
        Assert.assertEquals("11/10/09 21:23:43",aCase.getDateModified());
    }

    @Test
    public void testShouldParseCreateAttributesCorrectly() throws FileNotFoundException {
        CommcareCaseParser parser = new CommcareCaseParser();
        Case aCase = parser.parseCase(caseXml());
        Assert.assertEquals("houshold_rollout_ONICAF",aCase.getCaseTypeId());
        Assert.assertEquals("Smith",aCase.getCaseName());
    }

    @Test
    public void testShouldSetActionCorrectly() throws FileNotFoundException {
        CommcareCaseParser parser = new CommcareCaseParser();
        Case aCase = parser.parseCase(caseXml());
        Assert.assertEquals("CREATE",aCase.getAction());
    }

    @Test
    public void testShouldSetUpdateAttributesCorrectly() throws FileNotFoundException {
        CommcareCaseParser parser = new CommcareCaseParser();
        Case aCase = parser.parseCase(caseXml());

        Map<String,String> fieldValues = aCase.getFieldValues();
        Assert.assertEquals("Tom Smith",fieldValues.get("primary_contact_name"));
    }

    private String caseXml() {
        String caseXml = "<case>"+
        "<case_id>3F2504E04F8911D39A0C0305E82C3301</case_id>"+
        "<date_modified>11/10/09 21:23:43</date_modified>"+
        "<create>"+
        "<case_type_id>houshold_rollout_ONICAF</case_type_id>"+
        "<case_name>Smith</case_name>"+
        "</create>"+
        "<update>"+
        "<household_id>24/F23/3</household_id>"+
        "<primary_contact_name>Tom Smith</primary_contact_name>"+
        "<visit_number>1</visit_number>"+
        "</update>"+
        "</case>";

        return caseXml;  //To change body of created methods use File | Settings | File Templates.
    }

}
