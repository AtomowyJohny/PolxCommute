package com.atomowyjohny.PolxCommute.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
public class Miasto {
    @Id
    @Column(name = "ID_Miasta", nullable = false)
    private Long id;

    @Column(name = "Nazwa", nullable = false, length = 256)
    private String nazwa;

    @OneToMany(mappedBy = "idMiasta")
    private Set<AutobusMiejski> autobusMiejskis;

    @ManyToMany(mappedBy = "cities")
    private Set<AutobusMiedzymiastowy> autobusMiedzymiastowySet;

}