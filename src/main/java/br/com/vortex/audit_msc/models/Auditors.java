package br.com.vortex.audit_msc.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Entity
@Table(name = "auditors")
@Getter
@Setter
public class Auditors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String street;
    private String city;
    private String state;
    private String postal_code;
    private String country;
    private String airport;

    @ManyToMany
    @JoinTable(
            name = "auditor_standard",
            joinColumns = @JoinColumn(name = "auditor_id"),
            inverseJoinColumns = @JoinColumn(name = "standard_id")
    )

    @JsonIgnoreProperties("auditors")
    private Set<Standards> standards;

}
