package com.bfs.onboard.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "digitaldocument")
@NoArgsConstructor
@Getter
@Setter
public class DigitalDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "type")
    private String type;

    @Column(name = "required")
    private Boolean required;

    @Column(name = "templatelocation")
    private String templateLocation;

    @Column(name = "description")
    private String description;
}
