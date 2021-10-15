package com.bfs.onboard.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "personaldocument")
@NoArgsConstructor
@Getter
@Setter
public class PersonalDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "employeeid")
    private Integer employeeID;

    @Column(name = "path")
    private String path;

    // "I-983 Filled", "OPT EAD", "OPT Receipt", "I-20", "I-983 Signed", "OPT STEM EAD", "OPT STEM Receipt"
    @Column(name = "title")
    private String title;

    @Column(name = "comment")
    private String comment;

    @Column(name = "createdate")
    private LocalDateTime createdDate;

    @Column(name = "createdby")
    private Integer createdBy;
}
