package it.ticket.platform.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="profiles")
public class Profile {
	
	
	public Profile() {
	}

	public Profile(String firstname, String lastname, User user) {
        this.setFirstname(firstname);
        this.setLastname(lastname);
        this.setUser(user);
    }
	
	/*
	 * START DEFINITION COLUMNS
	 */
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String firstname;
    
    @Column(nullable = true)
    private String lastname;
    
    /*
	 * END DEFINITION COLUMNS
	 */
    
    /*
     * START RELATIONS
     */

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    /*
     * END RELATIONS
     */
    public String getFullName() {
		return this.getFirstname() + " " + this.getLastname();
    }
    
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
}