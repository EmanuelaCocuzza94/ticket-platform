package it.ticket.platform.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.ticket.platform.model.User;
import it.ticket.platform.security.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

//    @Autowired
//    private UserService userService;
//
//    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//    // Registrazione utente
//    @PostMapping("/register")
//    public ResponseEntity<?> registerUser(@RequestBody User user) {
//        User registeredUser = userService.registerUser(user.getUsername(), user.getEmail(), user.getPassword());
//        return ResponseEntity.ok("User registered with username: " + registeredUser.getUsername());
//    }

    
}