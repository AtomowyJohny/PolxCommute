package com.atomowyjohny.PolxCommute.repository;

import com.atomowyjohny.PolxCommute.entities.Pracownik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PracownikRepository extends JpaRepository<Pracownik, Long> {
}
