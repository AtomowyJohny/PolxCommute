package com.atomowyjohny.PolxCommute.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Mechanik")
public class Mechanik {
    @Id
    @GeneratedValue
    @Column(name = "ID_Mechanika", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_Mechanika", nullable = false)
    private Pracownik pracownik;

    @Column(name = "Stopien_Doswiadczenia", nullable = false)
    private Short stopienDoswiadczenia;

    @Column(name = "Dodatek_Do_Premii_Za_Doswiadczenie", nullable = false)
    private Short dodatekDoPremiiZaDoswiadczenie;

    @Column(name = "Premia", nullable = false)
    private Integer premia;

    @ManyToMany
    @JoinTable(
            name = "Mechanik_W_Autobusie",
            joinColumns = @JoinColumn(name = "ID_Mechanika"),
            inverseJoinColumns = @JoinColumn(name = "ID_Autobusu")
    )
    private Set<Autobus> autobusSet;

}