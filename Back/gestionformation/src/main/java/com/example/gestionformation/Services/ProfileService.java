package com.example.gestionformation.Services;

import com.example.gestionformation.Entities.Participant;
import com.example.gestionformation.Entities.Profile;
import com.example.gestionformation.Repositories.ParticipantRepository;
import com.example.gestionformation.Repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final ParticipantRepository participantRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository, ParticipantRepository participantRepository) {
        this.profileRepository = profileRepository;
        this.participantRepository = participantRepository;
    }

    public List<Profile> getProfiles(){
        return profileRepository.findAll();
    }

    public void addProfile(Profile profile) {
        Optional<Profile> Optprofile = profileRepository.findProfileByLibelle(profile.getLibelle());
        if (Optprofile.isPresent()){
            throw new IllegalStateException("Profile existe déjà");
        }
        profileRepository.save(profile);
    }

    public void deleteProfile(Long profileId) {
        profileRepository.findById(profileId);
        boolean exists= profileRepository.existsById(profileId);
        if(!exists){
            throw new IllegalStateException("Profile " + profileId + " n'existe pas");
        }
        Optional<Profile> profile=profileRepository.findById(profileId);
        profile.get().getParticipants().forEach(p->{ p.setProfil(null); });
        profile.get().setParticipants(null);
        profileRepository.deleteById(profileId);
    }

    public void updateProfile(Long profileId, Profile profileUpdate) {
        Profile profile = profileRepository.findById(profileId).orElseThrow(()-> new IllegalStateException(
                "Profile " + profileId + " n'existe pas"));
        if (profileUpdate.getLibelle()!=null){
            profile.setLibelle(profileUpdate.getLibelle());
        }

        profileRepository.save(profile);

    }


   /* public void linkNewParticipantToProfile(Long participantId, Long profileId){
        Participant participant = participantRepository.findById(participantId).orElseThrow(()-> new IllegalStateException(
                "participant with id " + participantId + " does not exist"));
        Profile profile = profileRepository.findById(profileId).orElseThrow(()-> new IllegalStateException(
                "profile with id " + profileId + " does not exist"));
        participant.setProfil(profile);
        participantRepository.save(participant);

        Set<Participant> participants = profile.getParticipants();
        participants.add(participant);
        profile.setParticipants(participants);
        profileRepository.save(profile);
    }*/
}