package com.atomowyjohny.PolxCommute.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AkumulatorDTO {
    private Long id;
    private Integer pojemnosc;
    private Integer znamionowaIloscCykli;
    private Integer iloscCykliRozladowania;
    private Short poziomNaladowania;
}
