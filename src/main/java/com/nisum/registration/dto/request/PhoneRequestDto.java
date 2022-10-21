package com.nisum.registration.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import io.swagger.annotations.ApiModel;

@Validated
@ApiModel(description = "Phone request description")
public class PhoneRequestDto {

	@Size(min = 7, message = "The number must have more than 6 digits.")
	@NotNull(message = "Number is required.")
	private String number;

	@NotNull(message = "City code is required.")
	private String citycode;

	@NotNull(message = "Country code is required.")
	private String countrycode;

	public PhoneRequestDto(
			@Size(min = 7, message = "The number must have more than 6 digits.") @NotNull(message = "Number is required.") String number,
			@NotNull(message = "City code is required.") String citycode,
			@NotNull(message = "Country code is required.") String countrycode) {
		this.number = number;
		this.citycode = citycode;
		this.countrycode = countrycode;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public String getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

}
