package com.gestion_tickets.config;

import com.gestion_tickets.models.User;
import com.gestion_tickets.repositories.UserRepositorie;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserInfoDetailsService implements UserDetailsService {

    private final UserRepositorie userRepositorie;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepositorie.findByEmail(email);
        if (user != null) {
            System.out.println("User found: " + user.getEmail()); // Log de vérification
            return new UserInfoDetails(user);
        }
        throw new UsernameNotFoundException("User not found");

    }
}
