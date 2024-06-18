package com.atomowyjohny.PolxCommute.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Przystanek {
    @Id
    @Column(name = "ID_Przystanku", nullable = false)
    private Long id;

    @Column(name = "Nazwa", nullable = false, length = 64)
    private String nazwa;

    @Column(name = "Numer", nullable = false)
    private Integer numer;

    @Column(name = "Adres", nullable = false, length = 128)
    private String adres;

    @ManyToMany(mappedBy = "przystanekSet")
    private Set<Kurs> kurs;

    @OneToOne(mappedBy = "przystanek")
    private Zajezdnia zajezdnia;

}