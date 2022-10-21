package com.nisum.registration.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.nisum.registration.exception.UserException;

class UtilsTest {

	@InjectMocks
	private Utils utils;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("Should fail when encrypts a message")
	void shouldFailWhenEncryptMessageSuccess() throws UserException {

		UserException userException = Assertions.assertThrows(UserException.class,
				() -> utils.encryptString("message"));
		assertNotNull(userException);
	}

	@Test
	@DisplayName("Should fail when Decrypt a message")
	void shouldFailWhenDecryptMessageSuccess() throws UserException {

		UserException userException = Assertions.assertThrows(UserException.class,
				() -> utils.decryptString("Jf5PcEFjyWrf+p9wA/6S/g=="));
		assertNotNull(userException);
	}

	@Test
	@DisplayName("Should fail when Generate a token")
	void shouldFailWhenGenerateTokenSuccess() {

		IllegalArgumentException userException = Assertions.assertThrows(IllegalArgumentException.class,
				() -> utils.generateToken("180"));
		assertNotNull(userException);
	}

}