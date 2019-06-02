package com.mdias.javaspringpostgresproject.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdias.javaspringpostgresproject.domain.User;
import com.mdias.javaspringpostgresproject.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public Optional<User> findByName(String username) {
		return repository.findByName(username);
	}

	public User save(User user) {
		return repository.save(user);
	}
 
}
