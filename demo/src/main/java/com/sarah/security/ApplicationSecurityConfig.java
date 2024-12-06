package com.sarah.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ApplicationSecurityConfig{
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/index.html").permitAll()
                .anyRequest()
                .authenticated()
                )
                .httpBasic(httpBasic -> httpBasic
                        .realmName("CustomerAppRealm")
                );

        return http.build();
    }
}
