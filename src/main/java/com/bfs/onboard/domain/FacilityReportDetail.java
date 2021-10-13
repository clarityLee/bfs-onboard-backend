package com.bfs.onboard.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "facilityreportdetail")
@NoArgsConstructor
@Getter
@Setter
public class FacilityReportDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "reportid")
    private Integer reportID;

    @Column(name = "employeeid")
    private Integer employeeID;

    @Column(name = "comments")
    private String comments;

    @Column(name = "createddate")
    private LocalDateTime createdDate;

    @Column(name = "lastmodificationdate")
    private LocalDateTime lastModificationDate;
}
