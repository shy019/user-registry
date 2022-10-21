package com.nisum.registration.handler;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nisum.registration.dto.StatusDto;
import com.nisum.registration.dto.response.ResponseHeaderDto;
import com.nisum.registration.dto.response.UserResponseDto;

@Service
public class UserHandler {

	public ResponseHeaderDto createUserResponse(UserResponseDto userResponseDto) {

		ResponseHeaderDto response = new ResponseHeaderDto();
		response.setStatus(StatusDto.OK);
		response.setErrors(Collections.emptyList());
		response.setUserResponseDto(userResponseDto);

		return response;
	}

	public ResponseHeaderDto createUserFailedResponse(List<String> errors) {

		ResponseHeaderDto response = new ResponseHeaderDto();
		response.setStatus(StatusDto.ERROR);
		response.setErrors(errors);
		response.setUserResponseDto(null);

		return response;
	}

}
