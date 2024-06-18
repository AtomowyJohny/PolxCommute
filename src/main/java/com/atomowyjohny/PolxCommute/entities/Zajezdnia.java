package com.atomowyjohny.PolxCommute.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Zajezdnia {
    @Id
    @Column(name = "ID_Zajezdni", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_Zajezdni", nullable = false)
    private Przystanek przystanek;

    @Column(name = "Rodzaj_Zajezdni", nullable = false, length = 16)
    private String rodzajZajezdni;

    @OneToMany(mappedBy = "idZajezdni")
    private Set<PracownikWZajezdni> pracownikWZajezdnis;

}