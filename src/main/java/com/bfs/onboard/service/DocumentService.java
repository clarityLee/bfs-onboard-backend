package com.bfs.onboard.service;

import com.bfs.onboard.constant.DocumentType;
import com.bfs.onboard.dao.PersonalDocumentDao;
import com.bfs.onboard.dao.impl.BasicTemplate;
import com.bfs.onboard.domain.PersonalDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DocumentService {

    private BasicTemplate template;
    private PersonalDocumentDao personalDocumentDao;
    @Autowired
    @Qualifier("hibernateTemplate")
    public void setTemplate(BasicTemplate basicTemplate) {
        this.template = basicTemplate;
    }
    @Autowired
    public void setPersonalDocumentDao(PersonalDocumentDao personalDocumentDao) {
        this.personalDocumentDao = personalDocumentDao;
    }

    public void saveComment(Integer personalDocumentId, String comment) {
        PersonalDocument p = template.findById(personalDocumentId, PersonalDocument.class);
        p.setComment(comment);
        template.save(p);
    }

    public List<PersonalDocument> getWorkDocs(Integer employeeId) {
        return personalDocumentDao.getWorkDocsByEmployee(employeeId);
    }
}
