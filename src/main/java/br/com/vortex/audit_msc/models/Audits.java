package br.com.vortex.audit_msc.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private Integer id;

    private String name;

    private LocalDate initialDate;

    private LocalDate finalDate;

    private Float onSiteManDays;

    private Float offSiteManDays;

    @ManyToMany
    @JoinTable(
            name = "auditor_audit",
            joinColumns = @JoinColumn(name = "audit_id"),
            inverseJoinColumns = @JoinColumn(name = "auditor_id")
    )
    @JsonIgnoreProperties({
            "standards",
            "street",
            "city",
            "state",
            "postal_code",
            "country",
            "airport",
            "audits"
    })
    private Set<Auditors> auditors = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "standard_audit",
            joinColumns = @JoinColumn(name = "audit_id"),
            inverseJoinColumns = @JoinColumn(name = "standard_id")
    )
    @JsonIgnoreProperties({"standards", "audits","auditors"})
    private Set<Standards> standards = new HashSet<>();

}
