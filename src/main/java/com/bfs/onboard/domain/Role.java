package com.bfs.onboard.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role")
@NoArgsConstructor
@Getter
@Setter
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "rolename")
    private String roleName;

    @Column(name = "description")
    private String description;

    @Column(name = "createdate")
    private LocalDateTime createDate;

    @Column(name = "modificationdate")
    private LocalDateTime modificationDate;

    @Column(name = "lastmodificationuser")
    private Integer lastModificationUser;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private List<RolePermission> rolePermissions;

    @Transient
    private List<String> permissions = new ArrayList<>();
}
