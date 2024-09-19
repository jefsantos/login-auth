package com.login_auth.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.login_auth.domain.User;


public interface UserRepository extends JpaRepository<User, String>{

	Optional<User>  findByEmail(String email);
}
