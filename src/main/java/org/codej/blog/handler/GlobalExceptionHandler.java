package org.codej.blog.handler;

import org.codej.blog.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice//모든 익셉션이 발생하면 여기로 들어온다.
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)//Exception을 여기서 잡아준다.
    public ResponseDTO<String> handleArgumentException(Exception e){
        return new ResponseDTO<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
    }

}
