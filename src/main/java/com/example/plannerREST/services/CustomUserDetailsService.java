package com.example.plannerREST.services;

import com.example.plannerREST.entities.Users;
import com.example.plannerREST.repositories.UserRepository;
import com.example.plannerREST.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

        Users users = userRepository.findByUserName(username);

        if(users == null || !users.isActive()) {
            throw new UsernameNotFoundException("User not found!");
        }

        User user = new User(users);

        return user;
    }
}
