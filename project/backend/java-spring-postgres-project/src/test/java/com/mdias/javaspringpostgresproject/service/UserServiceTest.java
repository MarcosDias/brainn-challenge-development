package com.mdias.javaspringpostgresproject.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.mdias.javaspringpostgresproject.AbstractTest;
import com.mdias.javaspringpostgresproject.domain.User;
import com.mdias.javaspringpostgresproject.repository.UserRepository;

public class UserServiceTest extends AbstractTest {
	
	@Autowired
	private UserService service;
	
	@MockBean
	private UserRepository repository;
	

	@Test
	public void findByName() {
		
		when(repository.findByNameLike(VALIDE_USER_GITHUB))
			.thenReturn(Optional.of(createFakeUser()));
		
		Optional<User> optUser = service.findByName(VALIDE_USER_GITHUB);
		
		assertThat(optUser)
			.isNotNull()
			.get()
			.hasFieldOrPropertyWithValue("id", 1L)
			.hasFieldOrPropertyWithValue("name", VALIDE_USER_GITHUB)
			.hasNoNullFieldsOrProperties()
			;
	}

	@Test
	public void save() {
		when(repository.save(any(User.class)))
			.thenReturn(createFakeUser());
		
		User fakeUser = createFakeUser();
		fakeUser.setId(null);
		
		User userSaved = service.save(fakeUser);
		
		assertThat(userSaved)
			.isNotNull()
			.hasFieldOrPropertyWithValue("id", 1L)
			.hasFieldOrPropertyWithValue("name", VALIDE_USER_GITHUB)
			.hasNoNullFieldsOrProperties()
			;
		
	}

	private User createFakeUser() {
		return User.builder()
				.id(1L)
				.createdDateTime(LocalDateTime.now())
				.name(VALIDE_USER_GITHUB)
				.build();
	}

}
