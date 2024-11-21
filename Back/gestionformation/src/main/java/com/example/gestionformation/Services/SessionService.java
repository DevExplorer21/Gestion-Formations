package com.example.gestionformation.Services;

import com.example.gestionformation.Entities.Participant;
import com.example.gestionformation.Entities.Session;
import com.example.gestionformation.Repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;
import java.util.Set;
@Service
public class SessionService {

        private final SessionRepository sessionRepository;

        @Autowired
        public SessionService(SessionRepository sessionRepository) {
            this.sessionRepository = sessionRepository;
        }

        public List<Session> getSessions() {
            return sessionRepository.findAll();
        }

        public Set<Participant> getAllParticipantsSessions(Long sessionId) {
            Session session = getSessions().stream().filter(s -> sessionId.equals(s.getId())).findFirst().orElse(null);
            return session.getParticipants();
        }

        public Session getSession(Long id) {
            Session session = getSessions().stream().filter(s -> id.equals(s.getId())).findFirst().orElse(null);
            return session;
        }

        public void addSession(Session session, BindingResult bindingResult) {
            sessionRepository.save(session);
        }

        public void DeleteSession(Long id) {
            Optional<Session> sessionId = sessionRepository.findById(id);
            if(!sessionId.isPresent()){
                throw new IllegalStateException("session does not exist");
            }
            sessionId.get().getOrganisme().setSessions(null);
            sessionId.get().setOrganisme(null);
            sessionId.get().getFormateur().setSessions(null);
            sessionId.get().setFormateur(null);
            sessionId.get().setFormations(null);
            sessionId.get().setFormations(null);
            sessionId.get().getParticipants().forEach(p -> { p.setSessions(null); });
            sessionId.get().setParticipants(null);

            sessionRepository.deleteById(id);
        }

        public void updateSession(Long sessionId, Session sessionUpdate) {
            Session session = sessionRepository.findById(sessionId).orElseThrow(()-> new IllegalStateException(
                    "session with id " + sessionId + " does not exist"));

            if (sessionUpdate.getLieu()!=null ){ session.setLieu(sessionUpdate.getLieu()); }
            if (sessionUpdate.getDateDebut()!=null ){ session.setDateDebut(sessionUpdate.getDateDebut()); }
            if (sessionUpdate.getDateFin()!=null ){ session.setDateFin(sessionUpdate.getDateFin()); }
            if (sessionUpdate.getOrganisme()!=null ){ session.setOrganisme(sessionUpdate.getOrganisme()); }
            if (sessionUpdate.getFormateur()!=null ){ session.setFormateur(sessionUpdate.getFormateur()); }
            if (sessionUpdate.getFormations()!=null ){ session.setFormations(sessionUpdate.getFormations()); }
            if (sessionUpdate.getNbrParticipants()!=null ){ session.setNbrParticipants(sessionUpdate.getNbrParticipants()); }
            sessionRepository.save(session);
        }

       /* public void deleteFormationSession(Long sessionId){
            Session session = sessionRepository.findById(sessionId).orElseThrow(()-> new IllegalStateException(
                    "sessions with id " + sessionId + " does not exist"));
            session.getFormations().forEach(f -> { f.setSessions(null); });
            session.setFormations(null);
            sessionRepository.save(session);
        }
        */
}
