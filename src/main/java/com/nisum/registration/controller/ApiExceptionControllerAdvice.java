package com.nisum.registration.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.registration.dto.StatusDto;
import com.nisum.registration.dto.response.ResponseHeaderDto;
import com.nisum.registration.exception.UserException;
import com.nisum.registration.handler.UserHandler;

@ControllerAdvice(annotations = RestController.class)
public class ApiExceptionControllerAdvice {

	private static final Logger logger = LoggerFactory.logger(ApiExceptionControllerAdvice.class);

	private static final String INTERNAL_ERROR = "Internal error::";
	private final UserHandler userHandler;

	public ApiExceptionControllerAdvice(UserHandler userHandler) {
		this.userHandler = userHandler;
	}

	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseHeaderDto handleException(Exception ex) {

		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw));

		logger.info(INTERNAL_ERROR
				.concat(ex.getMessage().isEmpty() ? Arrays.toString(ex.getStackTrace()) : ex.getMessage()));
		return createDefaultError(Collections.singletonList(ex.getMessage()));
	}

	public ResponseHeaderDto createDefaultError(List<String> errors) {

		ResponseHeaderDto response = new ResponseHeaderDto();
		response.setStatus(StatusDto.ERROR);
		response.setErrors(errors);

		return response;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ UserException.class })
	@ResponseBody
	public ResponseHeaderDto transferOfFundsException(Exception ex) {
		logger.info(INTERNAL_ERROR
				.concat(ex.getMessage().isEmpty() ? Arrays.toString(ex.getStackTrace()) : ex.getMessage()));
		return userHandler.createUserFailedResponse(Collections.singletonList(ex.getMessage()));
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({ IOException.class })
	@ResponseBody
	public ResponseHeaderDto internalServerException(Exception ex) {
		logger.info(INTERNAL_ERROR
				.concat(ex.getMessage().isEmpty() ? Arrays.toString(ex.getStackTrace()) : ex.getMessage()));
		return userHandler.createUserFailedResponse(Collections.singletonList(ex.getMessage()));
	}
}