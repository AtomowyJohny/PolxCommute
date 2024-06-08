package com.atomowyjohny.PolxCommute.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class MechanikDetailsDTO {
    private long id;
    private String name;
    private String surname;
    private LocalDate dataZatrudnienia;
    private int stopienDoswiadczenia;
}
