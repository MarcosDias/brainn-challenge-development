package com.mdias.javaspringpostgresproject.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mdias.javaspringpostgresproject.AbstractTest;
import com.mdias.javaspringpostgresproject.resource.TagResource;

@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserProjectStarControllerTest extends AbstractTest {

	private String BASE_URL = "/user/{username}/projectstarred";
	private String page = "2";
	private String size = "5";

	@Test
	public void getListProjectStarred_test_ok() throws Exception {
		
		loadUser();
		
		this.mvc.perform(get(BASE_URL, VALIDE_USER_GITHUB)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
				.param("page", page)
				.param("size", size))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.content", is(notNullValue())))
				.andExpect(jsonPath("$.pageNumber", is(Integer.parseInt(page))))
				.andExpect(jsonPath("$.pageSize", is(Integer.parseInt(size))))
				.andExpect(jsonPath("$.totalElements", is(notNullValue())))
				.andExpect(jsonPath("$.totalPages", is(notNullValue())))
				;
	}

	@Test
	public void getListProjectStarred_test_error_user_not_loaded() throws Exception {
		this.mvc.perform(get(BASE_URL, INVALIDE_USER_GITHUB)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
				.param("page", page)
				.param("size", size))
				.andDo(print())
				.andExpect(status().isNotFound())
				;
	}

	@Test
	public void setTagInProjectStarred_test_ok() throws Exception {
		loadUser();

		TagResource tag = TagResource.builder().name("Cool Project").build();

		this.mvc.perform(put(BASE_URL + "/{projectId}/tags", VALIDE_USER_GITHUB, 1)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(toJson(List.of(tag))))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.tags.[0].id", is(1)))
				.andExpect(jsonPath("$.tags.[0].name", is("Cool Project")))
				;
	}

	@Test
	public void setTagInProjectStarred_test_error_project_not_found() throws Exception {
		loadUser();

		TagResource tag = TagResource.builder().name("Cool Project").build();

		this.mvc.perform(put(BASE_URL + "/{projectId}/tags", VALIDE_USER_GITHUB, -1)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(toJson(List.of(tag))))
				.andDo(print())
				.andExpect(status().isNotFound())
				;
	}

	@Test
	public void setTagInProjectStarred_test_change_tag_ok() throws Exception {
		long projectId = 1l;
		loadUser();
		addTagInProject(projectId);

		TagResource tag = TagResource.builder().name("Nice Project").build();

		this.mvc.perform(put(BASE_URL + "/{projectId}/tags", VALIDE_USER_GITHUB, projectId)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(toJson(List.of(tag))))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.tags.[0].id", is(2)))
				.andExpect(jsonPath("$.tags.[0].name", is("Nice Project")))
				.andExpect(jsonPath("$.tags", hasSize(1)))
				;
	}

	@Test
	public void setTagInProjectStarred_test_remove_tag_ok() throws Exception {
		long projectId = 1l;
		loadUser();
		addTagInProject(projectId);

		this.mvc.perform(put(BASE_URL + "/{projectId}/tags", VALIDE_USER_GITHUB, projectId)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(toJson(List.of())))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.tags", empty()))
				;
	}

	private void addTagInProject(long projectId) throws Exception, JsonProcessingException {
		TagResource tag = TagResource.builder().name("Cool Project").build();

		this.mvc.perform(put(BASE_URL + "/{projectId}/tags", VALIDE_USER_GITHUB, projectId)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(toJson(List.of(tag))))
				;
	}

	private void loadUser() throws Exception {
		this.mvc.perform(post("/loaduser/{username}", VALIDE_USER_GITHUB)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE));
	}

}
