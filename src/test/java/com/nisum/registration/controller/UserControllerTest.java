package com.nisum.registration.controller;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.nisum.registration.handler.UserHandler;
import com.nisum.registration.service.IUserService;

class UserControllerTest {

	@InjectMocks
	private UserController userController;

	@Mock
	private IUserService iUserService;

	@Mock
	UserHandler userHandler;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
}