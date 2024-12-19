package it.ticket.platform.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
	
	@Bean
	public UserService userService() {
		return new UserService();
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
			.requestMatchers("/ticket/create").hasAuthority("ADMIN")
			.requestMatchers("/ticket", "/ticket/**").hasAnyAuthority("ADMIN", "OPERATOR")
			.requestMatchers("/**").permitAll()
			.and().formLogin().and().logout()
			.and().exceptionHandling()
			.and().csrf().disable();
		return http.build();
	}
	
	
//	@Bean
//	public DatabaseUserService userService() {
//		return new DatabaseUserService();
//	}
    
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userService());
		provider.setPasswordEncoder(passwordEncoder());
		
		return provider;
	}
	
}
