package edu.lucasrech.catalog_authentication.controller;

import edu.lucasrech.catalog_authentication.configuration.security.TokenService;
import edu.lucasrech.catalog_authentication.exception.UserNotFoundExcepiton;
import edu.lucasrech.catalog_authentication.model.user.*;
import edu.lucasrech.catalog_authentication.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor()
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @Operation(summary = "Efetua o login de um usuário administrador e caso autenticado retorna um token.", method = "POST")
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

    @Operation(summary = "Registra um novo usuário administrador. Requer token de autenticação", method = "POST")
    @RequestMapping("/register")
    public ResponseEntity<LoginResponseDTO> register(@RequestBody @NonNull RegisterRequestDTO body) {
        if(userRepository.findByUsername(body.username()).isPresent()) {
            return ResponseEntity.badRequest().body(new LoginResponseDTO(null, null));
        }
        if(userRepository.findByEmail(body.email()).isPresent()) {
            return ResponseEntity.badRequest().body(new LoginResponseDTO(null, null));
        }

        User user = new User(body.username(), body.email(), passwordEncoder.encode(body.password()));
        userRepository.save(user);

        String token = tokenService.generateToken(user);
        return ResponseEntity.ok(new LoginResponseDTO(user.getUsername(), token));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser (@RequestBody DeleteRequestDTO userToDelete) throws UserNotFoundExcepiton {
        if(userRepository.findByEmail(userToDelete.email()).isEmpty()) {
            throw new UserNotFoundExcepiton("User not found.");
        }
        User user = userRepository.findByEmail(userToDelete.email()).get();
        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }





}
