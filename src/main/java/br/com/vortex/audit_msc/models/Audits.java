package br.com.vortex.audit_msc.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Audits {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate initialDate;

    private LocalDate finalDate;

    private Double onSiteManDays;

    private Double offSiteManDays;

    @ManyToMany
    @JoinTable(
            name = "auditor_audit",
            joinColumns = @JoinColumn(name = "audit_id"),
            inverseJoinColumns = @JoinColumn(name = "auditor_id")
    )
    private Set<Auditors> auditors = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "standard_audit",
            joinColumns = @JoinColumn(name = "audit_id"),
            inverseJoinColumns = @JoinColumn(name = "standard_id")
    )
    private Set<Standards> standards = new HashSet<>();

    @Transient
    private Long auditorIds;

    @Transient
    private Long standardId;
}
