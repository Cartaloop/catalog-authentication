package edu.lucasrech.catalog_authentication.exception.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.lucasrech.catalog_authentication.dto.ErrorResponseDTO;
import edu.lucasrech.catalog_authentication.exception.EntityIdNotFoundException;
import edu.lucasrech.catalog_authentication.exception.ValueExpectedException;
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

    @ResponseBody
    @ExceptionHandler(ValueExpectedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO valueException(ValueExpectedException e) {
        String stringOfValues = String.join(", ", e.getRequest());
        return new ErrorResponseDTO(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                stringOfValues
        );
    }
}
