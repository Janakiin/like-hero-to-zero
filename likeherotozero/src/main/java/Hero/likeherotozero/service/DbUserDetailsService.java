package Hero.likeherotozero.service;

import Hero.likeherotozero.model.AppUser;
import Hero.likeherotozero.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DbUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public DbUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser User = userRepository.findByUsername(username);
        if (User == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(User.getUsername())
                .password(User.getPassword())
                .roles(User.getRole().replace("ROLE_", "")) // Spring Security erwartet ohne ROLE_ hier
                .build();
    }
}