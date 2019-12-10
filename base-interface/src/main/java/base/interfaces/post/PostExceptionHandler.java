package base.interfaces.post;

import base.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class PostExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public String ResourceNotFound(Exception ex) {
        log.error("ResourceNotFound : {}", ex.getMessage());
        return ex.getMessage();
    }
}
