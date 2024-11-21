package com.example.gestionformation.Services;

import com.example.gestionformation.Entities.Organisme;
import com.example.gestionformation.Repositories.FormateurRepository;
import com.example.gestionformation.Repositories.OrganismeRepository;
import com.example.gestionformation.Repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;


@Service
public class OrganismeService {

    private final OrganismeRepository organismeRepository;
    private final SessionRepository sessionRepository;
    private final FormateurRepository formateurRepository;
    @Autowired
    public OrganismeService(OrganismeRepository organismeRepository, FormateurRepository formateurRepository, SessionRepository sessionRepository) {
        this.organismeRepository= organismeRepository;
        this.formateurRepository = formateurRepository;
        this.sessionRepository = sessionRepository;
    }

    public List<Organisme> getOrganismes() {
        return organismeRepository.findAll();
    }

    public Organisme getOrganisme(Long id) {
        Organisme organisme = getOrganismes().stream().filter(o -> id.equals(o.getId())).findFirst().orElse(null);
        return organisme;
    }

    public void addOrganisme(Organisme organisme, BindingResult bindingResult) {
        Optional<Organisme> organismeOptional = organismeRepository.findOrganismeByLibelle(organisme.getLibelle());
        if (organismeOptional.isPresent()){
            throw new IllegalStateException("Organisme existe déjà");
        }
        if(bindingResult.hasErrors()){
            throw new IllegalStateException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        organismeRepository.save(organisme);
    }

    public void deleteOrganisme(Long id) {
        Optional<Organisme> organismeId = organismeRepository.findById(id);
        if(!organismeId.isPresent()){ throw new IllegalStateException("Pas d'organisme avec cet id"); }
        organismeId.get().getSessions().forEach(s -> { s.setOrganisme(null); });
        organismeId.get().setSessions(null);
        organismeId.get().getFormateurs().forEach(f->{ f.setOrganisme(null); });
        organismeId.get().setFormateurs(null);
        organismeRepository.deleteById(id);
    }

    public void updateOrganisme(Long organismeId, Organisme Neworganisme) {
        Organisme organisme = organismeRepository.findById(organismeId).orElseThrow(()-> new IllegalStateException(
                "L'organisme' " + organismeId + " n'existe pas"));

        if (Neworganisme.getLibelle()!=null){ organisme.setLibelle(Neworganisme.getLibelle()); }
        if (Neworganisme.getFormateurs()!=null ){ organisme.setFormateurs(Neworganisme.getFormateurs()); }
        if (Neworganisme.getSessions()!=null ){ organisme.setSessions(Neworganisme.getSessions()); }
        organismeRepository.save(organisme);
    }

}
