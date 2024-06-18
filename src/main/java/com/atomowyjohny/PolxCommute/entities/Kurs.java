package com.atomowyjohny.PolxCommute.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Kurs")
public class Kurs {
    @Id
    @Column(name = "ID_Kursu", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_Autobusu", nullable = false)
    private Autobus idAutobusu;

    @Column(name = "Nazwa_Kursu", nullable = false, length = 32)
    private String nazwaKursu;

    @Column(name = "Dlugosc_Trasy", nullable = false)
    private Integer dlugoscTrasy;

    @ManyToMany
    @JoinTable(name = "Przystanek_W_Kursie",
            joinColumns = @JoinColumn(name = "ID_Kursu"),
            inverseJoinColumns = @JoinColumn(name = "ID_Przystanku"))
    private Set<Przystanek> przystanekSet;

}