package com.atomowyjohny.PolxCommute.repository;

import com.atomowyjohny.PolxCommute.entities.AutobusElektryczny;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutobusElektrycznyRepository extends JpaRepository<AutobusElektryczny, Long> {
}
