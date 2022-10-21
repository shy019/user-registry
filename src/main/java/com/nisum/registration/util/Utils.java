package com.nisum.registration.util;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nisum.registration.exception.UserException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class Utils {

	@Value("${nisum.app.jwtSecret}")
	private String jwtSecret;

	@Value("${nisum.app.jwtExpirationMs}")
	private int jwtExpirationMs;

	@Value("${nisum.app.algorithm-encryption}")
	private String algorithm;

	public String encryptString(String message) throws UserException {
		try {
			return Encrypt.encrypt(algorithm, message, Encrypt.generateKey(128), Encrypt.generateIv());
		} catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException
				| InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException e) {
			throw new UserException("Error generating encryption.");
		}
	}

	public String decryptString(String messageEncrypted) throws UserException {
		try {
			return Encrypt.decrypt(algorithm, messageEncrypted, Encrypt.generateKey(128), Encrypt.generateIv());
		} catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException
				| InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException e) {
			throw new UserException("Error generating encryption.");
		}
	}

	public String generateToken(String name) {
		return Jwts.builder().setSubject((name)).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}
}
