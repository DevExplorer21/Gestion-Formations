package com.example.gestionformation.Services;


import com.example.gestionformation.Entities.Participant;
import com.example.gestionformation.Entities.Session;
import com.example.gestionformation.Repositories.ParticipantRepository;
import com.example.gestionformation.Repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ParticipantService {
    private final ParticipantRepository participantRepository;
    private final SessionRepository sessionRepository;

    @Autowired
    public ParticipantService(ParticipantRepository participantRepository, SessionRepository sessionRepository) {
        this.participantRepository = participantRepository;
        this.sessionRepository = sessionRepository;
    }
    public List<Participant> getParticipants(){
        return participantRepository.findAll();
    }

    public Participant getParticipant(Long id) {
        Participant participant = getParticipants().stream().filter(p -> id.equals(p.getId()))
                .findFirst()
                .orElse(null);
        return participant;
    }

    public void addParticipant(Participant participant, BindingResult bindingResult) {
        Optional<Participant> participantOptional = participantRepository.findParticipantByEmail(participant.getEmail());
        if (participantOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        if(bindingResult.hasErrors()){
            throw new IllegalStateException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        participantRepository.save(participant);
    }

    public void deleteParticipant(Long id){
        Optional<Participant> participantId = participantRepository.findById(id);
        if(!participantId.isPresent()){
            throw new IllegalStateException("participant n'existe pas");
        }
        participantId.get().setProfil(null);
        participantId.get().setSessions(null);
        participantId.get().setPays(null);
        participantRepository.deleteById(id);
    }

    public void updateParticipant(Long participantId, Participant Newparticipant) {
        Participant participant = participantRepository.findById(participantId).orElseThrow(()-> new IllegalStateException(
                "le participant " + participantId + " n'existe pas"));

        if (Newparticipant.getNom()!=null){ participant.setNom(Newparticipant.getNom()); }
        if (Newparticipant.getPrenom()!=null){ participant.setPrenom(Newparticipant.getPrenom()); }
        if (Newparticipant.getEmail()!=null){ participant.setEmail(Newparticipant.getEmail()); }
        if (Newparticipant.getTelephone()!=null){ participant.setTelephone(Newparticipant.getTelephone()); }
        if (Newparticipant.getType()!=null){ participant.setType(Newparticipant.getType()); }
        if (Newparticipant.getProfil()!=null){ participant.setProfil(Newparticipant.getProfil()); }
        participantRepository.save(participant);

    }

// hedhi jsp
    public void clearSessionsForParticipant(Long participantId){
        Participant participant = participantRepository.findById(participantId).orElseThrow(()-> new IllegalStateException(
                "participant with id " + participantId + " does not exist"));
        Set<Session> sessions = participant.getSessions();
        sessions.clear();
        participant.setSessions(sessions);
        participantRepository.save(participant);
    }


}


