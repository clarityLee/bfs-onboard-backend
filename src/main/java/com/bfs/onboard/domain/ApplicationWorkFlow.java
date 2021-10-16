package com.bfs.onboard.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "applicationworkflow")
@NoArgsConstructor
@Getter
@Setter
public class ApplicationWorkFlow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "type")
    private String type;

    @Column(name = "status")
    private Integer status;

    @Column(name = "comments")
    private String comments;

    @Column(name = "createdate")
    private LocalDateTime createDate;

    @Column(name = "modificationdate")
    private LocalDateTime modificationDate;

    @OneToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;

}
