package com.atomowyjohny.PolxCommute.controllers.Requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeAkumulatorReq {
    private Long idAutobusu;
    private Long idAkumulatoraDoWymiany;
    private Integer pojemnosc;
    private Integer znamionowaIloscCykli;
}
