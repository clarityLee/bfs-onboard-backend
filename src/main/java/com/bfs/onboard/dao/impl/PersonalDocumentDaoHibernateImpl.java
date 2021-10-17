package com.bfs.onboard.dao.impl;

import com.bfs.onboard.constant.DocumentType;
import com.bfs.onboard.dao.PersonalDocumentDao;
import com.bfs.onboard.domain.PersonalDocument;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class PersonalDocumentDaoHibernateImpl extends BasicTemplate implements PersonalDocumentDao {

    @Override
    public List<PersonalDocument> getByEmployee(Integer employeeId) {
        return getByField("employeeId", employeeId, PersonalDocument.class);
    }

    @Override
    public List<PersonalDocument> getWorkDocsByEmployee(Integer employeeId) {
        return getByField("employeeID", employeeId, PersonalDocument.class).stream()
                .filter(p -> DocumentType.WORK_AUTHORIZATION.equals(p.getTitle()))
                .collect(Collectors.toList());
    }

}
