package com.example.springsecuritydemo;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    /*
    * No matter which password encoder you use, it’s important to understand that the password in the database is never decoded.
    * Instead, the password that the user enters at login is encoded using the same algorithm,
    * and it’s then compared with the encoded password in the database. That comparison is performed in the PasswordEncoder’s matches() method.
    * */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/resources/**", "/signup", "/about").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/db/**").access(new WebExpressionAuthorizationManager("hasRole('ADMIN') and hasRole('DBA')"))
                .anyRequest().denyAll()
        );

        return http
                .authorizeRequests()
                .antMatchers("/design", "/orders").access("hasRole('USER')")
                .antMatchers("/", "/**").access("permitAll()")
                .and()
                .formLogin()
                .loginPage("/login")
                .and()
                .build();
    }

/*
Use of in memory spring security database

@Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        List<UserDetails> usersList = new ArrayList<>();
        usersList.add(new User(
                "user", encoder.encode("user"),
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));

        return new InMemoryUserDetailsManager(usersList);
    }*/
}
