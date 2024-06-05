package com.atomowyjohny.PolxCommute.controllers;

import com.atomowyjohny.PolxCommute.controllers.Responses.GetAllMechanicRes;
import com.atomowyjohny.PolxCommute.services.MechanikService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/mechanic")
public class MechanikController {
    private final MechanikService mechanikService;

    public MechanikController(MechanikService mechanikService) {
        this.mechanikService = mechanikService;
    }

    @GetMapping("/all")
    public GetAllMechanicRes getAllMechanics() {
        GetAllMechanicRes response = new GetAllMechanicRes();
        response.setMechanicList(this.mechanikService.findAll());
        return response;
    }
}
