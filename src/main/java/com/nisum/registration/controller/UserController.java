package com.nisum.registration.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.registration.dto.request.UserRequestDto;
import com.nisum.registration.dto.response.ResponseHeaderDto;
import com.nisum.registration.exception.UserException;
import com.nisum.registration.handler.UserHandler;
import com.nisum.registration.service.IUserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/registration/user")
public class UserController {

	private static final Logger logger = LoggerFactory.logger(UserController.class);

	private final IUserService iUserService;
	private final UserHandler userHandler;

	public UserController(IUserService iUserService, UserHandler userHandler) {
		this.iUserService = iUserService;
		this.userHandler = userHandler;
	}

	@PostMapping(value = "/", consumes = { "application/JSON" }, produces = { "application/JSON" })
	@ApiOperation(value = "Register person", notes = "This method registry a person")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Save user correctly."),
			@ApiResponse(code = 400, message = "Internal server error") })
	public ResponseEntity<ResponseHeaderDto> transferMoney(
			@ApiParam(name = "Person Info", required = true) @Validated @RequestBody UserRequestDto userRequestDto,
			BindingResult result) throws UserException {
		if (result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validate(result));
		}
		return ResponseEntity.ok(userHandler.createUserResponse(iUserService.registerPerson(userRequestDto)));
	}

	private ResponseHeaderDto validate(BindingResult result) {

		Map<String, String> errors = new HashMap<>();
		result.getFieldErrors().forEach(err -> {
			errors.put(err.getField(), err.getDefaultMessage());
			System.out.println(err.getDefaultMessage());
			logger.info(err.getField());
		});

		return userHandler.createUserFailedResponse(new ArrayList<>(errors.values()));
	}

}
