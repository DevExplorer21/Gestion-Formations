package com.example.gestionformation.Repositories;

import com.example.gestionformation.Entities.Formateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FormateurRepository extends JpaRepository<Formateur, Long> {
    Optional<Formateur> findFormateurByEmail(String email);
    Optional<Formateur> findById (Long id);
}
