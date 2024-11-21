package com.example.gestionformation.Repositories;

import com.example.gestionformation.Entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findProfileByLibelle(String libelle);

}
