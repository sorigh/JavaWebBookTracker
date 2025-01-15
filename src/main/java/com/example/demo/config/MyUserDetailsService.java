package com.example.demo.config;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Slf4j //lombok, logger (console or file)
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userByUsername = userRepository.findByUsername(username);

        if (userByUsername.isEmpty()) {
            log.error("Could not find user with that username: {}", username);
            throw new UsernameNotFoundException("Invalid credentials!");
        }

        User user = userByUsername.get();

        if (!user.getUsername().equals(username)) {
            log.error("Could not find user with that username: {}", username);
            throw new UsernameNotFoundException("Invalid credentials!");
        }

        //roluri
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        user.getRoles().stream()
                .forEach(authority -> grantedAuthorities.add(new SimpleGrantedAuthority(authority.getRole())));

        return new MySecurityUser(user.getUsername(), user.getPassword(), true, true, true, true, grantedAuthorities,
                user.getFirstName(), user.getLastName(), user.getEmailAddress(), user.getBirthdate());
    }
}