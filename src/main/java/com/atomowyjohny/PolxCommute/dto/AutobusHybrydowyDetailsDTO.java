package com.atomowyjohny.PolxCommute.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutobusHybrydowyDetailsDTO extends AutobusDetailsDTO{
    private Long autobusElektycznyId;
    private Long autobusSilinikowyId;

    private Integer iloscKoni;
    private Short spalanie;
    private Short pojemnoscZbiornika;
    private String typPaliwa;
    private Short iloscPaliwa;

    private int iloscPakietowZasilajacych;
    private int poziomNaladowania;

    private String typNapeduHybrydowego;
    private Boolean ladowaniePlugIn = false;
}
