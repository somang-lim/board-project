package com.fastcampus.boardproject.config;


import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import java.util.Optional;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import com.fastcampus.boardproject.domain.UserAccount;
import com.fastcampus.boardproject.repository.UserAccountRepository;

@Import(SecurityConfig.class)
public class TestSecurityConfig {

	@MockBean private UserAccountRepository userAccountRepository;


	@BeforeTestMethod
	public void securitySetup() {
		given(userAccountRepository.findById(anyString())).willReturn(Optional.of(UserAccount.of(
				"imhopeTest",
				"pw",
				"imhope-test@mail.com",
				"imhope-test",
				"test-memo"
		)));
	}

}
