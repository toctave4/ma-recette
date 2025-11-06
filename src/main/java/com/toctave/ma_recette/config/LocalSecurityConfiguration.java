package com.toctave.ma_recette.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Slf4j
@Profile("local")
public class LocalSecurityConfiguration {
    @Bean
    public SecurityFilterChain localFilterChain(HttpSecurity http) throws Exception {
        log.info("Utilisation du profile non-securisé pour le développement local.");
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authz -> authz
                        .anyRequest().permitAll()
                )
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
