package it.ticket.platform.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="Notes")
public class Note {
	
	/*
	 * START DEFINITION COLUMNS
	 */
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Text cannot be null")
	@Column(name="text",  nullable=false)
	private String text; 
	
	@Column(nullable = false)
    private LocalDateTime createdAt;

	@Column(nullable = true)
    private LocalDateTime updatedAt;
	
	/*
	 * END DEFINITION COLUMNS
	 */
	
	/*
     * START RELATIONS
     */
	
	@ManyToOne
	@JoinColumn(name="ticket_id", nullable=false)
	private Ticket ticket;
	
	/*
     * END RELATIONS
     */
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	
	
}
