package com.atomowyjohny.PolxCommute.repository;

import com.atomowyjohny.PolxCommute.entities.Akumulator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AkumulatorRepository extends JpaRepository<Akumulator, Long> {
}
