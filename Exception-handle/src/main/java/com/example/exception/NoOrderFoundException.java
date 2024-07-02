package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NoOrderFoundException extends RuntimeException {

	String orederName;
	String orderType;
	Long OrderValue;

	public NoOrderFoundException(String orederName, String orderType, Long orderValue) {
		super(String.format("%s not found with %s: %s", orederName, orderType, orderValue));
		this.orederName = orederName;
		this.orderType = orderType;
		this.OrderValue = orderValue;
	}

}
