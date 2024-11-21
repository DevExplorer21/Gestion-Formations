package com.example.gestionformation.Services;

import com.example.gestionformation.Entities.User;
import com.example.gestionformation.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        User user = getUsers().stream().filter(u -> id.equals(u.getId())).findFirst().orElse(null);
        return user;

    }

    public void addUser(User user, BindingResult bindingResult) {
        Optional<User> userOptional = userRepository.findByUsername(user.getUsername());
        String password=user.getPassword();
        //String pw_hash = BCrypt.hashpw(password, BCrypt.gensalt(10));
        // user.setPassword(pw_hash);
        if (userOptional.isPresent()){
            throw new IllegalStateException("login existe déjà");
        }
        if(bindingResult.hasErrors()){
            throw new IllegalStateException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        userRepository.save(user);
    }

    public void deleteUser(Long id){
        Optional<User> userId = userRepository.findById(id);
        if(!userId.isPresent()){
            throw new IllegalStateException("user n'existe pas");
        }
        userId.get().setRoles(null);
        userRepository.deleteById(id);
    }

    public void updateUser(Long userId, User Newuser) {
        User user = userRepository.findById(userId).orElseThrow(()-> new IllegalStateException(
                "user " + userId + " n'existe pas"));

        if (Newuser.getPassword()!=null){
            //String pw_hash = BCrypt.hashpw(userUpdate.getPassword(), BCrypt.gensalt(10));
            //user.setPassword(pw_hash);
            user.setPassword(Newuser.getPassword());
        }

        if (Newuser.getUsername()!=null){
            user.setUsername(Newuser.getUsername());
        }

        if (Newuser.getRoles()!=null){
            user.setRoles(Newuser.getRoles());
        }

        userRepository.save(user);

    }

}

