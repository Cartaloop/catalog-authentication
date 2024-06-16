package edu.lucasrech.catalog_authentication.exception;

import edu.lucasrech.catalog_authentication.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ProductExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    public ErrorResponseDTO entityNotFound(EntityIdNotFoundException e) {
        return new ErrorResponseDTO(
                e.getMessage(),
                HttpStatus.NOT_FOUND,
                e.getRequest()
        );
    }
}
