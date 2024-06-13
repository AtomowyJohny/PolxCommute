package com.atomowyjohny.PolxCommute.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Autobus_Elektryczny")
public class AutobusElektryczny {
    @Id
    @Column(name = "ID_Autobusu_Elektryczny", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ID_Autobusu_Elektryczny", nullable = false)
    private Autobus autobus;

    @Column(name = "Ilosc_Pakietow_Zasilajacych", nullable = false)
    private Integer iloscPakietowZasilajacych;

    @Column(name = "Poziom_Naladowania", nullable = false)
    private Integer poziomNaladowania;

    @OneToOne(mappedBy = "autobusElektryczny")
    private AutobusHybrydowy autobusHybrydowy;

    @ManyToMany
    @JoinTable(name = "Akumulatory_Autobusu",
            joinColumns = @JoinColumn(name = "ID_Autobusu_Elektryczny"),
            inverseJoinColumns = @JoinColumn(name = "ID_Akumulatora"))
    private Set<Akumulator> akumulators = new LinkedHashSet<>();


}