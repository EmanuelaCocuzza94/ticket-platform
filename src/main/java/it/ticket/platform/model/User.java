package it.ticket.platform.model;

import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="users")
public class User {
	
	public User() {
		
	}
	
	/*
	 * users
       (created_at, email, is_available, password, updated_at, username) 
	 */
	public User(LocalDateTime createdAt, String email,
			boolean isAvailable, String password,
			LocalDateTime updatedAt, String username, Role role
			) {
		this.setCreatedAt(createdAt);
		this.setEmail(email);
		this.setAvailable(isAvailable);
		this.setPassword(password);
		this.setUpdatedAt(updatedAt);
		this.setUsername(username);
		this.setRole(role);
	}

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
	
	@Column(nullable = false)
	private boolean isAvailable;
	
	@CreationTimestamp
	@Column(nullable = true)
    private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(nullable = true)
    private LocalDateTime updatedAt;
	
	@Enumerated(EnumType.STRING)
	@Column(updatable = false)
    private Role role;
	
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
	
//	@ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable(
//        name = "user_role",
//        joinColumns = @JoinColumn(name = "user_id"),
//        inverseJoinColumns = @JoinColumn(name = "role_id")
//    )
//	private List<Role> roles;
    
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


	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Set<Ticket> getAssignedTickets() {
		return assignedTickets;
	}

	public void setAssignedTickets(Set<Ticket> assignedTickets) {
		this.assignedTickets = assignedTickets;
	}

	public Set<Ticket> getCreatedTickets() {
		return createdTickets;
	}

	public void setCreatedTickets(Set<Ticket> createdTickets) {
		this.createdTickets = createdTickets;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
}
