package com.saptarshi.SecurityPractice.service;

import com.saptarshi.SecurityPractice.model.User;
import com.saptarshi.SecurityPractice.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepo repo;
    @Autowired
    PasswordEncoder encoder;

    public List<User>  getAllUsers() {
        return  repo.findAll();
    }

    public User addUser(User user) {
        return repo.save(user);
    }

    public User changePassword(String username, String password) {
        User user = repo.findByUsername(username);
        user.setPassword(encoder.encode(password));
        return repo.save(user);
    }

    public User forgetPassword(String username, String password) {
        User user = repo.findByUsername(username);
        user.setPassword(encoder.encode(password));
        return repo.save(user);
    }

    public User remove(String username, String pass) {
        User user = repo.findByUsername(username);
        if(encoder.matches(pass,user.getPassword())){
            repo.delete(user);
            return user;
        } else {
            throw new RuntimeException("Invalid Credentials");
        }
    }
}
