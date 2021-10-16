package com.bfs.onboard.controller;

import com.bfs.onboard.domain.PersonalDocument;
import com.bfs.onboard.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/docs/")
public class DocumentController {

    private DocumentService documentService;
    @Autowired
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/work-auth/employee/{id}")
    public ResponseEntity<List<PersonalDocument>> workDocsByEmployee(
            @PathVariable(name = "id") Integer employeeId) {

        return new ResponseEntity<>(documentService.getWorkDocs(employeeId), HttpStatus.OK);
    }

}
