package com.javaweb.controlleradvice;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.javaweb.beans.ErrorResponseDTO;
import com.javaweb.customeExceptions.FiledRequiredException;
import com.javaweb.customeExceptions.UnauthorizedException;

import java.text.ParseException;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.KeyLengthException;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler(FiledRequiredException.class)
	public ResponseEntity<Object> handlerLoginRequiredException (FiledRequiredException ex, WebRequest request){
		ErrorResponseDTO errorResponseDTO  = new ErrorResponseDTO();
		errorResponseDTO.setError(ex.getMessage());
		List<String> details = new ArrayList<>();
		details.add("Bạn phải nhập các trường bắt buộc!");
		errorResponseDTO.setDetails(details);
		return new ResponseEntity<>(errorResponseDTO,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<Object> handlerUnauthorizedException (UnauthorizedException ex, WebRequest request){
		ErrorResponseDTO errorResponseDTO  = new ErrorResponseDTO();
		errorResponseDTO.setError(ex.getMessage());
		List<String> details = new ArrayList<>();
		details.add("Thông tin người dùng chưa đăng nhập vui lòng đăng nhập lại!");
		errorResponseDTO.setDetails(details);
		return new ResponseEntity<>(errorResponseDTO,HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(ParseException.class)
	public ResponseEntity<Object> handlerParseException (ParseException ex, WebRequest request){
		ErrorResponseDTO errorResponseDTO  = new ErrorResponseDTO();
		errorResponseDTO.setError(ex.getMessage());
		List<String> details = new ArrayList<>();
		details.add("Token xác thực không hợp lệ");
		errorResponseDTO.setDetails(details);
		return new ResponseEntity<>(errorResponseDTO,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(JOSEException.class)
	public ResponseEntity<Object> handlerJOSEEException (JOSEException ex, WebRequest request){
		ErrorResponseDTO errorResponseDTO  = new ErrorResponseDTO();
		errorResponseDTO.setError(ex.getMessage());
		List<String> details = new ArrayList<>();
		details.add("Token xác thực không hợp lệ");
		errorResponseDTO.setDetails(details);
		return new ResponseEntity<>(errorResponseDTO,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(KeyLengthException.class)
	public ResponseEntity<Object> handlerKeyLenghtException (KeyLengthException ex, WebRequest request){
		ErrorResponseDTO errorResponseDTO  = new ErrorResponseDTO();
		errorResponseDTO.setError(ex.getMessage());
		List<String> details = new ArrayList<>();
		details.add("Token xác thực không hợp lệ");
		errorResponseDTO.setDetails(details);
		return new ResponseEntity<>(errorResponseDTO,HttpStatus.BAD_REQUEST);
	}
	
	
	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setError(ex.getFieldError().getDefaultMessage());
        List<String> details = new ArrayList<>();
		details.add("Vui lòng xem kỹ yêu cầu nhập vào!");
		errorResponseDTO.setDetails(details);
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }
	
}
