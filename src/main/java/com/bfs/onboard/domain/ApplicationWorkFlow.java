package com.bfs.onboard.domain;

import com.bfs.onboard.constant.AppWorkStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
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

    @Transient
    private String statusStr;
    public String getStatusStr() {
        return AppWorkStatus.statusToString(status);
    }

    public ApplicationWorkFlow removeMapping() {
        ApplicationWorkFlow a = new ApplicationWorkFlow();
        a.setId(id);
        a.setType(type);
        a.setStatus(status);
        a.setComments(comments);
        a.setCreateDate(createDate);
        a.setModificationDate(modificationDate);
        return a;
    }
}
