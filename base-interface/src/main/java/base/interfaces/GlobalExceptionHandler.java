package base.interfaces;

import base.domain.support.exception.DomainLayerException;
import lombok.extern.slf4j.Slf4j;
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

	@ExceptionHandler(DomainLayerException.class)
	public String handleDomainException(DomainLayerException e) {
		log.error("DomainLayerException : {}", e.getMessage(), e);
		return e.getMessage();
	}

}
