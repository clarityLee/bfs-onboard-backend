package com.bfs.onboard.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "facilityreport")
@NoArgsConstructor
@Getter
@Setter
public class FacilityReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "employeeid")
    private Integer employeeID;

    @Column(name = "reportdate")
    private LocalDateTime reportDate;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;
}
