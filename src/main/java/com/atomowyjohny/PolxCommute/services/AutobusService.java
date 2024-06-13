package com.atomowyjohny.PolxCommute.services;

import com.atomowyjohny.PolxCommute.dto.AkumulatorDTO;
import com.atomowyjohny.PolxCommute.dto.AutobusDTO;
import com.atomowyjohny.PolxCommute.entities.Autobus;
import com.atomowyjohny.PolxCommute.entities.AutobusElektryczny;
import com.atomowyjohny.PolxCommute.entities.Mechanik;
import com.atomowyjohny.PolxCommute.repository.AutobusElektrycznyRepository;
import com.atomowyjohny.PolxCommute.repository.AutobusHybrydowyRepository;
import com.atomowyjohny.PolxCommute.repository.AutobusRepository;
import com.atomowyjohny.PolxCommute.repository.MechanikRepository;
import com.atomowyjohny.PolxCommute.services.mappers.AkumulatorDtoMapper;
import com.atomowyjohny.PolxCommute.services.mappers.AutobusDtoMapper;
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

    private final MechanikRepository mechanikRepository;
    private final AutobusRepository autobusRepository;
    private final AutobusElektrycznyRepository autobusElektrycznyRepository;
    private final AutobusHybrydowyRepository autobusHybrydowyRepository;


    public AutobusService(final AutobusRepository autobusRepository, final MechanikRepository mechanikRepository, final AutobusElektrycznyRepository autobusElektrycznyRepository, final AutobusHybrydowyRepository autobusHybrydowyRepository) {
        this.autobusRepository = autobusRepository;
        this.mechanikRepository = mechanikRepository;
        this.autobusElektrycznyRepository = autobusElektrycznyRepository;
        this.autobusHybrydowyRepository = autobusHybrydowyRepository;
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
}



