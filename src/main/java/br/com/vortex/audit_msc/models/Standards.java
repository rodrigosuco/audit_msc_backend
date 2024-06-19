package br.com.vortex.audit_msc.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "standards")
@Getter
@Setter

public class Standards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @ManyToMany(mappedBy = "standards")
    @JsonIgnoreProperties({
            "standards",
            "street",
            "city",
            "state",
            "postal_code",
            "country",
            "airport"
    })
    private Set<Auditors> auditors;

    @OneToMany(mappedBy = "standards")
    @JsonIgnoreProperties("standards")
    private Set<Audits> audits = new HashSet<>();

}
