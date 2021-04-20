package com.imedia.services;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.imedia.ImediaApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ImediaApplication.class)
class CurrencyServiceTest {
	

	@Autowired
	CurrencyService service;


	@Test
	void testrefreshCurrency() {

		System.err.println(service.refreshCurrency());
		System.err.println(service.getRates());
	}
}
