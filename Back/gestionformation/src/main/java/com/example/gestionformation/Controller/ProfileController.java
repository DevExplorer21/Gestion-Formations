package com.example.gestionformation.Controller;

import com.example.gestionformation.Entities.Profile;
import com.example.gestionformation.Services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<Profile> getProfiles(){
        return profileService.getProfiles();
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping
    public void addProfile(@RequestBody Profile profile){
        profileService.addProfile(profile);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @DeleteMapping(path="{profileId}")
    public void deleteProfile(@PathVariable("profileId") Long profileId){
        profileService.deleteProfile(profileId);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(path="{profileId}")
    public void updateProfile( @PathVariable("profileId") Long profileId, @RequestBody Profile profileUpdate) {
        profileService.updateProfile(profileId, profileUpdate);

    }

}
