package edu.lucasrech.catalog_authentication.exception.handler;


import edu.lucasrech.catalog_authentication.dto.ErrorResponseDTO;
import edu.lucasrech.catalog_authentication.exception.UserNotFoundExcepiton;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundExcepiton.class)
    public ResponseEntity<ErrorResponseDTO> userNotFound(UserNotFoundExcepiton ex) {
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO(ex.getMessage(), "404"));
    }
}
