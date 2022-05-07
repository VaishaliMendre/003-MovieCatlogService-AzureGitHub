package com.cg.exception;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class MovieServiceErrorAdvise 
{
	/*@ResponseBody
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = {Exception.class})
	protected ErrorInfo handleConflict(Exception ex, HttpServletRequest req) {
		
		System.out.println(".....handleConflict");
		String bodyOfResponse = ex.getMessage();// "Country with this id not present";
		String uri = req.getRequestURL().toString();
		return  new ErrorInfo(uri,bodyOfResponse);
	}	*/
	@ExceptionHandler({MovieNotFoundException.class, 
		SQLException.class, NullPointerException.class})
	public ResponseEntity<String> handle1(MovieNotFoundException ue) 
	{
		System.out.println(".....handle1");
		return error(HttpStatus.INTERNAL_SERVER_ERROR, ue);
	}		
	private ResponseEntity<String> error(HttpStatus status, Exception e)
	{
		System.out.println(".....error");
		return ResponseEntity.status(status).body(e.getMessage());
	}

}
