package com.mdias.javaspringpostgresproject.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;

import com.mdias.javaspringpostgresproject.AbstractTest;
import com.mdias.javaspringpostgresproject.domain.ProjectStarred;
import com.mdias.javaspringpostgresproject.domain.User;
import com.mdias.javaspringpostgresproject.dto.ProjectGitHubDTO;
import com.mdias.javaspringpostgresproject.exception.UserNotFoundGitHubException;
import com.mdias.javaspringpostgresproject.repositry.ProjectStarredRepository;

public class ProjectStarredServiceTest extends AbstractTest {
	
	@Autowired
	private ProjectStarredService service;
	
	@MockBean
	private LoadUserGithubService loadUserGithubService;
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private ProjectStarredRepository projectStarredRepository; 
	
	@Before
	public void setUp() throws UserNotFoundGitHubException {

		when(loadUserGithubService.loadProjectStarred(VALIDE_USER_GITHUB))
			.thenReturn(createListFakeProjectGitHubDTO());
		
		when(userService.save(any(User.class)))
			.thenReturn(createFakeUser());
		
		when(projectStarredRepository.saveAll(Mockito.anyList()))
			.thenReturn(new ArrayList<ProjectStarred>());
		
		doNothing().when(projectStarredRepository).deleteByUser(any(User.class));
			
	}

	@Test
	@Rollback
	public void loadUser_test() throws Exception {
		
		User loadedUser = service.loadUser(VALIDE_USER_GITHUB);

		assertThat(loadedUser)
			.isNotNull()
			.hasNoNullFieldsOrProperties()
			.extracting("projectsStarred")
				.asList()
				.isNotEmpty();
	}

	@Test
	@Rollback
	public void loadUser_test_run_many_times_return_same_object() throws Exception {
		
		User userTimeOne = service.loadUser(VALIDE_USER_GITHUB);
		
		when(userService.findByName(VALIDE_USER_GITHUB))
			.thenReturn(Optional.of(createFakeUser()));
		
		User userTimeTwo = service.loadUser(VALIDE_USER_GITHUB);

		assertThat(userTimeTwo.getId())
			.isEqualTo(userTimeOne.getId())
			;
	}

	private List<ProjectGitHubDTO> createListFakeProjectGitHubDTO() {
		ProjectGitHubDTO fakeGitHubProject = ProjectGitHubDTO.builder()
			.id(1L)
			.name("name")
			.description("description")
			.url("url")
			.language("language")
			.build();

		return List.of(fakeGitHubProject);
	}

	private User createFakeUser() {
		return User.builder()
				.id(1L)
				.createdDateTime(LocalDateTime.now())
				.name(VALIDE_USER_GITHUB)
				.projectsStarred(createListFakeProjectStarred())
				.build();
	}

	private Set<ProjectStarred> createListFakeProjectStarred() {
		ProjectStarred projectStarred = ProjectStarred.builder()
			.id(1L)
			.createdDateTime(LocalDateTime.now())
			.githubId(1L)
			.name("name")
			.description("description")
			.url("url")
			.language("language")
			.build();
		return Set.of(projectStarred);
	}

}
