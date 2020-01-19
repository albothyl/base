package base.interfaces.member.advice;

import base.interfaces.member.exception.ErrorsSerializerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
@Slf4j
public class MemberCreateControllerAdvice {

    @ExceptionHandler(ErrorsSerializerException.class)
    public ResponseEntity<String> memberSignUpErrorsSerializeError() {
        String message = "member signup valid error";

        log.error(message + ": signup errors object serialize error");
        return ResponseEntity.badRequest().body(message);
    }
}
