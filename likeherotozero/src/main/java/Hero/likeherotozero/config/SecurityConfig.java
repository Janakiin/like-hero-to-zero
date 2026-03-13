package Hero.likeherotozero.config;

import Hero.likeherotozero.model.AppUser;
import Hero.likeherotozero.repository.UserRepository;
import Hero.likeherotozero.service.DbUserDetailsService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final DbUserDetailsService userDetailsService;

    public SecurityConfig(DbUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/emissions/add").authenticated() // nur angemeldete User
                        .anyRequest().permitAll() // alles andere öffentlich
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout(logout -> logout.permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner init(UserRepository repo, PasswordEncoder encoder) {
        return args -> {
            if (repo.findByUsername("wissenschaftler") == null) {
                AppUser user = new AppUser();
                user.setUsername("wissenschaftler");
                user.setPassword(encoder.encode("password"));
                user.setRole("ROLE_USER");
                repo.save(user);
            }
        };
    }
}
