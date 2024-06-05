package com.atomowyjohny.PolxCommute.controllers.Responses;

import com.atomowyjohny.PolxCommute.dto.MechanikDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class GetAllMechanicRes {

    @Getter
    @Setter
    private List<MechanikDTO> mechanicList;
}
