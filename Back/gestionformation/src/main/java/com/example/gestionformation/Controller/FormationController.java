package com.example.gestionformation.Controller;

import com.example.gestionformation.Entities.Formation;
import com.example.gestionformation.Services.FormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/formation")
public class FormationController {

        private final FormationService formationService;

        @Autowired
        public FormationController(FormationService formationService){
            this.formationService=formationService;
        }

        @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
        @GetMapping
        public List<Formation> getFormations(){
            return formationService.getFormations();
        }

        @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
        @GetMapping("{formationId}")
        Formation getFormation(@PathVariable Long formationId) {
            return formationService.getFormation(formationId);
        }

        @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
        @PostMapping
        public Formation addFormation(@Valid @RequestBody Formation formation, BindingResult bindingResult)
        {   formationService.addFormation(formation,bindingResult);
            return formation;
        }

        @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
        @DeleteMapping("{formationId}")
        public void deleteFormation(@PathVariable Long formationId) { formationService.deleteFormation(formationId); }

        @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
        @PutMapping("{formationId}")
        public void updateFormation( @PathVariable("formationId") Long formationId,
                @RequestBody Formation Newformation
        ){ formationService.updateFormation(formationId, Newformation); }
}
