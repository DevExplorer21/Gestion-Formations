package com.example.gestionformation.Controller;

import com.example.gestionformation.Entities.Domaine;
import com.example.gestionformation.Services.DomaineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/domaine")
public class DomaineController {
    private final DomaineService domaineService;
    @Autowired
    public DomaineController(DomaineService domainService) {
        this.domaineService = domainService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<Domaine> getDomaines(){
        return domaineService.getDomains();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path="{domainId}")
    public Domaine getDomaine(@PathVariable("domainId") Long domainId){
        return domaineService.getDomaine(domainId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public void addDomaine(@RequestBody Domaine domain){
        domaineService.addDomaine(domain);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(path="{domainId}")
    public void deleteDomaine(@PathVariable("domainId") Long domainId){
        domaineService.deleteDomaine(domainId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(path="{domainId}")
    public void updateDomaine(
            @PathVariable("domainId") Long domainId,
            @RequestBody Domaine Newdomaine
    ){
        domaineService.updateDomaine(domainId, Newdomaine);
    }

}

