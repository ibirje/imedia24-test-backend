package com.imedia.controllers;

import javax.servlet.ServletException;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imedia.config.Routes;
import com.imedia.dto.CurrencyConversion;
import com.imedia.services.CurrencyService;

@RestController
@RequestMapping(Routes.CURRENCY)
public class CurrencyController {


	
	@Autowired
	private CurrencyService service;
	
	
/*
	@GetMapping
	public CurrencyRate details() throws ServletException {
		return service.getRates();
	}
*/	

	@GetMapping("/convert")
	public CurrencyConversion convert( @RequestParam @NotNull Double eur, 
			                     @RequestParam @NotBlank String convertedTo) throws ServletException {
		
		return service.convertEurToCurrency(eur, convertedTo);
	}
	
}











