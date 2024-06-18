package com.atomowyjohny.PolxCommute.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Dyspozytor {
    @Id
    @Column(name = "ID_Dyspozytora", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_Dyspozytora", nullable = false)
    private Pracownik pracownik;

    @ManyToMany
    @JoinTable(name = "Dyspozytor_W_Autobusie",
            joinColumns = @JoinColumn(name = "ID_Dyspozytora"),
            inverseJoinColumns = @JoinColumn(name = "ID_Autobusu"))
    private Set<Autobus> autobuses;

}