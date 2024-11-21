package com.example.gestionformation.Controller;

import com.example.gestionformation.Entities.Role;
import com.example.gestionformation.Services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/role")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService){ this.roleService = roleService; }

    @GetMapping
    public List<Role> getRoles(){
        return roleService.getRoles();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "{roleId}")
    Role getRole(@PathVariable Long roleId) {
        return roleService.getRole(roleId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public void registerNewRole(@Valid @RequestBody Role role, BindingResult bindingResult){
        roleService.addRole(role,bindingResult);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(path="{roleId}")
    public void updateRole( @PathVariable("roleId") Long roleId, @RequestBody Role Newrole ){
        roleService.updateRole(roleId, Newrole);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("{id}")
    public void deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
    }

    //@PreAuthorize("hasRole('ADMIN')")
/*    @GetMapping(path="/link/{userId}/{roleId}")
    public void linkUserToRole(@PathVariable("userId") Long userId, @PathVariable("roleId") Long roleId){
        roleService.linkNewUserToRole(userId, roleId);
    }

    @GetMapping(path="/clear/{userId}")
    public void clearRoles(@PathVariable("userId") Long userId){
        roleService.clearRoles(userId);
    }*/
}
