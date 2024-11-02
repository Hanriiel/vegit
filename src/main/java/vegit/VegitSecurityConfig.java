package vegit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

import vegit.web.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity // Käytetään Web Securityä, joka riittää tähän tarkoitukseen
public class VegitSecurityConfig {

    private final UserDetailServiceImpl userDetailsService;

    public VegitSecurityConfig(UserDetailServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers( "/styles.css").permitAll() 
                        // Reseptit: GET sallittu kaikille, muut vain adminille
                        .requestMatchers(HttpMethod.GET, "/api/recipes/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/recipes/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/recipes/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/recipes/**").hasRole("ADMIN")
                        // Tuotteet: GET sallittu kaikille, muut vain adminille
                        .requestMatchers(HttpMethod.GET, "/api/products/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/products/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/products/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/products/**").hasRole("ADMIN")
                        // Arvostelut: DELETE sallittu vain adminille, muut kaikille
                        .requestMatchers(HttpMethod.GET, "/api/reviews/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/reviews/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/api/reviews/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.DELETE, "/api/reviews/**").hasRole("ADMIN")
                        // Thymeleaf: vain adminille rajoitetut näkymät
                        .requestMatchers("/addproduct", "/addrecipe", "/editproduct", "/editrecipe").hasRole("ADMIN")
                        .anyRequest().authenticated() // Mitään ei voi tehdä kirjautumatta sisään
                        )
                        .httpBasic(withDefaults())
                        .formLogin(formLogin -> formLogin
                            .loginPage("/login")
                            .defaultSuccessUrl("/index", true) // index-sivulle kirjautumisen jälkeen
                            .permitAll()) // kaikille pääsy loginiin
                        .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean // Nykyaikaisempi toteutus Beanilla
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class); // Luodaan AuthenticationManagerBuilder
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder()); // UserDetailsService ja password encoder autentikaatiota varten
        return authenticationManagerBuilder.build(); // rakennetaan & palautetaan AuthenticationManager
    }
}
