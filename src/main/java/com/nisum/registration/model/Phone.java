package com.nisum.registration.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;

@Entity
@Table(name = "phone")
@ApiModel(description = "Phone's description")
public class Phone {

	@Id
	@Column(name = "phone_id")
	private String number;
	private String citycode;
	private String countrycode;

	public Phone() {
	}

	public Phone(String number, String citycode, String countrycode) {
		super();
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
