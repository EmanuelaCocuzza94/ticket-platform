package it.ticket.platform.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.ticket.platform.model.Category;
import it.ticket.platform.model.Role;
import it.ticket.platform.model.Ticket;
import it.ticket.platform.model.TicketStatus;
import it.ticket.platform.model.User;
import it.ticket.platform.repository.CategoryRepository;
import it.ticket.platform.repository.TicketRepository;
import it.ticket.platform.repository.UserRepository;
import it.ticket.platform.security.UserService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/ticket")
public class TicketController {
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping
	public String index(@RequestParam(name = "title", required = false) String code, 
			Model model) {
		
		
		List<Ticket> allTickets = ticketRepository.findAll();
		
		System.out.println("call index page " + allTickets);
		
		// aggiunge all ticket/index.html le variabili definite in addAttribute;
		model.addAttribute("tickets", allTickets);
		
		return "ticket/index";
	}
	
	@GetMapping("/create")
	public String create(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        
		model.addAttribute("ticket", new Ticket());

		// Recupera operatori attivi (isAvailable == true)
		// get all active operators
		model.addAttribute("operators", userRepository.findByIsAvailableAndRole(true, Role.OPERATOR));
		model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("guest", userRepository.findUserByUsername(currentUser.getUsername()));
        
        model.addAttribute("ticketStatus", Arrays.asList(TicketStatus.values()));
        
        System.out.println("create Userrr: ");
		return "ticket/create";
	}
	
	@PostMapping("/store")
	public String store(@Valid @ModelAttribute(name = "ticket")Ticket ticket, BindingResult bindingResult,
			Model model, @AuthenticationPrincipal UserDetails currentUser) {
		
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("operators", userRepository.findByIsAvailableAndRole(true, Role.OPERATOR));
			model.addAttribute("categories", categoryRepository.findAll());
	        model.addAttribute("guest", userRepository.findUserByUsername(currentUser.getUsername()));
	        model.addAttribute("ticketStatus", Arrays.asList(TicketStatus.values()));
	        
			return "ticket/create";
		}
		
		model.addAttribute("createdAt", LocalDateTime.now());
		ticketRepository.save(ticket);
		
		return "redirect:/ticket";
	};
}
