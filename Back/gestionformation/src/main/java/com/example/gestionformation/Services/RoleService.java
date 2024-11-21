package com.example.gestionformation.Services;

import com.example.gestionformation.Entities.Role;
import com.example.gestionformation.Entities.User;
import com.example.gestionformation.Repositories.RoleRepository;
import com.example.gestionformation.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository, UserRepository userRepository){
        this.roleRepository=roleRepository;
        this.userRepository=userRepository;
    }

    public List<Role> getRoles(){
        return roleRepository.findAll();
    }

    public Role getRole(Long id) {
        Role role = getRoles().stream().filter(r -> id.equals(r.getId())).findFirst().orElse(null);
        return role;
    }

    public void addRole(Role role, BindingResult bindingResult) {
        Optional<Role> Optrole = roleRepository.findByName(role.getName());
        if (Optrole.isPresent()){
            throw new IllegalStateException("role existe déjà");
        }
        if(bindingResult.hasErrors()){
            throw new IllegalStateException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        roleRepository.save(role);
    }

    public void deleteRole(Long id){
        Optional<Role> roleId = roleRepository.findById(id);
        if(!roleId.isPresent()){ throw new IllegalStateException("role n'existe pas");}

       /* roleId.get().getUsers().forEach(u->{ u.setRoles(null); });
        roleId.get().setUsers(null);*/
        roleRepository.deleteById(id);
    }

    public void updateRole(Long roleId, Role Newrole) {
        Role role = roleRepository.findById(roleId).orElseThrow(()-> new IllegalStateException(
                "role " + roleId + " n'existe pas"));
        if (Newrole.getName() !=null){ role.setName(Newrole.getName()); }
      //  if (Newrole.getUsers()!=null ){ role.setUsers(Newrole.getUsers()); }
        roleRepository.save(role);
    }

   /* public void linkNewUserToRole(Long userId, Long roleId){
        User user = userRepository.findById(userId).orElseThrow(()-> new IllegalStateException(
                "user with id " + userId + " does not exist"));
        Role role = roleRepository.findById(roleId).orElseThrow(()-> new IllegalStateException(
                "role with id " + roleId + " does not exist"));

        Set<Role> userRoles = user.getRoles();
        userRoles.add(role);
        user.setRoles(userRoles);
        //userRepository.save(user);
    }

    public void clearRoles(Long userId){
        User user = userRepository.findById(userId).orElseThrow(()-> new IllegalStateException(
                "user with id " + userId + " does not exist"));
        Set<Role> userRoles = user.getRoles();
        userRoles.clear();
        user.setRoles(userRoles);
        //userRepository.save(user);
    }*/
}
