package com.Host_Life_Cycle_Management.Host_Life_Cycle_Management.config;



import com.Host_Life_Cycle_Management.Host_Life_Cycle_Management.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Autorisieren von HTTP-Anfragen
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/", "/index", "/login", "/register", "/css/**").permitAll()
                        .anyRequest().authenticated()
                )
                // Konfiguration der Formular-basierten Anmeldung
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/greeting", true)
                        .successHandler(new SimpleUrlAuthenticationSuccessHandler("/greeting"))
                        .permitAll()
                )
                // Konfiguration des Logout-Verhaltens
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .permitAll()
                );

        return http.build();
    }
}