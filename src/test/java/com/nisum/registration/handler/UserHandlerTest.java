package com.nisum.registration.handler;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.nisum.registration.dto.response.UserResponseDto;

class UserHandlerTest {

	@InjectMocks
	private UserHandler userHandler;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("Creates a general response with user")
	void createUserBalanceResponse() {

		assertNotNull(userHandler.createUserResponse(generateUserResponseDto()));
	}

	@Test
	@DisplayName("Creates a general failed response")
	void createUserBalanceFailedResponse() {

		assertNotNull(userHandler.createUserFailedResponse(Collections.singletonList("error")));
	}

	private static UserResponseDto generateUserResponseDto() {
		
		UserResponseDto userResponseDto = new UserResponseDto();
		userResponseDto.setActive(true);
		userResponseDto.setEmail("juandsf@dominio.cl");
		userResponseDto.setName("Juan David");
		return userResponseDto;
	}
}