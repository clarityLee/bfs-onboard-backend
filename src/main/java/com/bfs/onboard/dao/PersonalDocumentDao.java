package com.bfs.onboard.dao;

import com.bfs.onboard.domain.PersonalDocument;

import java.util.List;

public interface PersonalDocumentDao {
    List<PersonalDocument> getByEmployee(Integer employeeId);
}
