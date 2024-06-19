package br.com.vortex.audit_msc.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "audits")
@Getter
@Setter
public class Audits {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String activity_name;
    private LocalDate initial_date;
    private LocalDate final_date;
    private Float onsite_days;
    private Float offsite_days;

    @ManyToMany
    @JoinTable(
            name = "audit_auditors",
            joinColumns = @JoinColumn(name = "auditor_id"),
            inverseJoinColumns = @JoinColumn(name = "audit_id")
    )
    @JsonIgnoreProperties("audits")
    private Set<Auditors> auditors = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "standard_id")
    @JsonIgnoreProperties("audits")
    private Standards standard;

}
