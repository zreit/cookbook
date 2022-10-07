package com.example.cookbook;

import com.example.cookbook.controller.AppUserController;
import com.example.cookbook.controller.CookbookController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CookbookApplicationTests {

	@Autowired
	private AppUserController appUserController;
	@Autowired
	private CookbookController cookbookController;

	@Test
	void contextLoads() throws Exception {
		assertThat(appUserController).isNotNull();
		assertThat(cookbookController).isNotNull();
	}
}
