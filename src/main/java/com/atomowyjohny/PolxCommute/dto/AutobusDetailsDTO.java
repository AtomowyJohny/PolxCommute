package com.atomowyjohny.PolxCommute.dto;

import com.atomowyjohny.PolxCommute.enums.AutobusType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutobusDetailsDTO {
    private Long id;
    private AutobusType autobusType;
}
