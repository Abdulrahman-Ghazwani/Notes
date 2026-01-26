package com.notes.demo.service;

import com.notes.demo.model.User;
import com.notes.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService  {
    private final UserRepository userRepository;
    private final MyUserDetailsService myUserDetailsService;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        User user = userRepository.findUserById(id);
        if (user == null){
            throw new RuntimeException("user not found");
        }
        return user;
    }
    
    public void addUser(User user){
        userRepository.save(user);
    }

    public void deleteUser(Long id){
        User user = userRepository.findUserById(id);
        if (user == null){
            throw new RuntimeException("user not found");
        }
        userRepository.delete(user);
    }

    public void updateUser(User user, Long id){
        User u = userRepository.findUserById(id);
        if (u == null){
            throw new RuntimeException("user not found");
        }
        u.setName(user.getName());
        u.setUsername(user.getUsername());
        u.setEmail(user.getEmail());

        userRepository.save(u);
    }
}
