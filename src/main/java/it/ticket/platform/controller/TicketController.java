package it.ticket.platform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.ticket.platform.model.Ticket;
import it.ticket.platform.repository.TicketRepository;

@Controller
@RequestMapping("/ticket")
public class TicketController {
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@GetMapping
	public String index(@RequestParam(name = "code", required = false) String code, 
			Model model) {
		
		
		List<Ticket> allTickets = ticketRepository.findAll();
		
		// aggiunge all ticket/index.html le variabili definite in addAttribute;
		model.addAttribute("ticket", allTickets);
		
		return "ticket/index";
	}
	
	@GetMapping("/create")
	public String create() {
		String[] select = {"One", "Two", "Three"};
		
		return "ticket/create";
	}
	
	@PostMapping("{user_id}/create")
	public String store() {
		// creare la riga a database
		// 1) validare i dati;
		// 2) salvare i dati a DB;
		
		// mess success: ticket creato con successo
		return "ticket/index";
	}
}
