package com.atomowyjohny.PolxCommute.controllers;

import com.atomowyjohny.PolxCommute.controllers.Responses.GetAcumulatorsResponse;
import com.atomowyjohny.PolxCommute.controllers.Responses.GetAllAutobusRes;
import com.atomowyjohny.PolxCommute.dto.AutobusDTO;
import com.atomowyjohny.PolxCommute.services.AutobusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@RestController
@RequestMapping("bus")
public class AutobusController {
    private final AutobusService autobusService;

    public AutobusController(AutobusService autobusService) {
        this.autobusService = autobusService;
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<GetAllAutobusRes> getAllByMechanikId(@PathVariable Long id) {
        final GetAllAutobusRes response = new GetAllAutobusRes();
        response.setAutobusList(this.autobusService.getAll(id));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<AutobusDTO> getAutobusDetails(@PathVariable Long id) {
        final Optional<AutobusDTO> dto = this.autobusService.getAutobusDetails(id);

        if (dto.isPresent()) {
            return ResponseEntity.ok(dto.get());
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/acc/{id}")
    public ResponseEntity<GetAcumulatorsResponse> getAutobusAcumulators(@PathVariable Long id) {
        try {
            final GetAcumulatorsResponse response = new GetAcumulatorsResponse();
            response.setAcumulators(autobusService.getAutobusAcumulators(id));
            return ResponseEntity.ok(response);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
