package com.bfs.onboard.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "person")
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "middlename")
    private String middleName;

    @Column(name = "preferredname")
    private String preferredName;

    @Column(name = "email")
    private String email;

    @Column(name = "cellphone")
    private String cellPhone;

    @Column(name = "alternatephone")
    private String alternatePhone;

    @Column(name = "gender")
    private String gender;

    @Column(name = "ssn")
    private String ssn;

    @Column(name = "dateofbirth")
    private LocalDate dateOfBirth;

    @OneToOne(mappedBy = "person")
    private Employee employee;

    @OneToOne(mappedBy = "person")
    private User user;

    @OneToOne(mappedBy = "person")
    @JsonBackReference
    private Contact contact;

    @OneToMany(mappedBy = "person")
    private List<Address> addresses = new ArrayList<>();

    public Person removeMapping() {
        Person p = new Person();
        p.setId(id);
        p.setFirstName(firstName);
        p.setLastName(lastName);
        p.setMiddleName(middleName);
        p.setPreferredName(preferredName);
        p.setEmail(email);
        p.setCellPhone(cellPhone);
        p.setAlternatePhone(alternatePhone);
        p.setGender(gender);
        p.setSsn(ssn);
        p.setDateOfBirth(dateOfBirth);
        return p;
    }
}
