package com.nisum.registration.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.nisum.registration.dto.StatusDto;
import com.nisum.registration.dto.request.PhoneRequestDto;
import com.nisum.registration.dto.request.UserRequestDto;
import com.nisum.registration.dto.response.ResponseHeaderDto;
import com.nisum.registration.dto.response.UserResponseDto;
import com.nisum.registration.exception.UserException;
import com.nisum.registration.handler.UserHandler;
import com.nisum.registration.model.Phone;
import com.nisum.registration.model.User;
import com.nisum.registration.repository.PhoneRepository;
import com.nisum.registration.repository.UserRepository;
import com.nisum.registration.util.Utils;

class UserServiceTest {

	@InjectMocks
	private UserService userService;

	@Mock
	private ModelMapper modelMapper;

	@Mock
	private UserRepository userRepository;

	@Mock
	private PhoneRepository phoneRepository;

	@Mock
	private UserHandler userHandler;

	@Mock
	private Utils utils;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("Register an user")
	void registerUserSuccess() throws UserException {

		UserRequestDto userRequestDto = generateUserRequestDto();
		UserResponseDto userResponseDto = generateUserResponseDto();
		ResponseHeaderDto responseHeaderDto = generateResponseHeaderDto(userResponseDto);
		User user = new User();

		Mockito.when(userRepository.findUserByEmail(userRequestDto.getEmail())).thenReturn(Optional.empty());
		Mockito.when(
				phoneRepository.findPhoneByNumber(userRequestDto.getPhones().stream().findFirst().get().getNumber()))
				.thenReturn(Optional.empty());
		Mockito.when(userHandler.createUserResponse(userResponseDto)).thenReturn(responseHeaderDto);
		Mockito.when(userRepository.save(user)).thenReturn(user);

		when(this.modelMapper.map(userRequestDto, User.class)).thenReturn(user);
		when(this.modelMapper.map(user, UserResponseDto.class)).thenReturn(userResponseDto);
		when(utils.encryptString(user.getPassword())).thenReturn("password");

		UserResponseDto userResponseDto2 = userService.registerPerson(userRequestDto);
		assertNotNull(userResponseDto2);
	}

	@Test
	@DisplayName("Should fail when register an user because of email registered")
	void registerShouldFailBecauseOfEmailRegistered() {

		UserRequestDto userRequestDto = generateUserRequestDto();
		User user = generateUser();

		Mockito.when(userRepository.findUserByEmail(userRequestDto.getEmail())).thenReturn(Optional.of(user));

		UserException userException = Assertions.assertThrows(UserException.class,
				() -> userService.registerPerson(userRequestDto));
		assertNotNull(userException);
	}

	@Test
	@DisplayName("Should fail when register an user because of number registered")
	void registerShouldFailBecauseOfNumberRegistered() {

		UserRequestDto userRequestDto = generateUserRequestDto();
		User user = new User();
		Phone phone = new Phone("12345678", "23", "57");

		Mockito.when(userRepository.findUserByEmail(userRequestDto.getEmail())).thenReturn(Optional.empty());
		Mockito.when(
				phoneRepository.findPhoneByNumber(userRequestDto.getPhones().stream().findFirst().get().getNumber()))
				.thenReturn(Optional.of(phone));

		UserException userException = Assertions.assertThrows(UserException.class,
				() -> userService.registerPerson(userRequestDto));
		assertNotNull(userException);
	}

	private static UserResponseDto generateUserResponseDto() {

		UserResponseDto userResponseDto = new UserResponseDto();
		userResponseDto.setActive(true);
		userResponseDto.setEmail("juandsf@dominio.cl");
		userResponseDto.setName("Juan David");
		return userResponseDto;
	}

	private static UserRequestDto generateUserRequestDto() {

		UserRequestDto userResponseDto = new UserRequestDto();
		userResponseDto.setEmail("juandsf@dominio.cl");
		userResponseDto.setName("Juan David");
		userResponseDto.setPhones(new HashSet<>(Arrays.asList(new PhoneRequestDto("12345678", "23", "57"))));
		return userResponseDto;
	}

	private static User generateUser() {

		User user = new User();
		user.setEmail("juandsf@dominio.cl");
		user.setName("Juan David");
		return user;
	}

	private static ResponseHeaderDto generateResponseHeaderDto(UserResponseDto userResponseDto) {

		ResponseHeaderDto responseHeaderDto = new ResponseHeaderDto();
		responseHeaderDto.setUserResponseDto(userResponseDto);
		responseHeaderDto.setStatus(StatusDto.OK);
		return responseHeaderDto;
	}
}