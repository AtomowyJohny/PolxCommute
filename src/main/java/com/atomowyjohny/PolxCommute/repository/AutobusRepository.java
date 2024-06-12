package com.atomowyjohny.PolxCommute.repository;

import com.atomowyjohny.PolxCommute.entities.Autobus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutobusRepository extends JpaRepository<Autobus , Long> {
}
