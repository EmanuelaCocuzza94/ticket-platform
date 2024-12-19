package it.ticket.platform.security;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import it.ticket.platform.model.Role;
import it.ticket.platform.model.User;

public class DatabaseUserDetails implements UserDetails {

	private final Long id;
	private final String username;
	private final String password;
	private final Set<GrantedAuthority> authorities;
	
	public DatabaseUserDetails(User user) {
		GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().name());
		
		this.id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		authorities = new HashSet<>();
		//for(Role role : Role.values()) {
		authorities.add(new SimpleGrantedAuthority("ROLE_OPERATOR"));
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		//}
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

}
