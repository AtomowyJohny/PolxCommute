package com.atomowyjohny.PolxCommute.repository;

import com.atomowyjohny.PolxCommute.entities.Mechanik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MechanikRepository extends JpaRepository<Mechanik, Long> {
}
