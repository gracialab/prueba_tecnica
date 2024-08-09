package com.example.pruebaTecnicaGlab.config;


  
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.example.pruebaTecnicaGlab.service.CustomUserDetailsService;
import static org.springframework.security.config.Customizer.withDefaults;





@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
            .requestMatchers("/api/save").authenticated() // Solo autenticado .authenticated()
            .requestMatchers("/api/getById/**").permitAll() // Permitir acceso sin autenticación
            .requestMatchers("/api/getAll").permitAll() // Permitir acceso sin autenticación
            .requestMatchers("/api/update/**").permitAll() // Solo autenticado
            .requestMatchers("/api/delete/**").permitAll() // Solo autenticado
            .requestMatchers("/api/register").permitAll() // Permitir acceso sin autenticación
            .requestMatchers("/api/login").permitAll() // Permitir acceso sin autenticación
            .anyRequest().authenticated() // Cualquier otra solicitud requiere autenticación
            )
            .httpBasic(withDefaults()) // Habilita la autenticación básica
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/index", true)
                .permitAll()
            )
            .logout(logout -> logout
                .permitAll()
            )
            .csrf(csrf -> csrf
                .disable() // Solo para desarrollo; en producción, se habilita CSRF
            );

        // Configuración de UserDetailsService
        http.userDetailsService(customUserDetailsService);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
