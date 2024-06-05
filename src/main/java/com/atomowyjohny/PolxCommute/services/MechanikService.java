package com.atomowyjohny.PolxCommute.services;

import com.atomowyjohny.PolxCommute.dto.MechanikDTO;
import com.atomowyjohny.PolxCommute.entities.Mechanik;
import com.atomowyjohny.PolxCommute.entities.Pracownik;
import com.atomowyjohny.PolxCommute.repository.MechanikRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MechanikService {

    private final MechanikRepository mechanikRepository;

    public MechanikService(MechanikRepository mechanikRepository){
        this.mechanikRepository = mechanikRepository;
    }

    public List<MechanikDTO> findAll(){
        return this.mechanikRepository.findAll().stream().map(this::mapToMechanikDto).toList();
    }

    private MechanikDTO mapToMechanikDto(Mechanik mechanik) {
        final Pracownik pracownik = mechanik.getPracownik();
        final MechanikDTO mechanikDTO = new MechanikDTO();
        mechanikDTO.setId(mechanik.getId());
        mechanikDTO.setName(pracownik.getImie());
        mechanikDTO.setSurname(pracownik.getNazwisko());
        return mechanikDTO;
    }

}
