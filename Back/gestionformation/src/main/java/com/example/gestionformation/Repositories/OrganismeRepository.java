package com.example.gestionformation.Repositories;

import com.example.gestionformation.Entities.Organisme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganismeRepository extends JpaRepository<Organisme, Long> {
    Optional<Organisme> findOrganismeByLibelle(String libelle);
}
