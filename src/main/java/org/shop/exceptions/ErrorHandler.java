package org.shop.exceptions;

import lombok.extern.log4j.Log4j2;
import org.shop.customer.CustomerController;
import org.shop.dtos.ErrorDto;
import org.shop.invoice.InvoiceController;
import org.shop.item.ItemController;
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
