package com.example.gestionformation.Controller;

import com.example.gestionformation.Entities.Formateur;
import com.example.gestionformation.Services.FormateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "api/formateur")
public class FormateurController {
    private final FormateurService formateurService;

    @Autowired
    public FormateurController(FormateurService formateurService) {
        this.formateurService = formateurService;
    }

    @GetMapping
    public List<Formateur> getFormateurs() {
        return formateurService.getFormateurs();
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(path = "{formateurId}")
    Formateur getFormateur(@PathVariable Long formateurId) {
        return formateurService.getFormateur(formateurId);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMINAdmin')")
    @PostMapping
    public Formateur addFormateur(@Valid @RequestBody Formateur formateur, BindingResult bindingResult) {
        formateurService.addFormateur(formateur, bindingResult);
        return formateur;
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @DeleteMapping("{id}")
    public void deleteFormateurById(@PathVariable Long id) {
        formateurService.deleteFormateur(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PutMapping(path = "{formateurId}")
    public void updateFormateur( @PathVariable("formateurId") Long formateurId, @RequestBody Formateur Newformateur)
    { formateurService.updateFormateur(formateurId, Newformateur);
      }




}
