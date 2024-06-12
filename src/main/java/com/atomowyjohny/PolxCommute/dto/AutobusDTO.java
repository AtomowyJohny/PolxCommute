package com.atomowyjohny.PolxCommute.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AutobusDTO {
    private Long id;
    private String model;

    private int rokProdukcji;
    private int mocNetto;
    private int zasieg;
    private int przebieg;
    private int iloscMiejsc;

    private AutobusDetailsDTO details;
}
