package edu.lucasrech.catalog_authentication.controller;

import edu.lucasrech.catalog_authentication.configuration.security.TokenService;
import edu.lucasrech.catalog_authentication.exception.InvalidValueException;
import edu.lucasrech.catalog_authentication.exception.NullValueException;
import edu.lucasrech.catalog_authentication.exception.UserNotFoundExcepiton;
import edu.lucasrech.catalog_authentication.model.user.*;
import edu.lucasrech.catalog_authentication.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor()
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @Operation(summary = "Efetua o login de um usuário administrador e caso autenticado retorna um token.", method = "POST")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO body) throws InvalidValueException {
        User user = userService.findByUsername(body.username())
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));
        if (passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = tokenService.generateToken(user);
            return ResponseEntity.ok(new LoginResponseDTO(user.getUsername(), token));
        } else {
            throw new InvalidValueException("Password doesn't match.");
        }
    }

    @Operation(summary = "Registra um novo usuário administrador. Requer token de autenticação", method = "POST")
    @RequestMapping("/register")
    public ResponseEntity<LoginResponseDTO> register(@RequestBody @NonNull RegisterRequestDTO body) throws InvalidValueException, NullValueException {
        User user = new User(body.username(), body.email(), passwordEncoder.encode(body.password()));
        userService.saveUser(user);
        String token = tokenService.generateToken(user);
        return ResponseEntity.ok(new LoginResponseDTO(user.getUsername(), token));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser (@RequestBody DeleteRequestDTO userToDelete) throws UserNotFoundExcepiton {
        userService.deleteUser(userToDelete);
        return ResponseEntity.ok().build();
    }





}
