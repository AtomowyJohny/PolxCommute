package com.atomowyjohny.PolxCommute.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
public class Autobus {
    @Id
    @Column(name = "ID_Autobusu", nullable = false)
    private Long id;

    @Column(name = "Przebieg", nullable = false)
    private Integer przebieg;

    @Column(name = "Rok_Produkcji", nullable = false)
    private Integer rokProdukcji;

    @Column(name = "Model", nullable = false, length = 64)
    private String model;

    @Column(name = "Moc_Netto", nullable = false)
    private Integer mocNetto;

    @Column(name = "Zasieg", nullable = false)
    private Integer zasieg;

    @Column(name = "Ilosc_Miejsc", nullable = false)
    private Integer iloscMiejsc;

    @OneToOne(mappedBy = "autobus")
    private AutobusElektryczny autobusElektryczny;

    @OneToOne(mappedBy = "autobus")
    private AutobusSilnikowy autobusSilnikowy;

    @ManyToMany
    @JoinTable(
            name = "Mechanik_W_Autobusie",
            joinColumns = @JoinColumn(name = "ID_Autobusu"),
            inverseJoinColumns = @JoinColumn(name = "ID_Mechanika")
    )
    private Set<Mechanik> mechanikSet;
}