package it.ticket.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.ticket.platform.model.Ticket;


public interface TicketRepository extends JpaRepository<Ticket, Long>{
	
	
}
