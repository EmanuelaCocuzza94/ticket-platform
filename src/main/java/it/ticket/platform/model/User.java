package it.ticket.platform.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="Users")
public class User {
	
	/*
	 * START DEFINITION COLUMNS
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "username cannot be null")
	@Column(unique = true, nullable = false)
	private String username;
	
	@NotBlank(message = "Email cannot be null")
	@Column(unique = true, nullable = false)
	private String email;
	
	@NotBlank(message = "Password cannot be null")
	@Column(nullable = false)
	private String password;
	
	@NotBlank
	@Column(nullable = false)
	private boolean isAvailable;
	
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
	
	@OneToOne(mappedBy = "user")
    private Profile profile;
	
	@OneToMany(mappedBy = "user")
	private Set<Ticket> assignedTickets;
	
	@OneToMany(mappedBy = "createdBy")
	private Set<Ticket> createdTickets;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="user_role")
	private List<Role> roles;
	
	/*
	 * END RELATIONS
	 */
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
}
