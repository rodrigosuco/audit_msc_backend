package br.com.vortex.audit_msc.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
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
    @JsonBackReference
    private Set<Auditors> auditors;

}
