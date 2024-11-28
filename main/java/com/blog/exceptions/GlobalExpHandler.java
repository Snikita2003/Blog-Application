package com.blog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.blog.payloads.*;

@RestControllerAdvice
public class GlobalExpHandler {

	@ExceptionHandler( ResourceNotFoundException.class)
	public ResponseEntity< ApiResponce > resNotFoundExpHandler( ResourceNotFoundException ex)
	{
		String msg=ex.getMessage();
		ApiResponce apiResponce=new ApiResponce(msg,false);
		return new ResponseEntity<ApiResponce>(apiResponce , HttpStatus.NOT_FOUND );
		
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethodArgsNotValidExp(MethodArgumentNotValidException  ex)
	{
		Map<String, String> errors=new HashMap<String, String>();
		ex.getBindingResult().getAllErrors().forEach((e)->{
			
			String fname=  (( FieldError) e).getField() ;
			String msg=e.getDefaultMessage();
			errors.put(fname,msg);
		});
		
		
		return new ResponseEntity<Map<String,String>>(errors,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ApiResponce> apiExceptionHandler(ApiException ex)
	{
		String message = ex.getMessage();  // jo apiResponse ka exception ke bad message hoga isme aa jayega 
		ApiResponce apiResponse = new ApiResponce(message , false);
		return new ResponseEntity<ApiResponce>(apiResponse , HttpStatus.BAD_REQUEST);
	}
}

















