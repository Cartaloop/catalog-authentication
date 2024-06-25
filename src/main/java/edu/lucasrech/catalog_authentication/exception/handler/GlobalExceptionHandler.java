package edu.lucasrech.catalog_authentication.exception.handler;


import edu.lucasrech.catalog_authentication.exception.dto.ErrorResponseDTO;
import edu.lucasrech.catalog_authentication.exception.InvalidValueException;
import edu.lucasrech.catalog_authentication.exception.NullValueException;
import edu.lucasrech.catalog_authentication.exception.UserNotFoundExcepiton;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundExcepiton.class)
    public ResponseEntity<ErrorResponseDTO> userNotFound(UserNotFoundExcepiton ex) {
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO(ex.getMessage(), "404"));
    }

    @ExceptionHandler(InvalidValueException.class)
    public ResponseEntity<ErrorResponseDTO> invalidValue(InvalidValueException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO(ex.getMessage(), "400"));
    }

    @ExceptionHandler(NullValueException.class)
    public ResponseEntity<ErrorResponseDTO> nullValue(NullValueException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO(ex.getMessage(), "400"));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> usernameNotFound(UsernameNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponseDTO(ex.getMessage(), "401"));
    }
}
