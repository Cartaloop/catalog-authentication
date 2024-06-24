package edu.lucasrech.catalog_authentication.controller;

import edu.lucasrech.catalog_authentication.configuration.security.TokenService;
import edu.lucasrech.catalog_authentication.model.user.LoginRequestDTO;
import edu.lucasrech.catalog_authentication.model.user.LoginResponseDTO;
import edu.lucasrech.catalog_authentication.model.user.RegisterRequestDTO;
import edu.lucasrech.catalog_authentication.model.user.User;
import edu.lucasrech.catalog_authentication.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor()
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO body) {
        User user = userRepository.findByUsername(body.username())
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));
        if (passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = tokenService.generateToken(user);
            return ResponseEntity.ok(new LoginResponseDTO(user.getUsername(), token));
        } else {
            return ResponseEntity.badRequest().body(new LoginResponseDTO(null, null));
        }
    }

    @RequestMapping("/register")
    public ResponseEntity<LoginResponseDTO> register(@RequestBody @NonNull RegisterRequestDTO body) {
        if(userRepository.findByUsername(body.username()).isPresent()) {
            return ResponseEntity.badRequest().body(new LoginResponseDTO(null, null));
        }
        if(userRepository.findByEmail(body.email()).isPresent()) {
            return ResponseEntity.badRequest().body(new LoginResponseDTO(null, null));
        }

        User user = new User(body.username(), body.email(), passwordEncoder.encode(body.password()), body.role());
        userRepository.save(user);

        String token = tokenService.generateToken(user);
        return ResponseEntity.ok(new LoginResponseDTO(user.getUsername(), token));
    }





}
