package com.example.gestionformation.Controller;

import com.example.gestionformation.Entities.User;
import com.example.gestionformation.Services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserDetailsServiceImpl userService;

    @Autowired
    public UserController(UserDetailsServiceImpl userService){
        this.userService=userService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "{userId}")
    User getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }

    @PostMapping
    public User addUser(@Valid @RequestBody User user, BindingResult bindingResult){
        userService.addUser(user,bindingResult);
        return user;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(path="{userId}")
    public void updateUser(
            @PathVariable("userId") Long userId,
            @RequestBody User userUpdate
    ) {
        userService.updateUser(userId, userUpdate);
    }

}
