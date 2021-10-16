package com.bfs.onboard.dao.impl;

import com.bfs.onboard.dao.PersonalDocumentDao;
import com.bfs.onboard.domain.PersonalDocument;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class PersonalDocumentDaoHibernateImpl extends BasicTemplate implements PersonalDocumentDao {

    @Override
    public List<PersonalDocument> getByEmployee(Integer employeeId) {
        return getByField("employeeId", employeeId, PersonalDocument.class);
    }

}
