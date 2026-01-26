package com.notes.demo.SecurityConfig;

import com.notes.demo.service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final MyUserDetailsService myUserDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(myUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .authenticationProvider(daoAuthenticationProvider())
                .authorizeHttpRequests(auth -> auth
                        // ADMIN
                        .requestMatchers(
                                "/api/v1/notes/get-all",
                                "/api/v1/notes/block-user"
                        ).hasAuthority("ADMIN")

                        // Public
                        .requestMatchers(
                                "/api/v1/notes/login",
                                "/api/v1/notes/logout",
                                "/api/v1/notes/register",
                                "/api/v1/notes/fetch-notes",
                                "/api/v1/notes/add-note/{userId}",
                                "/api/v1/notes/update-note/{id}",
                                "/api/v1/notes/delete-note/{id}",
                                "/api/v1/notes/get-note/{id}",
                                "/api/v1/users/get-all",
                                "/api/v1/users/add-user",
                                "/api/v1/users/edit-user/{id}",
                                "/api/v1/users/delete-user/{id}",
                                "/api/v1/users/find-user/{id}"
                        ).permitAll()

                        // Authenticated
                        .requestMatchers(
//                                "/api/v1/note/get-all-notes",
//                                "/api/v1/note/add-note",
//                                "/api/v1/note/update-note",
                                "/api/v1/notes/delete-note"
                        ).authenticated()


                        .anyRequest().authenticated()
                )
                .logout(l -> l
                        .logoutUrl("/api/v1/notes/logout")
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                )
                .httpBasic(withDefaults());

        return http.build();
    }

}
