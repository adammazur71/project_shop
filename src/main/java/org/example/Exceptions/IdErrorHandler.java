package org.example.Exceptions;

import lombok.extern.log4j.Log4j2;
import org.example.Dtos.IdNotFoundDto;
import org.example.ShopController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(assignableTypes = ShopController.class)
@Log4j2
public class IdErrorHandler {
    @ExceptionHandler(IdNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public IdNotFoundDto handleException(IdNotFoundException exception){
        log.error(exception.getMessage() + ", STATUS: " + HttpStatus.NOT_FOUND + ": " + HttpStatus.NOT_FOUND.value());
        return new IdNotFoundDto(exception.getMessage(), HttpStatus.NOT_FOUND);
    }


}
