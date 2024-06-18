package com.atomowyjohny.PolxCommute.controllers.Responses;

import com.atomowyjohny.PolxCommute.dto.ChargerModeDto;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Builder
@Getter
public class GetAllChargerModesRes {
    private Set<ChargerModeDto> chargerModes;
}
