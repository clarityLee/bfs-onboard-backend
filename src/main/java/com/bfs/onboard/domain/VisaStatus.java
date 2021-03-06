package com.bfs.onboard.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VisaStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    // H1-B, L2, F1(CPT/OPT), H4, Other
    @Column(name = "visatype")
    private String visaType;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "modificationdate")
    private LocalDateTime modificationDate;

    @Column(name = "createuser")
    private Integer createUser;

    @OneToOne(mappedBy = "visaStatus")
    private Employee employee;

    public VisaStatus removeMapping() {
        VisaStatus v = new VisaStatus();
        v.setId(id);
        v.setVisaType(visaType);
        v.setActive(active);
        v.setModificationDate(modificationDate);
        v.setCreateUser(createUser);
        return v;
    }
}
