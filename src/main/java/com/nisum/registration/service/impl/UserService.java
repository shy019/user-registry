package com.nisum.registration.service.impl;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.nisum.registration.dto.request.UserRequestDto;
import com.nisum.registration.dto.response.UserResponseDto;
import com.nisum.registration.exception.UserException;
import com.nisum.registration.model.User;
import com.nisum.registration.repository.PhoneRepository;
import com.nisum.registration.repository.UserRepository;
import com.nisum.registration.service.IUserService;
import com.nisum.registration.util.Utils;

@Service
public class UserService implements IUserService {

	private static final Logger logger = LoggerFactory.logger(UserService.class);

	private final UserRepository userRepository;
	private final PhoneRepository phoneRepository;
	private final ModelMapper modelMapper;
	private final Utils utils;

	public UserService(UserRepository userRepository, PhoneRepository phoneRepository, ModelMapper modelMapper,
			Utils utils) {
		super();
		this.userRepository = userRepository;
		this.phoneRepository = phoneRepository;
		this.modelMapper = modelMapper;
		this.utils = utils;
	}

	@Override
	public UserResponseDto registerPerson(UserRequestDto userRequestDto) throws UserException {

		if (!userRepository.findUserByEmail(userRequestDto.getEmail()).isEmpty()) {
			logger.error("Email is already registered for email {}.".concat(userRequestDto.getEmail()));
			throw new UserException("Email is already registered.");
		}

		if (userRequestDto.getPhones().stream()
				.anyMatch(phone -> !phoneRepository.findPhoneByNumber(phone.getNumber()).isEmpty())) {
			logger.error("Phone number is already registered");
			throw new UserException("Any of the phone numbers is already registered.");
		}

		return modelMapper.map(userRepository.save(createUser(userRequestDto)), UserResponseDto.class);
	}

	private User createUser(UserRequestDto userRequestDto) throws UserException {
		User user = modelMapper.map(userRequestDto, User.class);
		user.setActive(true);
		user.setCreated(LocalDate.now());
		user.setLastLogin(LocalDate.now());
		user.setModified(null);
		user.setPassword(utils.encryptString(user.getPassword()));
		user.setId(UUID.randomUUID().toString());
		user.setToken(utils.generateToken(user.getName()));
		return user;
	}
}
