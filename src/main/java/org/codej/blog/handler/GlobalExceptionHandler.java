package org.codej.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice//모든 익셉션이 발생하면 여기로 들어온다.
public class GlobalExceptionHandler {

    @ExceptionHandler(value = IllegalArgumentException.class)//illegalException을 여기서 잡아준다.
    public String handleArgumentException(IllegalArgumentException e){
        return "<h1>"+e.getMessage()+"</h1>";
    }
}
