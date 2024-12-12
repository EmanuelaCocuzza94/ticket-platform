package it.ticket.platform.model;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="Tickets")
public class Ticket {
	
	/*
	 * START DEFINITION COLUMNS
	 */
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	
	@NotBlank(message = "Code is required")
	@Column(nullable = false)
	private String code;
	
	@NotBlank(message = "Title cannot be null")
	@Column(nullable = false)
    private String title;
	
	@NotBlank(message = "Description cannot be null")
	@Column(nullable = false)
    private String description;
	
	@Enumerated(EnumType.STRING)
	private TicketStatus status;
	
	/*
	 * N istanze della classe Tickets per 1 istanza di User
	 */
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
	@JoinColumn(name = "created_by", nullable = false)
	private User createdBy;
    
    @Column(nullable = false)
    private LocalDateTime createdAt;

	@Column(nullable = true)
    private LocalDateTime updatedAt;
	
	/*
	 * END DEFINITION COLUMNS
	 */
	
	/**
	 * START RELATIONS
	 */
	
	@OneToMany(mappedBy = "ticket")
	private Set<Note> notes;
	
	/**
	 * END RELATIONS
	 */
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Note> getNotes() {
		return notes;
	}

	public void setNotes(Set<Note> notes) {
		this.notes = notes;
	}

	public TicketStatus getStatus() {
		return status;
	}

	public void setStatus(TicketStatus status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	
}
