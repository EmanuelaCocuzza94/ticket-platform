package it.ticket.platform.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.ticket.platform.model.Role;
import it.ticket.platform.model.User;


public interface UserRepository extends JpaRepository<User, Long>{
	
	public Optional<User> findByUsername(String username);
	
	public User findUserByUsername(String username);
	
	public List<User> findByIsAvailableAndRole(boolean available, Role role);
}
