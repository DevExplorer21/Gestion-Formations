package com.example.gestionformation.Controller;

import com.example.gestionformation.Entities.Organisme;
import com.example.gestionformation.Services.OrganismeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/organisme")
public class OrganismeController {
    private final OrganismeService organismeService;

    @Autowired
    public OrganismeController(OrganismeService organismeService){
        this.organismeService=organismeService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<Organisme> getOrganismes(){
        return organismeService.getOrganismes();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("{organismeId}")
    Organisme getOrganisme(@PathVariable Long organismeId) {
        return organismeService.getOrganisme(organismeId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public Organisme addOrganisme(@Valid @RequestBody Organisme organisme, BindingResult bindingResult)
    {   organismeService.addOrganisme(organisme,bindingResult);
        return organisme;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("{organismeId}")
    public void deleteOrganisme(@PathVariable Long organismeId) { organismeService.deleteOrganisme(organismeId); }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("{organismeId}")
    public void updateOrganisme( @PathVariable("organismeId") Long organismeId,
                                 @RequestBody Organisme NewOrganisme
    ){ organismeService.updateOrganisme(organismeId, NewOrganisme); }
}



