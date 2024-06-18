package com.atomowyjohny.PolxCommute.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Pracownik_W_Zajezdni")
public class PracownikWZajezdni {
    @EmbeddedId
    private PracownikWZajezdniId id;

    @MapsId("idPracownika")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_Pracownika", nullable = false)
    private Pracownik idPracownika;

    @MapsId("idZajezdni")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_Zajezdni", nullable = false)
    private Zajezdnia idZajezdni;

    @Column(name = "Data_Od", nullable = false)
    private LocalDate dataOd;

    @Column(name = "Data_Do", nullable = false)
    private LocalDate dataDo;

}