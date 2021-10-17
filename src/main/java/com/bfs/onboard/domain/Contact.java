package com.bfs.onboard.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
@Table(name = "contact")
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @OneToOne
    @JsonManagedReference
    @JoinColumn(name = "personid")
    private Person person;

    @Column(name = "ownerid")
    private Integer ownerId;

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
