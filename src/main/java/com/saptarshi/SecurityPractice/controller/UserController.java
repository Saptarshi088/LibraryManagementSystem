package com.saptarshi.SecurityPractice.controller;

import com.saptarshi.SecurityPractice.dto.UserDto;
import com.saptarshi.SecurityPractice.mapper.UserMapper;
import com.saptarshi.SecurityPractice.model.User;
import com.saptarshi.SecurityPractice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.beans.Encoder;
import java.security.Principal;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService service;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UserMapper userMapper;

    @GetMapping("/api")
    public String home(){
        return "Hello World!";
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getALlUsers(){

        return ResponseEntity.ok(service.getAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList()
        );
    }

    @PostMapping("/register")
    public User createUser(@RequestBody User user){
        String pass = user.getPassword();
        user.setPassword(encoder.encode(pass));
        return service.addUser(user);
    }

    @PutMapping("/changePassword")
    public User changePass(@RequestParam String password, Principal principal){
        String username = principal.getName();
        return service.changePassword(username,password);
    }

    @PutMapping("/forgetPassword")
    public User forgetPassword(@RequestParam String username,@RequestParam String password){
        return service.forgetPassword(username,password);
    }

    @PutMapping("/admin/reset/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public User adminReset(@PathVariable String username, @RequestParam String password) {
        return service.changePassword(username, password);
    }


    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestParam String username, @RequestParam String pass){
        try {
            User deletedUser = service.remove(username, pass);
            return ResponseEntity.ok("User " + deletedUser.getUsername() + " deleted.");
        }catch(RuntimeException e){
            return ResponseEntity.status(401).body(e.getMessage());

        }
    }

}
