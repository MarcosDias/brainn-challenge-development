package com.mdias.javaspringpostgresproject.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.mdias.javaspringpostgresproject.AbstractTest;
import com.mdias.javaspringpostgresproject.domain.User;
import com.mdias.javaspringpostgresproject.exception.ResourceNotFound;
import com.mdias.javaspringpostgresproject.repository.UserRepository;

public class UserServiceTest extends AbstractTest {
	
	@Autowired
	private UserService service;
	
	@MockBean
	private UserRepository repository;
	
	@Before
	public void setUp() {
		when(repository.findByName(VALIDE_USER_GITHUB))
			.thenReturn(Optional.of(createFakeUser()));

		when(repository.save(any(User.class)))
			.thenReturn(createFakeUser());
	}

	@Test
	public void findByName_test_ok() {

		Optional<User> optUser = service.findByName(VALIDE_USER_GITHUB);

		assertThat(optUser)
			.isNotNull()
			.get()
			.hasFieldOrPropertyWithValue("id", 1L)
			.hasFieldOrPropertyWithValue("name", VALIDE_USER_GITHUB)
			;
	}

	@Test
	public void create_test_ok() {

		User fakeUser = createFakeUser();
		fakeUser.setId(null);

		User userSaved = service.create(VALIDE_USER_GITHUB);

		assertThat(userSaved)
			.isNotNull()
			.hasFieldOrPropertyWithValue("id", 1L)
			.hasFieldOrPropertyWithValue("name", VALIDE_USER_GITHUB)
			;
		
	}
	
	@Test
	public void getUserLoaded_test_ok() throws ResourceNotFound {

		User userLoaded = service.getUserLoaded(VALIDE_USER_GITHUB);

		assertThat(userLoaded)
			.isNotNull()
			.hasFieldOrPropertyWithValue("id", 1L)
			.hasFieldOrPropertyWithValue("name", VALIDE_USER_GITHUB)
			;
	}
	
	@Test(expected = ResourceNotFound.class)
	public void getUserLoaded_test_error_resource_not_found() throws ResourceNotFound {
		
		service.getUserLoaded(INVALIDE_USER_GITHUB);
	}
	
	@Test
	public void getUserLoadedOrCreate_test_ok_user_already_created() {
		User userLoaded = service.getUserLoadedOrCreate(VALIDE_USER_GITHUB);

		assertThat(userLoaded)
			.isNotNull()
			.hasFieldOrPropertyWithValue("id", 1L)
			.hasFieldOrPropertyWithValue("name", VALIDE_USER_GITHUB)
			;
	}
	
	@Test
	public void getUserLoadedOrCreate_test_ok_create_new_user() {

		when(repository.save(any(User.class)))
			.thenReturn(createFakeUser(INVALIDE_USER_GITHUB));

		User userCreated = service.getUserLoadedOrCreate(INVALIDE_USER_GITHUB);

		assertThat(userCreated)
			.isNotNull()
			.hasFieldOrPropertyWithValue("id", 1L)
			.hasFieldOrPropertyWithValue("name", INVALIDE_USER_GITHUB)
			;
	}

	private User createFakeUser() {
		return createFakeUser(VALIDE_USER_GITHUB);
	}

	private User createFakeUser(String userName) {
		return User.builder()
				.id(1L)
				.createdDateTime(LocalDateTime.now())
				.name(userName)
				.build();
	}

}
