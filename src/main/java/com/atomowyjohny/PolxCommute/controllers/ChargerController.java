package com.atomowyjohny.PolxCommute.controllers;

import com.atomowyjohny.PolxCommute.controllers.Responses.ChargeActionRes;
import com.atomowyjohny.PolxCommute.controllers.Responses.GetAllChargerModesRes;
import com.atomowyjohny.PolxCommute.services.ChargerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("charger")
public class ChargerController {
    private final ChargerService chargerService;

    public ChargerController(final ChargerService chargerService) {
        this.chargerService = chargerService;
    }

    @GetMapping("modes")
    public ResponseEntity<GetAllChargerModesRes> getAllChargerModeRes() {
        return ResponseEntity.ok(GetAllChargerModesRes.builder().chargerModes(chargerService.getChargerModes()).build());
    }

    @PatchMapping("{id}/action-charger/{modeId}")
    public ResponseEntity<ChargeActionRes> charge(@PathVariable Long id, @PathVariable int modeId) {
        return ResponseEntity.ok(ChargeActionRes.builder().chargeLevel(chargerService.chargerAction(id, modeId)).build());
    }
}
