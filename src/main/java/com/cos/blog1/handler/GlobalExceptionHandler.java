package com.cos.blog1.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog1.dto.ResponseDto;

@ControllerAdvice//모든 익셉션을 가져온다.
@RestController//데이터처리
public class GlobalExceptionHandler {

	@ExceptionHandler(value = Exception.class) //IllegalArgumentException 발생 하였을때 메소드실행
	public ResponseDto<String>handleArgumentException(Exception e) {
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage()); //500
	}
}
