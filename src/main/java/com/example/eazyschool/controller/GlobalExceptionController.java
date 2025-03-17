package com.example.eazyschool.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


/*
@ControllerAdvice is a specialization of the @Component annotation which allows to handle
exceptions across the whole application in one global handling component. It can be viewed
as an interceptor of exceptions thrown by methods annotated with @RequestMapping and similar.
* */

@Slf4j
@ControllerAdvice
public class GlobalExceptionController {
    /*
        @ExceptionHandler will register the given method for a given
        exception type, so that ControllerAdvice can invoke this method
        logic if a given exception type is thrown inside the web application.
    */

    /*

        Exception class is the global class of all the exceptions there it will handle all the exception
        if no specific exception handler is mentioned for that given error.

     */

//    @ExceptionHandler(NullPointerException.class, ArrayIndexOutOfBoundsException.class)
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(Exception exception){
        ModelAndView errorPage = new ModelAndView();
        errorPage.setViewName("error");
        errorPage.addObject("errormsg",exception.getMessage());
        return errorPage;
    }
}
