package it.ticket.platform.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.ticket.platform.security.UserService;
import it.ticket.platform.model.Role;
import it.ticket.platform.model.User;
import it.ticket.platform.model.UserAvailabilityDTO;
import it.ticket.platform.repository.TicketRepository;
import it.ticket.platform.repository.UserRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	private UserService userService;
	
	@GetMapping("/profile")
	public String index(Model model, @AuthenticationPrincipal UserDetails currentUser ) {
		
		User auth = userRepository.findUserByUsername(currentUser.getUsername());
		model.addAttribute("roles", Role.values());
		model.addAttribute("user", auth);
		
		return "profile/index";
	}
	
	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute(name = "user") User user, BindingResult bindingResult,
			Model model, @AuthenticationPrincipal UserDetails currentUser) {
		
		if(bindingResult.hasErrors()) {
			User auth = userRepository.findUserByUsername(currentUser.getUsername());
			model.addAttribute("roles", Role.values());
			model.addAttribute("user", auth);
			System.out.println("Errrorrrrrr: " );
			return "profile/index";
		}
		
		//model.addAttribute("updatedAt", LocalDateTime.now());
		userRepository.save(user);
		return "redirect:/ticket";
	}
	
//	@RequestMapping(value="/{id}/availability", method=RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//    public @ResponseBody ResponseEntity<String> updateAvailability(@PathVariable Long id, @RequestBody UserAvailabilityDTO availabilityDTO) {
//		userService.updateUserAvailable(id, availabilityDTO.isAvailable());
//        return ResponseEntity.ok("Update User Availability success!!");
//    }
	
	@PostMapping("/update-availability")
    public String updateAvailability(@ModelAttribute("user") User updatedUser, RedirectAttributes redirectAttributes) {
        // Recupera l'utente esistente dal database
        User user = userRepository.findById(updatedUser.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + updatedUser.getId()));

        // Aggiorna solo il campo isAvailable
        System.out.println("TTTTTT: " + updatedUser.isAvailable());
        user.setAvailable(updatedUser.isAvailable());
        userRepository.save(user);

        // Aggiungi un messaggio di successo
        redirectAttributes.addFlashAttribute("success", "User availability updated successfully!");
        return "redirect:/ticket";
    }
	

}