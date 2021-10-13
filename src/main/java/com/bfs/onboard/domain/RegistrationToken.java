package com.bfs.onboard.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "registrationtoken")
@NoArgsConstructor
@Getter
@Setter
public class RegistrationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "token")
    private String token;

    @Column(name = "validduration")
    private LocalDateTime validDuration;

    @Column(name = "email")
    private String email;

    @Column(name = "createdby")
    private Integer createdBy;
}
