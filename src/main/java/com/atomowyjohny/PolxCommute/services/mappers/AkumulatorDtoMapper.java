package com.atomowyjohny.PolxCommute.services.mappers;

import com.atomowyjohny.PolxCommute.dto.AkumulatorDTO;
import com.atomowyjohny.PolxCommute.entities.Akumulator;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AkumulatorDtoMapper {
    public static AkumulatorDTO map(Akumulator akumulator) {
        final AkumulatorDTO dto = new AkumulatorDTO();

        dto.setId(akumulator.getId());
        dto.setPojemnosc(akumulator.getPojemnosc());
        dto.setZnamionowaIloscCykli(akumulator.getZnamionowaIloscCykli());
        dto.setIloscCykliRozladowania(akumulator.getIloscCykliRozladowania());
        dto.setPoziomNaladowania(akumulator.getPoziomNaladowania());
        return dto;
    }
}
