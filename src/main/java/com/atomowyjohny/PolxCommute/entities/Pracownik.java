package com.atomowyjohny.PolxCommute.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "Pracownik")
public class Pracownik {
    @Id
    @GeneratedValue
    @Column(name = "ID_Pracownika", nullable = false)
    private Long id;

    @Column(name = "Imie", nullable = false, length = 32)
    private String imie;

    @Column(name = "Nazwisko", nullable = false, length = 64)
    private String nazwisko;

    @Column(name = "PESEL", nullable = false)
    private Long pesel;

    @Column(name = "Wiek", nullable = false)
    private Byte wiek;

    @Column(name = "Data_Urodzenia", nullable = false)
    private LocalDate dataUrodzenia;

    @Column(name = "Dodatek_Od_Lat_Pracy", nullable = false)
    private Integer dodatekOdLatPracy;

    @Column(name = "Numer_Telefonu")
    private Integer numerTelefonu;

    @Column(name = "Data_Zatrudnienia", nullable = false)
    private LocalDate dataZatrudnienia;

    @Column(name = "Mnoznik_Wyplaty", nullable = false)
    private Double mnoznikWyplaty;

    @Column(name = "Stawka_Bazowa", nullable = false)
    private Integer stawkaBazowa;
}