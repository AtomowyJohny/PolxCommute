package com.atomowyjohny.PolxCommute.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Autobus_Miejski")
public class AutobusMiejski {
    @Id
    @Column(name = "ID_Autobusu_Miejski", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_Autobusu_Miejski", nullable = false)
    private Autobus autobus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_Miasta", nullable = false)
    private Miasto idMiasta;

    @Column(name = "Oznaczenie_Kursu", nullable = false)
    private Boolean oznaczenieKursu = false;

}