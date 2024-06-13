package com.atomowyjohny.PolxCommute.repository;

import com.atomowyjohny.PolxCommute.entities.AutobusHybrydowy;
import com.atomowyjohny.PolxCommute.entities.AutobusHybrydowyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutobusHybrydowyRepository extends JpaRepository<AutobusHybrydowy, AutobusHybrydowyId> {
}
