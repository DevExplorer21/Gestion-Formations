package com.example.gestionformation.Controller;

import com.example.gestionformation.Entities.Participant;
import com.example.gestionformation.Repositories.ParticipantRepository;
import com.example.gestionformation.Repositories.SessionRepository;
import com.example.gestionformation.Services.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "api/participant")
public class ParticipantController {
    private final ParticipantService participantService;

    @Autowired
    public ParticipantController(ParticipantService participantService){
        this.participantService=participantService;
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping
    public List<Participant> getParticipants(){
        return participantService.getParticipants();
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(path = "{participantId}")
    Participant getParticipant(@PathVariable Long participantId) {
        return participantService.getParticipant(participantId);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping
    public Participant addParticipant(@Valid @RequestBody Participant participant, BindingResult bindingResult){
        participantService.addParticipant(participant,bindingResult);
        return participant;
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @DeleteMapping("{id}")
    public void deleteParticipant(@PathVariable Long id) {
        participantService.deleteParticipant(id);
    }


    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PutMapping(path="{participantId}")
    public void updateParticipant(
            @PathVariable("participantId") Long participantId,
            @RequestBody Participant participantUpdate
    ){
        participantService.updateParticipant(participantId, participantUpdate);

    }
// hedhi jsp
   /* @GetMapping(path="/clear/{participantId}")
    public void clearSessionsForParticipant(@PathVariable("participantId") Long participantId){
        participantService.clearSessionsForParticipant(participantId);
    }*/
}
