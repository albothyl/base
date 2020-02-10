package base.interfaces;

import base.domain.support.exception.BusinessException;
import base.support.errors.exception.ErrorsSerializerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public String global(Exception e) {
		log.error("Exception : {}", e.getMessage(), e);
		return "Exception";
	}

	@ExceptionHandler(ErrorsSerializerException.class)
	public ResponseEntity<String> memberSignUpErrorsSerializeError(ErrorsSerializerException e) {
		String message = "member signup valid error";

		log.error("ErrorsSerializerException : {}", e.getMessage());
		return ResponseEntity.badRequest().body(message);
	}

	@ExceptionHandler(BusinessException.class)
	public String handleDomainException(BusinessException e) {
		log.error("DomainLayerException : {}", e.getMessage(), e);
		return e.getMessage();
	}

}
