package com.bfs.onboard.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "visastatus")
@NoArgsConstructor
@Getter
@Setter
public class VisaStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "visatype")
    private String visaType;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "modificationdate")
    private LocalDateTime modificationDate;

    @Column(name = "createuser")
    private Integer createUser;
}
