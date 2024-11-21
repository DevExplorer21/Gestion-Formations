package com.example.gestionformation.Repositories;

import com.example.gestionformation.Entities.Pays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaysRepository extends JpaRepository<Pays, Long> {
    Optional<Pays> findPaysByNom(String nom);
}
