package in.achyuta.exception;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class AppExceptionHandler {
	
	private Logger logger=LoggerFactory.getLogger(AppExceptionHandler.class);
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<AppException> handleException(String exceptionMsg){
		logger.error("Exception Occured : "+exceptionMsg);
		AppException ex= new AppException();
		ex.setExceptionCode("CD2001");
		ex.setExceptionDesc(exceptionMsg);
		ex.setExceptionDate(LocalDateTime.now());
		
		return new ResponseEntity<>(ex,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
