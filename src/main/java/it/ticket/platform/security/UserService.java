package it.ticket.platform.security;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import it.ticket.platform.model.User;
import it.ticket.platform.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    //private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> userOpt = userRepository.findByUsername(username);
		
		if(userOpt.isPresent()) {
			DatabaseUserDetails userDetails = new DatabaseUserDetails(userOpt.get());
			return userDetails;
		} else {
			throw new UsernameNotFoundException("Username not found");
		}
	}
    
    public void updateUserAvailable(Long userId, boolean isAvailable) {
    	
    	Optional<User> userOpt = userRepository.findById(userId);
		
		if(userOpt.isPresent()) {
			User user = userOpt.get();
			
			user.setAvailable(isAvailable);
			userRepository.save(user);
			
		} else {
			throw new UsernameNotFoundException("User not found");
		}
    	
    }
    
//    List<User> findByIsAvailableTrue() {
//    	return userRepository.findByIsAvailableTrue();
//    }
    /*
    public User registerUser(String username, String email, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(
    			LocalDateTime.now(),
    			email,
    			true, encodedPassword,
    			LocalDateTime.now(),
    			username,
    			Role.OPERATOR
    			);
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    */
}
