package com.atomowyjohny.PolxCommute.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Kierowca {
    @Id
    @Column(name = "ID_Kierowcy", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_Kierowcy", nullable = false)
    private Pracownik pracownik;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_Autobusu", nullable = false)
    private Autobus idAutobusu;

    @Column(name = "Lata_Doswiadczenia", nullable = false)
    private Short lataDoswiadczenia;

    @Column(name = "Typ_Prawa_Jazdy", nullable = false, length = 2)
    private String typPrawaJazdy;

    @Column(name = "Kara_Za_Opoznienia", nullable = false)
    private Short karaZaOpoznienia;

    @Column(name = "Dodatek_Za_Przydzielone_Kursy", nullable = false)
    private Double dodatekZaPrzydzieloneKursy;

}