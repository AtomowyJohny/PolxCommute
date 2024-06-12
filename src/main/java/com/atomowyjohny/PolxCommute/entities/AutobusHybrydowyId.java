package com.atomowyjohny.PolxCommute.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class AutobusHybrydowyId implements Serializable {
    @Column(name = "ID_Autobusu_Elektryczny", nullable = false)
    private Long idAutobusuElektryczny;

    @Column(name = "ID_Autobusu_Silnikowy", nullable = false)
    private Long idAutobusuSilnikowy;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AutobusHybrydowyId entity = (AutobusHybrydowyId) o;
        return Objects.equals(this.idAutobusuElektryczny, entity.idAutobusuElektryczny) &&
                Objects.equals(this.idAutobusuSilnikowy, entity.idAutobusuSilnikowy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAutobusuElektryczny, idAutobusuSilnikowy);
    }

}