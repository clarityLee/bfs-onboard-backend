package com.bfs.onboard.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "employee")
@NoArgsConstructor
@Getter
@Setter
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "personid")
    private Integer personId;

    @Column(name = "title")
    private String title;

    @Column(name = "managerid")
    private String managerId;

    @Column(name = "startdate")
    private LocalDate startDate;

    @Column(name = "endDate")
    private LocalDate endDate;

    @Column(name = "avatar")
    private String avartar;

    @Column(name = "car")
    private String car;

    @Column(name = "citizen")
    private Boolean citizen;

    @Column(name = "greencard")
    private Boolean greenCard;

    @Column(name = "visastatusid")
    private LocalDate visaStatusID;

    @Column(name = "visastartdate")
    private LocalDate VisaStartDate;

    @Column(name = "visaenddate")
    private LocalDate visaEndDate;

    @Column(name = "driverlicense")
    private String driverLicense;

    @Column(name = "driverlicense_expirationdate")
    private String driverLicence_ExpirationDate;

    @Column(name = "houseid")
    private Long HouseID;
}
