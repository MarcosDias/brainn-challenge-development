package com.mdias.javaspringpostgresproject.exception;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorData {
	private int status;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime timestamp;
	private String message;
	
	public ErrorData(HttpStatus status, String message) {
		this(status.value(), LocalDateTime.now(), message);
	}
}
