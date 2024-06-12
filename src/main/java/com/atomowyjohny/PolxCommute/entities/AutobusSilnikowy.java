package com.atomowyjohny.PolxCommute.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Autobus_Silnikowy")
public class AutobusSilnikowy {
    @Id
    @Column(name = "ID_Autobusu_Silnikowy", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ID_Autobusu_Silnikowy", nullable = false)
    private Autobus autobus;

    @Column(name = "Ilosc_Koni", nullable = false)
    private Integer iloscKoni;

    @Column(name = "Spalanie", nullable = false)
    private Short spalanie;

    @Column(name = "Pojemnosc_Zbiornika", nullable = false)
    private Short pojemnoscZbiornika;

    @Column(name = "Typ_Paliwa", nullable = false, length = 32)
    private String typPaliwa;

    @Column(name = "Ilosc_Paliwa", nullable = false)
    private Short iloscPaliwa;

    @OneToOne(mappedBy = "autobusSilnikowy")
    private AutobusHybrydowy autobusHybrydowy;

}