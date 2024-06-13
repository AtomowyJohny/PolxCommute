package com.atomowyjohny.PolxCommute.services.mappers;

import com.atomowyjohny.PolxCommute.dto.AutobusDTO;
import com.atomowyjohny.PolxCommute.dto.AutobusElektrycznyDetailsDTO;
import com.atomowyjohny.PolxCommute.dto.AutobusHybrydowyDetailsDTO;
import com.atomowyjohny.PolxCommute.dto.AutobusSilnikowyDetailsDTO;
import com.atomowyjohny.PolxCommute.entities.Autobus;
import com.atomowyjohny.PolxCommute.entities.AutobusElektryczny;
import com.atomowyjohny.PolxCommute.entities.AutobusHybrydowy;
import com.atomowyjohny.PolxCommute.entities.AutobusSilnikowy;
import com.atomowyjohny.PolxCommute.enums.AutobusType;
import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class AutobusDtoMapper {
    public static AutobusDTO mapAutobusWithDetails(Autobus autobus) {
        final AutobusDTO dto = new AutobusDTO();
        dto.setId(autobus.getId());
        dto.setModel(autobus.getModel());
        dto.setRokProdukcji(autobus.getRokProdukcji());
        dto.setMocNetto(autobus.getMocNetto());
        dto.setZasieg(autobus.getZasieg());
        dto.setPrzebieg(autobus.getPrzebieg());
        dto.setIloscMiejsc(autobus.getIloscMiejsc());

        final AutobusElektryczny autobusElektryczny = autobus.getAutobusElektryczny();
        final AutobusSilnikowy autobusSilnikowy = autobus.getAutobusSilnikowy();

        if(Objects.nonNull(autobusElektryczny) && Objects.nonNull(autobusSilnikowy)) {
            final AutobusHybrydowy autobusHybrydowy = autobusElektryczny.getAutobusHybrydowy();
            if (Objects.nonNull(autobusHybrydowy)) {
                dto.setDetails(mapAutobusHybrydowy(autobusHybrydowy));
                return dto;
            }
        }

        if (Objects.nonNull(autobusElektryczny)) {
            dto.setDetails(mapAutobusElektryczny(autobusElektryczny));
            return dto;
        }

        if (Objects.nonNull(autobusSilnikowy)) {
            dto.setDetails(mapAutobusSilnikowy(autobusSilnikowy));
            return dto;
        }

        return dto;
    }

    private static AutobusHybrydowyDetailsDTO mapAutobusHybrydowy(AutobusHybrydowy autobusHybrydowy) {
        final AutobusHybrydowyDetailsDTO detailsDTO = new AutobusHybrydowyDetailsDTO();

        final AutobusElektryczny autobusElektryczny = autobusHybrydowy.getAutobusElektryczny();
        final AutobusSilnikowy autobusSilnikowy = autobusHybrydowy.getAutobusSilnikowy();

        detailsDTO.setAutobusSilinikowyId(autobusSilnikowy.getId());
        detailsDTO.setAutobusElektycznyId(autobusElektryczny.getId());

        detailsDTO.setAutobusType(AutobusType.HYBRYDOWY);
        detailsDTO.setSpalanie(autobusSilnikowy.getSpalanie());
        detailsDTO.setIloscKoni(autobusSilnikowy.getIloscKoni());
        detailsDTO.setPojemnoscZbiornika(autobusSilnikowy.getPojemnoscZbiornika());
        detailsDTO.setTypPaliwa(autobusSilnikowy.getTypPaliwa());
        detailsDTO.setIloscPaliwa(autobusSilnikowy.getIloscPaliwa());
        detailsDTO.setPoziomNaladowania(autobusElektryczny.getPoziomNaladowania());
        detailsDTO.setIloscPakietowZasilajacych(autobusElektryczny.getIloscPakietowZasilajacych());

        detailsDTO.setTypNapeduHybrydowego(autobusHybrydowy.getTypNapeduHybrydowego());
        detailsDTO.setLadowaniePlugIn(autobusHybrydowy.getLadowaniePlugIn());
        return detailsDTO;
    }

    private static AutobusSilnikowyDetailsDTO mapAutobusSilnikowy(AutobusSilnikowy autobusSilnikowy) {
        final AutobusSilnikowyDetailsDTO detailsDTO = new AutobusSilnikowyDetailsDTO();
        detailsDTO.setAutobusType(AutobusType.SPALINOWY);
        detailsDTO.setId(autobusSilnikowy.getId());
        detailsDTO.setSpalanie(autobusSilnikowy.getSpalanie());
        detailsDTO.setIloscKoni(autobusSilnikowy.getIloscKoni());
        detailsDTO.setPojemnoscZbiornika(autobusSilnikowy.getPojemnoscZbiornika());
        detailsDTO.setTypPaliwa(autobusSilnikowy.getTypPaliwa());
        detailsDTO.setIloscPaliwa(autobusSilnikowy.getIloscPaliwa());
        return detailsDTO;
    }

    private static AutobusElektrycznyDetailsDTO mapAutobusElektryczny(AutobusElektryczny autobusElektryczny) {
        final AutobusElektrycznyDetailsDTO detailsDTO = new AutobusElektrycznyDetailsDTO();
        detailsDTO.setAutobusType(AutobusType.ELEKTRYCZNY);
        detailsDTO.setId(autobusElektryczny.getId());
        detailsDTO.setPoziomNaladowania(autobusElektryczny.getPoziomNaladowania());
        detailsDTO.setIloscPakietowZasilajacych(autobusElektryczny.getIloscPakietowZasilajacych());
        return detailsDTO;
    }


    public static AutobusDTO mapToAutobusDTO(Autobus autobus) {
        final AutobusDTO dto = new AutobusDTO();
        dto.setId(autobus.getId());
        dto.setModel(autobus.getModel());
        return dto;
    }
}

