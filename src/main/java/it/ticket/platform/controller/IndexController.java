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

import it.ticket.platform.model.User;
import it.ticket.platform.security.UserService;

@Controller
@RequestMapping("/")
public class IndexController {
	
	@GetMapping
	public String index() {
		return "redirect:/ticket";
	}
	
	@Autowired
    private UserService userService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
//	// Login utente
//    @PostMapping("/login")
//    public ResponseEntity<?> loginUser(@RequestBody User loginRequest) {
//        User user = userService.findByUsername(loginRequest.getUsername());
//
//        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
//            Map<String, String> response = new HashMap<>();
//            response.put("message", "Login successful");
//            response.put("username", user.getUsername());
//            return ResponseEntity.ok(response);
//        } else {
//            return ResponseEntity.status(401).body("Invalid credentials");
//        }
//    }

}