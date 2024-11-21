package com.example.gestionformation.Services;

import com.example.gestionformation.Entities.Formation;
import com.example.gestionformation.Repositories.FormationRepository;
import com.example.gestionformation.Repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FormationService {
    private final FormationRepository formationRepository;
    private final SessionRepository sessionRepository;

    @Autowired
    public FormationService(FormationRepository formationRepository, SessionRepository sessionRepository) {
        this.formationRepository = formationRepository;
        this.sessionRepository=sessionRepository;
    }

    public List<Formation> getFormations() {
        return formationRepository.findAll();
    }


    public Formation getFormation(Long id) {
        Formation formation = getFormations().stream().filter(f -> id.equals(f.getId())).findFirst().orElse(null);
        return formation;
    }

    public void addFormation(Formation formation, BindingResult bindingResult) {
        Optional<Formation>formationOptional = formationRepository.findFormationByTitre(formation.getTitre());
        if (formationOptional.isPresent()){
            throw new IllegalStateException("Formation existe déjà");
        }
        if(bindingResult.hasErrors()){
            throw new IllegalStateException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        formationRepository.save(formation);
    }

    public void deleteFormation(Long id) {
        Optional<Formation> formationId = formationRepository.findById(id);
        if(!formationId.isPresent()){ throw new IllegalStateException("Pas de formation avec cet id"); }
        formationId.get().getSessions().forEach(Session -> { Session.setFormations(null); });
        formationId.get().setSessions(null);
        formationId.get().getDomain().setFormations(null);
        formationId.get().setDomain(null);
        formationRepository.deleteById(id);
    }

    public void updateFormation(Long formationId, Formation Newformation) {
        Formation formation = formationRepository.findById(formationId).orElseThrow(()-> new IllegalStateException(
                "La formation " + formationId + " n'existe pas"));

        if (Newformation.getTitre()!=null){ formation.setTitre(Newformation.getTitre()); }
        if (Newformation.getType()!=null){ formation.setType(Newformation.getType()); }
        if (Newformation.getDuree()!=null){ formation.setDuree(Newformation.getDuree()); }
        if (Newformation.getBudget()!=null){ formation.setBudget(Newformation.getBudget()); }
        if (Newformation.getNb_session()!=0){ formation.setNb_session(Newformation.getNb_session()); }
        if (Newformation.getDomain()!=null ){ formation.setDomain(Newformation.getDomain()); }
        if (Newformation.getSessions()!=null ){ formation.setSessions(Newformation.getSessions()); }
        formationRepository.save(formation);
    }

}
