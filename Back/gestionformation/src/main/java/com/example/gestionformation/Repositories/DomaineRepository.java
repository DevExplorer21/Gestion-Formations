package com.example.gestionformation.Repositories;

import com.example.gestionformation.Entities.Domaine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DomaineRepository extends JpaRepository<Domaine, Long> {
    Optional<Domaine> findDomaineByLibelle(String libelle);
}
