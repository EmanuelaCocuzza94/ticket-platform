package it.ticket.platform;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import it.ticket.platform.model.Category;
import it.ticket.platform.model.Profile;
import it.ticket.platform.model.Role;
import it.ticket.platform.model.User;
import it.ticket.platform.repository.CategoryRepository;
import it.ticket.platform.repository.ProfileRepository;
import it.ticket.platform.repository.UserRepository;

@SpringBootApplication
public class TicketPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketPlatformApplication.class, args);
	}
	

	@Bean
    CommandLineRunner seedDatabase(UserRepository userRepository, ProfileRepository profileRepository,
    		CategoryRepository categoryRepository
    		) {
        return args -> {
        	
            // Seed user data
            if (userRepository.count() == 0) {
            	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String encodedPassword = passwordEncoder.encode("password");
                
            	//users
            	User admin = new User(
            			LocalDateTime.now(),
            			"admin@admin.com",
            			true, encodedPassword,
            			LocalDateTime.now(),
            			"admin",
            			Role.ADMIN
            			);
        		Profile adminProfile = new Profile("John", "Doe", admin);
                userRepository.save(admin);
                profileRepository.save(adminProfile);
                
            	User operator = new User(
            			LocalDateTime.now(),
            			"operator@operator.test",
            			true, encodedPassword,
            			LocalDateTime.now(),
            			"operator",
            			Role.OPERATOR
            			);
        		Profile operatorProfile = new Profile("Pippo", "Doe", operator);
                userRepository.save(operator);
                profileRepository.save(operatorProfile);
                
                User operator2 = new User(
            			LocalDateTime.now(),
            			"operator2@operator2.test",
            			true, encodedPassword,
            			LocalDateTime.now(),
            			"operator2",
            			Role.OPERATOR
            			);
        		Profile operator2Profile = new Profile("Pluto", "Row", operator2);
                userRepository.save(operator2);
                profileRepository.save(operator2Profile);
                
                
                User operator3 = new User(
            			LocalDateTime.now(),
            			"operator3@operator3.test",
            			true, encodedPassword,
            			LocalDateTime.now(),
            			"operator3",
            			Role.OPERATOR
            			);
        		Profile operator3Profile = new Profile("Caglio", "Rew", operator3);
                userRepository.save(operator3);
                profileRepository.save(operator3Profile);
                
                User operator4 = new User(
            			LocalDateTime.now(),
            			"operator4@operator4.test",
            			true, encodedPassword,
            			LocalDateTime.now(),
            			"operator4",
            			Role.OPERATOR
            			);
        		Profile operator4Profile = new Profile("Sempronio", "Bit", operator4);
                userRepository.save(operator4);
                profileRepository.save(operator4Profile);
            }
            
            if (categoryRepository.count() == 0) {
        		Category category1 = new Category("Servizi");
        		Category category2 = new Category("Sviluppo");
        		Category category3 = new Category("Amministrativo");
        		
        		categoryRepository.save(category1);
        		categoryRepository.save(category2);
        		categoryRepository.save(category3);
        	}
            
            System.out.println("Database seeding completed.");
        };
    }
}
