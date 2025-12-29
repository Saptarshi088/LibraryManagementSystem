package com.saptarshi.SecurityPractice.service;

import com.saptarshi.SecurityPractice.model.User;
import com.saptarshi.SecurityPractice.model.UserPrinciple;
import com.saptarshi.SecurityPractice.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetails implements UserDetailsService {

    @Autowired
    UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = (User) repo.findByUsername(username);

//        return org.springframework.security.core.userdetails.User
//                .withUsername(user.getUsername())
//                .password(user.getPassword())
//                .roles(user.getRoles())
//                .build();

        // This line generates a UserDetails Manager
  //return new UserPrinciple(user);
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles())
                .build();
    }

}
