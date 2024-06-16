package com.atomowyjohny.PolxCommute.services;

import com.atomowyjohny.PolxCommute.controllers.Requests.ChangeAkumulatorReq;
import com.atomowyjohny.PolxCommute.dto.AkumulatorDTO;
import com.atomowyjohny.PolxCommute.dto.AutobusDTO;
import com.atomowyjohny.PolxCommute.entities.Akumulator;
import com.atomowyjohny.PolxCommute.entities.Autobus;
import com.atomowyjohny.PolxCommute.entities.AutobusElektryczny;
import com.atomowyjohny.PolxCommute.entities.Mechanik;
import com.atomowyjohny.PolxCommute.repository.AkumulatorRepository;
import com.atomowyjohny.PolxCommute.repository.AutobusRepository;
import com.atomowyjohny.PolxCommute.repository.MechanikRepository;
import com.atomowyjohny.PolxCommute.services.mappers.AkumulatorDtoMapper;
import com.atomowyjohny.PolxCommute.services.mappers.AutobusDtoMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AutobusService {

    private static final Short NEW_ACUMULATOR_POZIOM_NALADOWANIA = 80;
    private final MechanikRepository mechanikRepository;
    private final AutobusRepository autobusRepository;
    private final AkumulatorRepository akumulatorRepository;

    public AutobusService(final AutobusRepository autobusRepository, final MechanikRepository mechanikRepository, AkumulatorRepository akumulatorRepository) {
        this.autobusRepository = autobusRepository;
        this.mechanikRepository = mechanikRepository;
        this.akumulatorRepository = akumulatorRepository;
    }

    public List<AutobusDTO> getAll(Long mechanikID) {
        final Optional<Mechanik> mechanikOptional = mechanikRepository.findById(mechanikID);
        if (mechanikOptional.isPresent()) {
            final Set<Autobus> autobusSet = mechanikOptional.get().getAutobusSet();
            return autobusSet.stream().map(AutobusDtoMapper::mapToAutobusDTO).toList();
        } else {
            return List.of();
        }
    }

    public Optional<AutobusDTO> getAutobusDetails(Long autobusId) {
        return this.autobusRepository.findById(autobusId).map(AutobusDtoMapper::mapAutobusWithDetails);
    }

    public Set<AkumulatorDTO> getAutobusAcumulators(Long id) throws HttpClientErrorException {
        final Optional<Autobus> autobus = autobusRepository.findById(id);

        if (autobus.isPresent() && Objects.nonNull(autobus.get().getAutobusElektryczny())) {
            final AutobusElektryczny autobusElektryczny = autobus.get().getAutobusElektryczny();
            return autobusElektryczny.getAkumulators().stream().map(AkumulatorDtoMapper::map).collect(Collectors.toSet());
        } else {
            log.info("Autobus nie został znaleziony");
            throw new HttpClientErrorException(HttpStatusCode.valueOf(404));
        }
    }

    @Transactional
    public void changeAutobusAkumulator(ChangeAkumulatorReq req) throws HttpClientErrorException {
        final Optional<Autobus> autobus = autobusRepository.findById(req.getIdAutobusu());
        if (autobus.isPresent()) {
            final AutobusElektryczny autobusElektryczny = autobus.get().getAutobusElektryczny();
            final Optional<Akumulator> akumulatorToRemove = akumulatorRepository.findById(req.getIdAkumulatoraDoWymiany());
            if (Objects.nonNull(autobusElektryczny) && akumulatorToRemove.isPresent()) {
                final Set<Akumulator> akumulatorSet = autobusElektryczny.getAkumulators();
                akumulatorSet.remove(akumulatorToRemove.get());

                final Akumulator nowyAkumulator = new Akumulator();
                nowyAkumulator.setPojemnosc(req.getPojemnosc());
                nowyAkumulator.setZnamionowaIloscCykli(req.getZnamionowaIloscCykli());
                nowyAkumulator.setPoziomNaladowania(NEW_ACUMULATOR_POZIOM_NALADOWANIA);
                nowyAkumulator.setIloscCykliRozladowania(0);
                akumulatorRepository.saveAndFlush(nowyAkumulator);

                akumulatorSet.add(nowyAkumulator);
                autobusElektryczny.setAkumulators(akumulatorSet);

                final Autobus autobusToSave = autobus.get();
                autobusToSave.setAutobusElektryczny(autobusElektryczny);
                autobusRepository.saveAndFlush(autobusToSave);

            } else {
                throw new HttpClientErrorException(HttpStatusCode.valueOf(404));
            }
        } else {
            log.info("Autobus nie został znaleziony");
            throw new HttpClientErrorException(HttpStatusCode.valueOf(404));
        }
    }
}



