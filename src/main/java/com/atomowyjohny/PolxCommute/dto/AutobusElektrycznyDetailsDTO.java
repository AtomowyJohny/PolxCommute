package com.atomowyjohny.PolxCommute.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutobusElektrycznyDetailsDTO extends AutobusDetailsDTO {
    private int iloscPakietowZasilajacych;
    private int poziomNaladowania;
}
