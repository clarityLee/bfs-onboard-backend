package com.bfs.onboard.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "house")
@NoArgsConstructor
@Getter
@Setter
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "contactid")
    private Integer contactID;

    @Column(name = "address")
    private String address;

    @Column(name = "numberofperson")
    private Integer numberOfPerson;
}
