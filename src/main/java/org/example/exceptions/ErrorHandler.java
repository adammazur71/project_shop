package org.example.exceptions;

import lombok.extern.log4j.Log4j2;
import org.example.dtos.IdNotFoundDto;
import org.example.dtos.ItemNotFoundDto;
import org.example.item.ItemController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(assignableTypes = ItemController.class)
@Log4j2
public class ErrorHandler {
    @ExceptionHandler(IdNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public IdNotFoundDto handleIdNotFoundException(IdNotFoundException exception){
        log.info(exception.getMessage() + ", STATUS: " + HttpStatus.NOT_FOUND);
        return new IdNotFoundDto(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ItemNotFoundDto handleItemNotFoundException(ItemNotFoundException exception){
        log.info(exception.getMessage() + ", STATUS: " + HttpStatus.NOT_FOUND);
        return new ItemNotFoundDto(exception.getMessage(), HttpStatus.NOT_FOUND);
    }


}
