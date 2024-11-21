package com.example.gestionformation.Controller;

import com.example.gestionformation.Entities.Pays;
import com.example.gestionformation.Services.PaysService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path="api/pays")
public class PaysController {

    private final PaysService paysService;

    @Autowired
    public PaysController(PaysService paysService) {
        this.paysService = paysService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<Pays> getPays(){
        return paysService.getPays();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public void addPays(@RequestBody Pays pays){
        paysService.addPays(pays);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(path="{paysId}")
    public void deletePays(@PathVariable("paysId") Long paysId){
        paysService.deletePays(paysId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(path="{paysId}")
    public void updatePays(
            @PathVariable("paysId") Long paysId,
            @RequestBody Pays Newpays) {
        paysService.updatePays(paysId, Newpays);

    }



}
