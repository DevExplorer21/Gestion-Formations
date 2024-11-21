package com.example.gestionformation.Repositories;

import com.example.gestionformation.Entities.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface FormationRepository extends JpaRepository<Formation, Long> {
    Optional<Formation> findFormationByTitre(String titre);
}
