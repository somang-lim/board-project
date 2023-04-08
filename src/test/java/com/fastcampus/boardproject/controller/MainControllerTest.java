package com.fastcampus.boardproject.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.fastcampus.boardproject.config.TestSecurityConfig;

@Import(TestSecurityConfig.class)
@WebMvcTest(MainController.class)
class MainControllerTest {

	private final MockMvc mvc;

	public MainControllerTest(@Autowired MockMvc mvc) {
		this.mvc = mvc;
	}


	@DisplayName("루트 페이지를 요청하면, 게시판 페이지를 호출한다.")
	@Test
	void givenNothing_whenRequestingRootPage_thenRedirectsToArticlesPage() throws Exception {
		// Given

		// When & Then
		mvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(view().name("forward:/articles"))
				.andExpect(forwardedUrl("/articles"))
				.andDo(MockMvcResultHandlers.print());
	}

}
