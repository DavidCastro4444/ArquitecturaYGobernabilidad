package com.security;

import com.repositoy.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // For testing purposes, return a hardcoded user
        if (username.equals("testuser")) {
            return org.springframework.security.core.userdetails.User
                    .withUsername("testuser")
                    .password("testpassword")
                    .roles("USER")
                    .build();
        }

        // If the provided username is not the test user, throw an exception
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}


