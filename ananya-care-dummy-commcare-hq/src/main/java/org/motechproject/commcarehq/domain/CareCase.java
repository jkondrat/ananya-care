package org.motechproject.commcarehq.domain;


import org.codehaus.jackson.annotate.JsonProperty;
import org.ektorp.support.TypeDiscriminator;
import org.joda.time.DateTime;
import org.motechproject.model.MotechBaseDataObject;

@TypeDiscriminator("doc.type == 'CareCase'")
public class CareCase extends MotechBaseDataObject {

    @JsonProperty
    private String caseId;

    @JsonProperty
    private String xmlDocument;

    @JsonProperty
    private DateTime submittedAt;

    public CareCase(String caseId, String xmlDocument, DateTime submittedAt) {
        this.caseId = caseId;
        this.xmlDocument = xmlDocument;
        this.submittedAt = submittedAt;
    }
}