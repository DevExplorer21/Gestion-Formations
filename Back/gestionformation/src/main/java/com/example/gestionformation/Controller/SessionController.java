package com.example.gestionformation.Controller;

import com.example.gestionformation.Entities.Participant;
import com.example.gestionformation.Entities.Session;
import com.example.gestionformation.Services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/session")
public class SessionController {
    private final SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService){ this.sessionService=sessionService; }

    @GetMapping(path = "/participants/{sessionId}")
    public Set<Participant> getAllParticipantsSessions(@PathVariable Long sessionId){
        return sessionService.getAllParticipantsSessions(sessionId);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping
    public List<Session> getsessions(){
        return sessionService.getSessions();
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(path = "{sessionId}")
    Session getSession(@PathVariable Long sessionId) {
        return sessionService.getSession(sessionId);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping
    public Session addSession(@Valid @RequestBody Session session, BindingResult bindingResult){
        sessionService.addSession(session,bindingResult);
        return session;
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @DeleteMapping("{id}")
    public void DeleteSessionById(@PathVariable Long id) {
        sessionService.DeleteSession(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PutMapping(path="{sessionId}")
    public void updateSession( @PathVariable("sessionId") Long sessionId, @RequestBody Session Newsession ){
        sessionService.updateSession(sessionId, Newsession);
    }

   /* @GetMapping(path="/deleteFormationSession/{sessionId}")
    public void deleteFormationSession(@PathVariable("sessionId") Long sessionId){
        sessionService.deleteFormationSession(sessionId);
    }*/
}
