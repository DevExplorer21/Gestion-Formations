package com.example.gestionformation.Services;

import com.example.gestionformation.Entities.Domaine;
import com.example.gestionformation.Repositories.DomaineRepository;
import com.example.gestionformation.Repositories.FormationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
public class DomaineService {

        private final DomaineRepository domaineRepository;
        private final FormationRepository formationRepository;

        @Autowired
        public DomaineService(DomaineRepository domaineRepository, FormationRepository formationRepository) {
            this.domaineRepository = domaineRepository;
            this.formationRepository = formationRepository;
        }


        // get all domaines
        public List<Domaine> getDomains() {
            return domaineRepository.findAll();
        }

      // get domaine by id
        public Domaine getDomaine(Long id) {
            Domaine domaine = getDomains().stream().filter(d -> id.equals(d.getId())).findFirst().orElse(new Domaine("NotFound"));
            return domaine;
        }

        // add domaine
        //optional faza bech kn je retour feragh t9ala9ch l code mte3na
        public void addDomaine(Domaine domaine) {
            Optional<Domaine> domainOpt = domaineRepository.findDomaineByLibelle(domaine.getLibelle());
            if (domainOpt.isPresent()){
                throw new IllegalStateException("Domaine existe déja");
            }
            domaineRepository.save(domaine);
        }

        // delete domaine
        public void deleteDomaine(Long domaineId) {
            domaineRepository.findById(domaineId);
            boolean existe = domaineRepository.existsById(domaineId);
            if(!existe){
                throw new IllegalStateException("le domaine" + domaineId + " n'existe pas");
            }
            Optional<Domaine> domaine = domaineRepository.findById(domaineId);
            domaine.get().getFormations().forEach(formation-> { formation.setDomain(null); });
            domaine.get().setFormations(null);
            domaineRepository.deleteById(domaineId);
        }

        //update domaine
        public void updateDomaine(Long domaineId, Domaine Newdomaine) {
            Domaine domaine = domaineRepository.findById(domaineId).orElseThrow(()-> new IllegalStateException( "le domaine" + domaineId + " n'existe pas"));
            if (Newdomaine.getLibelle()!=null )
            { domaine.setLibelle(Newdomaine.getLibelle()); }

            if (Newdomaine.getFormations()!=null )
            { domaine.setFormations(Newdomaine.getFormations()); }

            domaineRepository.save(domaine);
        }

}
