package it.ticket.platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.ticket.platform.model.Ticket;


public interface TicketRepository extends JpaRepository<Ticket, Long>{
	
	public List<Ticket> findByTitleContaining(String title);
}
