package com.bfs.onboard.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "unclaimedfile")
@NoArgsConstructor
@Getter
@Setter
public class UnclaimedFile implements Serializable {

    @Id
    @Column(name = "path", nullable = false)
    private String path;

    @Column(name = "createdate")
    private LocalDateTime createDate;
}
