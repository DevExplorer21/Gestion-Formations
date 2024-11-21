package com.example.gestionformation.Services;

import com.example.gestionformation.Entities.Formateur;
import com.example.gestionformation.Repositories.FormateurRepository;
import com.example.gestionformation.Repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
public class FormateurService {

    private final FormateurRepository formateurRepository;
    private final SessionRepository sessionRepository;

    @Autowired
    public FormateurService(FormateurRepository formateurRepository, SessionRepository sessionRepository) {
        this.formateurRepository = formateurRepository;
        this.sessionRepository = sessionRepository;
    }
    public List<Formateur> getFormateurs() {
        return formateurRepository.findAll();
    }

    public void deleteFormateur(Long id) {
        Optional<Formateur> formateurId = formateurRepository.findById(id);
        if(!formateurId.isPresent()){ throw new IllegalStateException("formateur n'existe pas"); }
        formateurId.get().getSessions().forEach(s -> { s.setFormateur(null); });
        formateurId.get().setSessions(null);
        formateurId.get().getOrganisme().setFormateurs(null);
        formateurId.get().setOrganisme(null);
        formateurRepository.deleteById(id);
    }

    public void addFormateur(Formateur formateur, BindingResult bindingResult) {
        Optional<Formateur> Optformateur = formateurRepository.findFormateurByEmail(formateur.getEmail());
        if (Optformateur.isPresent()){ throw new IllegalStateException("email existe déjà"); }
        if(bindingResult.hasErrors()){ throw new IllegalStateException(bindingResult.getAllErrors().get(0).getDefaultMessage()); }
        formateurRepository.save(formateur);
    }

    public Formateur getFormateur(Long id) {
        Formateur formateur = getFormateurs().stream().filter(f -> id.equals(f.getId())).findFirst().orElse(null);
        return formateur;
    }

    public void updateFormateur(Long formateurId, Formateur Nnewformateur) {
        Formateur formateur = formateurRepository.findById(formateurId).orElseThrow(()-> new IllegalStateException(
                "le formateur  " + formateurId + " n'existe pas"));

        if (Nnewformateur.getNom()!=null ){ formateur.setNom(Nnewformateur.getNom()); }
        if (Nnewformateur.getPrenom()!=null){ formateur.setPrenom(Nnewformateur.getPrenom()); }
        if (Nnewformateur.getEmail()!=null){ formateur.setEmail(Nnewformateur.getEmail()); }
        if (Nnewformateur.getTelephone()!=null){ formateur.setTelephone(Nnewformateur.getTelephone()); }
        if (Nnewformateur.getType()!=null){ formateur.setType(Nnewformateur.getType()); }
        if (Nnewformateur.getOrganisme()!=null){ formateur.setOrganisme(Nnewformateur.getOrganisme()); }
        formateurRepository.save(formateur);

    }


}

