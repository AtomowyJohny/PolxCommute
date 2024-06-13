package com.atomowyjohny.PolxCommute.controllers.Responses;

import com.atomowyjohny.PolxCommute.dto.AkumulatorDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class GetAcumulatorsResponse {
    Set<AkumulatorDTO> acumulators;
}
