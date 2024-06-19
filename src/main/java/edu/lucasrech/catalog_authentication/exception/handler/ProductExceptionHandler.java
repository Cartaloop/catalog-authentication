package edu.lucasrech.catalog_authentication.exception.handler;

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
                "404",
                e.getRequest()
        );
    }

    @ResponseBody
    @ExceptionHandler(ValueExpectedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO valueException(ValueExpectedException e) {
        String[] formatedArray = new String[3];
        for(int i = 0; i <= 3; i++) {
            switch (i){
                case 0: formatedArray[i] = "Name: " + e.getRequest()[i]; break;
                case 1: formatedArray[i] = "Description: " + e.getRequest()[i]; break;
                case 2: formatedArray[i] = "Price: " + e.getRequest()[i]; break;
            }

        }
        String stringOfValues = String.join(", ", formatedArray);
        return new ErrorResponseDTO(
                e.getMessage(),
                "400",
                stringOfValues
        );
    }
}
