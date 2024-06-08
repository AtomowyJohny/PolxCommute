package com.atomowyjohny.PolxCommute.controllers;

import com.atomowyjohny.PolxCommute.controllers.Responses.GetAllMechanicRes;
import com.atomowyjohny.PolxCommute.dto.MechanikDetailsDTO;
import com.atomowyjohny.PolxCommute.services.MechanikService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController()
@RequestMapping("mechanic")
public class MechanikController {
    private final MechanikService mechanikService;

    public MechanikController(MechanikService mechanikService) {
        this.mechanikService = mechanikService;
    }

    @GetMapping("/all")
    public ResponseEntity<GetAllMechanicRes> getAllMechanics() {
        GetAllMechanicRes response = new GetAllMechanicRes();
        response.setMechanicList(this.mechanikService.findAll());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MechanikDetailsDTO> getMechanikDetails(@PathVariable int id) {
        final Optional<MechanikDetailsDTO> dto = this.mechanikService.findById(id);

        if (dto.isPresent()) {
            return ResponseEntity.ok(dto.get());
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
