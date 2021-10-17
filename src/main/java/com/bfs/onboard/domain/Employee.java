package com.bfs.onboard.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "personid")
    private Person person;

    @Column(name = "title")
    private String title;

    @Column(name = "managerid")
    private String managerId;

    @Column(name = "startdate")
    private LocalDate startDate;

    @Column(name = "endDate")
    private LocalDate endDate;

    @Column(name = "avartar")
    private String avartar;

    @Column(name = "car")
    private String car;

    @Column(name = "citizen")
    private Boolean citizen;

    @Column(name = "greencard")
    private Boolean greenCard;

    @OneToOne
    @JoinColumn(name = "visastatusid")
    private VisaStatus visaStatus;

    @Column(name = "visastartdate")
    private LocalDate visaStartDate;

    @Column(name = "visaenddate")
    private LocalDate visaEndDate;

    @Column(name = "driverlicense")
    private String driverLicense;

    @Column(name = "driverlicense_expirationdate")
    private LocalDate driverLicence_ExpirationDate;

    @Column(name = "houseid")
    private Long houseID;

    @Column(name = "active")
    private Boolean active = false;

    @Column(name = "workemail")
    private String workEmail;

    @Column(name = "workphone")
    private String workPhone;

    @OneToOne(mappedBy = "employee")
    private ApplicationWorkFlow applicationWorkFlow;

    public Employee removeMapping() {
        Employee e = new Employee();
        e.setId(id);
        e.setTitle(title);
        e.setManagerId(managerId);
        e.setStartDate(startDate);
        e.setEndDate(endDate);
        e.setAvartar(avartar);
        e.setCar(car);
        e.setCitizen(citizen);
        e.setGreenCard(greenCard);
        e.setVisaStartDate(visaStartDate);
        e.setVisaEndDate(visaEndDate);
        e.setDriverLicense(driverLicense);
        e.setDriverLicence_ExpirationDate(driverLicence_ExpirationDate);
        e.setHouseID(houseID);
        e.setActive(active);
        e.setWorkPhone(workPhone);
        e.setWorkPhone(workPhone);
        return e;
    }
}
