package com.atomowyjohny.PolxCommute.services;

import com.atomowyjohny.PolxCommute.dto.ChargerModeDto;
import com.atomowyjohny.PolxCommute.entities.Akumulator;
import com.atomowyjohny.PolxCommute.enums.ChargerMode;
import com.atomowyjohny.PolxCommute.repository.AkumulatorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ChargerService {

    private final AkumulatorRepository akumulatorRepository;

    public ChargerService(AkumulatorRepository akumulatorRepository) {
        this.akumulatorRepository = akumulatorRepository;
    }

    private static ChargerModeDto mapToChargerModeDto(ChargerMode chargerMode) {
        final ChargerModeDto chargerModeDto = new ChargerModeDto();
        chargerModeDto.setValue(chargerMode.getValue());

        switch (chargerMode) {
            case STORAGE -> {
                chargerModeDto.setLabel("Przechowywanie");
            }
            case CHARGING -> {
                chargerModeDto.setLabel("Ładowanie");
            }
            case DISCHARGING -> {
                chargerModeDto.setLabel("Rozładowanie");
            }
            default -> log.warn("Unknown charger mode!");
        }

        return chargerModeDto;
    }

    public Set<ChargerModeDto> getChargerModes() {
        return Arrays.stream(ChargerMode.values()).map(ChargerService::mapToChargerModeDto).collect(Collectors.toSet());
    }

    public int chargerAction(Long idAkumulator, int mode) {
        switch (ChargerMode.fromInt(mode)) {
            case CHARGING -> {
                return setAkumulatorCharge(idAkumulator, 100);
            }
            case DISCHARGING -> {
                return setAkumulatorCharge(idAkumulator, 0);
            }
            case STORAGE -> {
                return setAkumulatorCharge(idAkumulator, 80);
            }
            case null -> throw new HttpClientErrorException(HttpStatusCode.valueOf(404));
        }
    }

    private int setAkumulatorCharge(Long idAkumulator, int poziomNaladowania) {
        final Optional<Akumulator> akumulator = akumulatorRepository.findById(idAkumulator);
        if (akumulator.isPresent()) {
            final Akumulator entity = akumulator.get();
            entity.setPoziomNaladowania((short) poziomNaladowania);
            akumulatorRepository.saveAndFlush(entity);
        } else {
            log.info("Akumulator nie został znaleziony");
            throw new HttpClientErrorException(HttpStatusCode.valueOf(404));
        }

        return poziomNaladowania;
    }
}
