package com.nisum.registration.service;

import com.nisum.registration.dto.request.UserRequestDto;
import com.nisum.registration.dto.response.UserResponseDto;
import com.nisum.registration.exception.UserException;

public interface IUserService {

	public UserResponseDto registerPerson(UserRequestDto userRequestDto) throws UserException;
}
