package edu.lucasrech.catalog_authentication.service;

import edu.lucasrech.catalog_authentication.model.UserAuthenticated;
import edu.lucasrech.catalog_authentication.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
    private final UserRepository userRepository;
    public UserDetailsServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            return userRepository.findByUsername(username)
                    .map(UserAuthenticated::new)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
