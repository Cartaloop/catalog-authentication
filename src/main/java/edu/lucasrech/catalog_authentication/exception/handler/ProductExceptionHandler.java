package edu.lucasrech.catalog_authentication.exception.handler;

import edu.lucasrech.catalog_authentication.dto.ErrorResponseDTO;
import edu.lucasrech.catalog_authentication.exception.EntityIdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ProductExceptionHandler {

    @ResponseBody
    @ExceptionHandler(EntityIdNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDTO entityNotFound(EntityIdNotFoundException e) {
        return new ErrorResponseDTO(
                e.getMessage(),
                HttpStatus.NOT_FOUND,
                e.getRequest()
        );
    }
}
