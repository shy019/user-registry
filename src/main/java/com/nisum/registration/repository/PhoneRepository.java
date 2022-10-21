package com.nisum.registration.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nisum.registration.model.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, String> {

	Optional<Phone> findPhoneByNumber(String number);
}
