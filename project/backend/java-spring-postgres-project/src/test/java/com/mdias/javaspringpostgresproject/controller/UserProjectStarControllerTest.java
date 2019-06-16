package com.mdias.javaspringpostgresproject.controller;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.mdias.javaspringpostgresproject.AbstractTest;

public class UserProjectStarControllerTest extends AbstractTest {

	private String BASE_URL = "/user/{username}/projectstarred";

	@Test
	public void getListProjectStarred_test_ok() throws Exception {
		String page = "2", size = "5";
		
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
		String page = "2", size = "5";
		
		this.mvc.perform(get(BASE_URL, INVALIDE_USER_GITHUB)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
				.param("page", page)
				.param("size", size))
				.andDo(print())
				.andExpect(status().isNotFound())
				;
	}

	private void loadUser() throws Exception {
		this.mvc.perform(post("/loaduser/{username}", VALIDE_USER_GITHUB)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE));
	}

}
