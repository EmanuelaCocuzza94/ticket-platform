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
import org.springframework.web.bind.annotation.PathVariable;
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
	public String index(@RequestParam(name = "title", required = false) String keyword, 
		Model model, @AuthenticationPrincipal UserDetails currentUser ) {
		
		User auth = userRepository.findUserByUsername(currentUser.getUsername());
		
		List<Ticket> allTickets = null;
		Role authRole = auth.getRole();
		Long authId = auth.getId();
		if(authRole == Role.ADMIN) {
			if(keyword != null && !keyword.isBlank()) {
				model.addAttribute("keyword", keyword);
				allTickets = ticketRepository.findByTitleContaining(keyword);
			} else {
				allTickets = ticketRepository.findAll();
			}
		} else if(authRole == Role.OPERATOR) {
			if(keyword != null && !keyword.isBlank()) {
				model.addAttribute("keyword", keyword);
				allTickets = ticketRepository.findByUserIdAndTitleContaining(authId, keyword);
			} else {
				allTickets = ticketRepository.findByUserId(authId);
			}
		}
		
		
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
		
		//model.addAttribute("createdAt", LocalDateTime.now());
		ticketRepository.save(ticket);
		
		return "redirect:/ticket";
	};
	
	@GetMapping("/show/{id}")
	public String show(@PathVariable(name = "id")Long id, Model model) {
		
		Optional<Ticket> ticket = ticketRepository.findById(id);
		
		if(ticket.isPresent()) {
			model.addAttribute("operators", userRepository.findByIsAvailableAndRole(true, Role.OPERATOR));
			model.addAttribute("categories", categoryRepository.findAll());
	        model.addAttribute("ticketStatus", Arrays.asList(TicketStatus.values()));
	        
			model.addAttribute("ticket", ticket.get());
		}
		
		return "ticket/show";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable(name = "id") Long id, Model model) {

		Optional<Ticket> ticket = ticketRepository.findById(id);
		
		if(ticket.isPresent()) {
			model.addAttribute("ticket", ticket.get());
			
			model.addAttribute("operators", userRepository.findByIsAvailableAndRole(true, Role.OPERATOR));
			model.addAttribute("categories", categoryRepository.findAll());
	        model.addAttribute("ticketStatus", Arrays.asList(TicketStatus.values()));
		}
		
		return "ticket/edit";
	}
	
	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute(name = "ticket") Ticket ticket, BindingResult bindingResult,
			Model model) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("ticket", ticket);
			model.addAttribute("operators", userRepository.findByIsAvailableAndRole(true, Role.OPERATOR));
			model.addAttribute("categories", categoryRepository.findAll());
	        model.addAttribute("ticketStatus", Arrays.asList(TicketStatus.values()));
			
			return "ticket/edit";
		}
		
		//model.addAttribute("updatedAt", LocalDateTime.now());
		ticketRepository.save(ticket);
		return "redirect:/ticket";
	}
	
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable(name = "id") Long id) {
		ticketRepository.deleteById(id);
		return "redirect:/ticket";
	}
	
}
