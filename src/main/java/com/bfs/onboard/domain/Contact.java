package com.bfs.onboard.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "contact")
@NoArgsConstructor
@Getter
@Setter
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "personid")
    private Integer personID;

    @Column(name = "relationship")
    private String relationship;

    @Column(name = "title")
    private String title;

    @Column(name = "isreference")
    private Boolean isReference = false;

    @Column(name = "isemergency")
    private Boolean isEmergency = false;

    @Column(name = "islandlord")
    private Boolean isLandlord = false;
}
