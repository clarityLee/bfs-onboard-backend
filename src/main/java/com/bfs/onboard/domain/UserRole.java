package com.bfs.onboard.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "userrole")
@NoArgsConstructor
@Getter
@Setter
public class UserRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private User user;

    @Column(name = "roleid")
    private Integer roleID;

    @Column(name = "activeFlag")
    private Boolean activeFlag;

    @Column(name = "createdate")
    private LocalDateTime createDate;

    @Column(name = "modificationdate")
    private LocalDateTime modificationDate;

    @Column(name = "lastmodificationuser")
    private Integer lastModificationUser;
}
