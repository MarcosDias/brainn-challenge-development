package com.mdias.javaspringpostgresproject.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdias.javaspringpostgresproject.domain.User;
import com.mdias.javaspringpostgresproject.exception.ResourceNotFound;
import com.mdias.javaspringpostgresproject.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public Optional<User> findByName(String username) {
		return repository.findByName(username);
	}

	public User create(String username) {
		User user = User.builder().name(username).build();
		return repository.save(user);
	}

	public User getUserLoaded(String username) throws ResourceNotFound {
		Optional<User> optUser = this.findByName(username);
		return optUser.orElseThrow(() -> new ResourceNotFound("User not found, sorry! :("));
	}

	public User getUserLoadedOrCreate(String username) {
		Optional<User> optUser = this.findByName(username);
		
		if (optUser.isPresent()) return optUser.get();
		
		return this.create(username);
	}
 
}
