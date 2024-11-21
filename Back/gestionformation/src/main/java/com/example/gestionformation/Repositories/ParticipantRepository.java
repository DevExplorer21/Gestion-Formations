package com.example.gestionformation.Repositories;

import com.example.gestionformation.Entities.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    Optional<Participant> findParticipantByEmail(String email);

}
