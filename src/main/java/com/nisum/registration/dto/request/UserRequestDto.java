package com.nisum.registration.dto.request;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import io.swagger.annotations.ApiModel;

@Validated
@ApiModel(description = "User request description")
public class UserRequestDto {

	@Size(min = 3, message = "UserName must have more than 3 characters.")
	@NotNull(message = "Name is required.")
	private String name;

	@Email(regexp = "^([_A-Za-z0-9-+]{3,7}+@(dominio.cl|dominio.cl))$", message = "The email must have to apply next format: aaaaaaa@dominio.cl")
	@NotNull(message = "Email is required.")
	private String email;

	@Size(min = 3, message = "Password must have more than 3 characters.")
	@NotNull(message = "Password is required")
	private String password;

	@Valid
	@NotNull(message = "Phones are required.")
	private Set<PhoneRequestDto> phones;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<PhoneRequestDto> getPhones() {
		return phones;
	}

	public void setPhones(Set<PhoneRequestDto> phones) {
		this.phones = phones;
	}
}