package com.example.demo;

import com.example.demo.controller.CurrencyController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CurrencyComparisonApplicationTests {

	@Autowired
	private CurrencyController currencyController;

	@Test
	public void contextLoads() {
		assertThat(currencyController).isNotNull();
	}

}
