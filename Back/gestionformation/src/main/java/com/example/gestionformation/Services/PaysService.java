package com.example.gestionformation.Services;

import com.example.gestionformation.Entities.Pays;
import com.example.gestionformation.Repositories.ParticipantRepository;
import com.example.gestionformation.Repositories.PaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PaysService {

    private final PaysRepository paysRepository;
    private final ParticipantRepository participantRepository;

    @Autowired
    public PaysService(PaysRepository paysRepository, ParticipantRepository participantRepository) {
        this.paysRepository = paysRepository;
        this.participantRepository = participantRepository;
    }

    public List<Pays> getPays() {
        return paysRepository.findAll();

    }

    public void addPays(Pays pays) {
        Optional<Pays> Optpays = paysRepository.findPaysByNom(pays.getNom());
        if (Optpays.isPresent()){
            throw new IllegalStateException("Pays existe déjà");
        }
        paysRepository.save(pays);
    }

    public void deletePays(Long paysId) {
        paysRepository.findById(paysId);
        boolean exist= paysRepository.existsById(paysId);
        if(!exist){
            throw new IllegalStateException("Pays " + paysId + " n'existe pas");
        }
        Optional<Pays> pays= paysRepository.findById(paysId);
        pays.get().getParticipants().forEach(p->{ p.setPays(null);});
        pays.get().setParticipants(null);
        paysRepository.deleteById(paysId);
    }

    public void updatePays(Long paysId, Pays NewPays) {
        Pays pays = paysRepository.findById(paysId).orElseThrow(()-> new IllegalStateException(
                "Pays " + paysId + " n'existe pas"));
        if (NewPays.getNom()!=null ){
            pays.setNom(NewPays.getNom());
        }
        paysRepository.save(pays);
    }

    /*public void linkNewParticipantToCountry(Long participantId, Long paysId){
        Participant participant = participantRepository.findById(participantId).orElseThrow(()-> new IllegalStateException(
                "participant " + participantId + " n'existe pas"));
        Pays pays = paysRepository.findById(paysId).orElseThrow(()-> new IllegalStateException(
                "Pays " + paysId + " n'existe pas"));
        participant.setPays(pays);
        participantRepository.save(participant);

        Set<Participant> participants = pays.getParticipants();
        participants.add(participant);
        pays.setParticipants(participants);
        paysRepository.save(pays);
    }*/
}

