package com.atomowyjohny.PolxCommute.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutobusSilnikowyDetailsDTO extends AutobusDetailsDTO {
    private Integer iloscKoni;
    private Short spalanie;
    private Short pojemnoscZbiornika;
    private String typPaliwa;
    private Short iloscPaliwa;
}
