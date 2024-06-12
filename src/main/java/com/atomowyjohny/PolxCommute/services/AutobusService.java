package com.atomowyjohny.PolxCommute.services;

import com.atomowyjohny.PolxCommute.dto.AutobusDTO;
import com.atomowyjohny.PolxCommute.entities.Autobus;
import com.atomowyjohny.PolxCommute.entities.Mechanik;
import com.atomowyjohny.PolxCommute.repository.AutobusRepository;
import com.atomowyjohny.PolxCommute.repository.MechanikRepository;
import com.atomowyjohny.PolxCommute.services.mappers.AutobusDtoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AutobusService {

    private final MechanikRepository mechanikRepository;
    private final AutobusRepository autobusRepository;

    public AutobusService(AutobusRepository autobusRepository, MechanikRepository mechanikRepository) {
        this.autobusRepository = autobusRepository;
        this.mechanikRepository = mechanikRepository;
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

    public Optional<AutobusDTO> getAutobusElektrycznyDetails(Long autobusId) {
        return this.autobusRepository.findById(autobusId).map(AutobusDtoMapper::mapAutobusWithDetails);
    }

}
