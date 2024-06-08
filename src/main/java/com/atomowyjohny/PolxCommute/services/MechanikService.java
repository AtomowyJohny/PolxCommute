package com.atomowyjohny.PolxCommute.services;

import com.atomowyjohny.PolxCommute.dto.MechanikDTO;
import com.atomowyjohny.PolxCommute.dto.MechanikDetailsDTO;
import com.atomowyjohny.PolxCommute.entities.Mechanik;
import com.atomowyjohny.PolxCommute.entities.Pracownik;
import com.atomowyjohny.PolxCommute.repository.MechanikRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MechanikService {

    private final MechanikRepository mechanikRepository;

    public MechanikService(MechanikRepository mechanikRepository){
        this.mechanikRepository = mechanikRepository;
    }

    public List<MechanikDTO> findAll(){
        return this.mechanikRepository.findAll().stream().map(this::mapToMechanikDto).toList();
    }

    public Optional<MechanikDetailsDTO> findById(long id) {
        return mechanikRepository.findById(id).map(this::mapToMechanikDetailsDto);
    }

    private MechanikDTO mapToMechanikDto(Mechanik mechanik) {
        final Pracownik pracownik = mechanik.getPracownik();
        final MechanikDTO mechanikDTO = new MechanikDTO();
        mechanikDTO.setId(mechanik.getId());
        mechanikDTO.setName(pracownik.getImie());
        mechanikDTO.setSurname(pracownik.getNazwisko());
        return mechanikDTO;
    }

    private MechanikDetailsDTO mapToMechanikDetailsDto(Mechanik mechanik) {
        final Pracownik pracownik = mechanik.getPracownik();
        final MechanikDetailsDTO mechanikDetailsDTO = new MechanikDetailsDTO();
        mechanikDetailsDTO.setId(mechanik.getId());
        mechanikDetailsDTO.setName(pracownik.getImie());
        mechanikDetailsDTO.setSurname(pracownik.getNazwisko());
        mechanikDetailsDTO.setDataZatrudnienia(pracownik.getDataZatrudnienia());

        mechanikDetailsDTO.setStopienDoswiadczenia(mechanik.getStopienDoswiadczenia());

        return mechanikDetailsDTO;
    }

}
