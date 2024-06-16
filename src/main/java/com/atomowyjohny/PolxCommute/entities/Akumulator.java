package com.atomowyjohny.PolxCommute.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

@Getter
@Setter
@Entity
public class Akumulator implements Serializable {
    @Id
    @GeneratedValue(generator = CustomIdGenerator.GENERATOR_NAME)
    @GenericGenerator(name = CustomIdGenerator.GENERATOR_NAME, strategy = "com.atomowyjohny.PolxCommute.entities.CustomIdGenerator")
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