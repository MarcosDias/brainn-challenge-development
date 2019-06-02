package com.mdias.javaspringpostgresproject.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.mdias.javaspringpostgresproject.AbstractTest;

public class LoadUserGithubControllerTest extends AbstractTest {

	private String BASE_URL = "/loaduser";

	@Test
	public void loadUserGithub_test_ok() throws Exception {
		this.mvc.perform(post(BASE_URL  + "/{username}", VALIDE_USER_GITHUB)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is("marcosdias")))
				;
	}
	
	@Test
	public void loadUserGithub_test_erro() throws Exception {
		this.mvc.perform(post(BASE_URL  + "/{username}", INVALIDE_USER_GITHUB)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andDo(print())
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.status", is(404)))
				.andExpect(jsonPath("$.message", is("User \"anyNameNotValide\" not found on GitHub!")))
				.andExpect(jsonPath("$.timestamp").exists())
				;
	}

	@Test
	public void loadUserGithub_test_many_time_ok() throws Exception {

		this.mvc.perform(post(BASE_URL  + "/{username}", VALIDE_USER_GITHUB)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE));
		
		this.mvc.perform(post(BASE_URL  + "/{username}", VALIDE_USER_GITHUB)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is("marcosdias")))
				;
	}

}
