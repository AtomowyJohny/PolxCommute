package com.atomowyjohny.PolxCommute.controllers.Responses;

import com.atomowyjohny.PolxCommute.dto.AutobusDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetAllAutobusRes {
    List<AutobusDTO> autobusList;
}
