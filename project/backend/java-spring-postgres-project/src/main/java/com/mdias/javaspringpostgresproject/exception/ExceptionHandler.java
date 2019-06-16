package com.mdias.javaspringpostgresproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler({ 
		ResourceNotFound.class,
		UserNotFoundGitHubException.class 
	})
	public ResponseEntity<ErrorData> userNotFoundGitHub(Exception ex) {
		return new ResponseEntity<>(new ErrorData(HttpStatus.NOT_FOUND, ex.getMessage()), HttpStatus.NOT_FOUND);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	protected ResponseEntity<ErrorData> defaultError(Exception ex) {
		log.error("[ defaultError ] - Erro: {}", ex);
		return new ResponseEntity<>(new ErrorData(HttpStatus.INTERNAL_SERVER_ERROR, "Sorry, unexpected error! :("),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
