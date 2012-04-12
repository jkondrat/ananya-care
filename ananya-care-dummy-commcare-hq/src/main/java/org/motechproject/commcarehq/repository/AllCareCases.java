package org.motechproject.commcarehq.repository;

import org.ektorp.CouchDbConnector;
import org.motechproject.commcarehq.domain.CareCase;
import org.motechproject.dao.MotechBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class AllCareCases extends MotechBaseRepository<CareCase> {

    @Autowired
    public AllCareCases(@Qualifier("ananyaCareDbConnector") CouchDbConnector dbCouchDbConnector) {
        super(CareCase.class, dbCouchDbConnector);
    }
}
