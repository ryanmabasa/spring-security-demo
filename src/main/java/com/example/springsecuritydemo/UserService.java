package com.example.springsecuritydemo;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/*
* As it turns out, Spring Security offers several out-of-the-box implementations of UserDetailsService,
* including the following:

An in-memory user store
A JDBC user store
An LDAP user store
* */
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException(username));
        return user;
    }
}
