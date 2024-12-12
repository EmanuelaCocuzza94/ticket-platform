package it.ticket.platform.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="Categories")
public class Category {
	/*
	 * START DEFINITION COLUMNS
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotBlank(message = "Name cannot be null")
    @Column(nullable = false, unique = true)
    private String name;

    /*
	 * END DEFINITION COLUMNS
	 */
    
    /*
     * START RELATIONS
     */
    @OneToMany(mappedBy = "category")
    private List<Ticket> tickets;
    
    /*
     * >END RELATIONS
     */
    
    // TODO Getters and Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
    
}