package com.eduflix.eduflix.ExceptionHandler;

import com.eduflix.eduflix.Exceptions.AlreadyUserExistsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerClass {

    @ExceptionHandler(AlreadyUserExistsException.class)
    public String handleAlreadyUserExistsException(AlreadyUserExistsException ex){
        return ex.getMessage();
    }
}
