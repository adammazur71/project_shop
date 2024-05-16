package org.example.exceptions;

import lombok.extern.log4j.Log4j2;
import org.example.customer.CustomerController;
import org.example.dtos.ErrorDto;
import org.example.invoice.InvoiceController;
import org.example.item.ItemController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(assignableTypes = {ItemController.class, CustomerController.class, InvoiceController.class})
@Log4j2
public class ErrorHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleNotFoundException(NotFoundException exception) {
        log.info(exception.getMessage() + ", STATUS: " + HttpStatus.NOT_FOUND);
        return new ErrorDto(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ErrorDto handleValidationException(ValidationException exception) {
        log.info(exception.getMessage() + ", STATUS: " + HttpStatus.NOT_ACCEPTABLE);
        return new ErrorDto(exception.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }


}
