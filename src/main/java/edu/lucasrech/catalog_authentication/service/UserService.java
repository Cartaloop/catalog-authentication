package edu.lucasrech.catalog_authentication.service;

import edu.lucasrech.catalog_authentication.exception.InvalidValueException;
import edu.lucasrech.catalog_authentication.exception.NullValueException;
import edu.lucasrech.catalog_authentication.exception.UserNotFoundExcepiton;
import edu.lucasrech.catalog_authentication.model.user.DeleteRequestDTO;
import edu.lucasrech.catalog_authentication.model.user.RegisterRequestDTO;
import edu.lucasrech.catalog_authentication.model.user.User;
import edu.lucasrech.catalog_authentication.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Optional<User> findByEmail(String email) throws NullValueException, InvalidValueException {
        if(email == null) {
            throw new NullValueException();
        } else if(!email.matches(regex)) {
            throw new InvalidValueException(email);
        }
        return userRepository.findByEmail(email);
    }

    public Optional<User> findByUsername(String username) {
        if(username.isEmpty() || userRepository.findByUsername(username).isEmpty()) {
            throw new UsernameNotFoundException("User" + username + "not found");
        }
        return userRepository.findByUsername(username);
    }

    public User saveUser(User newUser) throws NullValueException, InvalidValueException {
        if(newUser == null) {
            throw new NullValueException();
        } else if (!newUser.getEmail().matches(regex)) {
            throw new InvalidValueException("Invalid email: " + newUser.getEmail());
        } else if(userRepository.findByEmail(newUser.getEmail()).isPresent() ||
                userRepository.findByUsername(newUser.getUsername()).isPresent()) {
            throw new InvalidValueException("User " + newUser.getUsername() + " already exists");
        }

        return userRepository.save(newUser);
    }

    public void deleteUser(DeleteRequestDTO userToDelete) throws UserNotFoundExcepiton {
        if(userRepository.findByEmail(userToDelete.email()).isEmpty()) {
            throw new UserNotFoundExcepiton("User not found.");
        }
        User user = userRepository.findByEmail(userToDelete.email()).get();
        userRepository.delete(user);
    }
}
