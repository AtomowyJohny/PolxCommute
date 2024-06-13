package com.atomowyjohny.PolxCommute.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Akumulator {
    @Id
    @Column(name = "ID_Akumulatora", nullable = false)
    private Long id;

    @Column(name = "Pojemnosc", nullable = false)
    private Integer pojemnosc;

    @Column(name = "Znamionowa_Ilosc_Cykli", nullable = false)
    private Integer znamionowaIloscCykli;

    @Column(name = "Ilosc_Cykli_Rozladowania", nullable = false)
    private Integer iloscCykliRozladowania;

    @Column(name = "Poziom_Naladowania", nullable = false)
    private Short poziomNaladowania;
}