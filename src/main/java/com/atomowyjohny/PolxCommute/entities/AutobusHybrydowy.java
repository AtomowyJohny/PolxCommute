package com.atomowyjohny.PolxCommute.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Autobus_Hybrydowy")
public class AutobusHybrydowy {
    @EmbeddedId
    private AutobusHybrydowyId id;

    @MapsId("idAutobusuElektryczny")
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ID_Autobusu_Elektryczny", nullable = false)
    private AutobusElektryczny autobusElektryczny;

    @MapsId("idAutobusuSilnikowy")
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ID_Autobusu_Silnikowy", nullable = false)
    private AutobusSilnikowy autobusSilnikowy;

    @Column(name = "Typ_Napedu_Hybrydowego", nullable = false, length = 10)
    private String typNapeduHybrydowego;

    @Column(name = "Ladowanie_Plug_in", nullable = false)
    private Boolean ladowaniePlugIn = false;

}