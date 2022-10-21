package com.nisum.registration.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nisum.registration.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	Optional<User> findUserByEmail(String email);
}
