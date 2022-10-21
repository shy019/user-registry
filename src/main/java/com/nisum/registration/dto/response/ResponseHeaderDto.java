package com.nisum.registration.dto.response;

import java.util.List;

import com.nisum.registration.dto.StatusDto;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Response base description")
public class ResponseHeaderDto {

	private StatusDto status;
	private UserResponseDto user;
	private List<String> errors;

	public UserResponseDto getUserResponseDto() {
		return user;
	}

	public void setUserResponseDto(UserResponseDto userResponseDto) {
		this.user = userResponseDto;
	}

	public StatusDto getStatus() {
		return status;
	}

	public void setStatus(StatusDto status) {
		this.status = status;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
}
