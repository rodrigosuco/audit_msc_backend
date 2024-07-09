package br.com.vortex.audit_msc.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "companies")
@Getter
@Setter
public class Companies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String address;
    private String cnpj;
    private String contact_name;
    private String contact_number;
    private String scope;
    private Boolean is_intercompany;
    private Boolean is_active;
    private String standard_code;

    @ManyToMany
    @JoinTable(
            name = "company_standards",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "standard_id")
    )
    @JsonIgnoreProperties("auditors")
    private Set<Standards> standards = new HashSet<>();
}