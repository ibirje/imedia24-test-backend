package com.imedia.services;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.imedia.dto.CurrencyConversion;
import com.imedia.models.CurrencyRate;
import com.imedia.utils.constants.Const;

@Service
public class CurrencyService {


	@Cacheable(value = "currencyrates")
	public CurrencyRate getRates() { return null;}
	
	
	// add cacheput
	@CachePut(value = "currencyrates")
	@Scheduled(fixedDelay = 5 * Const.MIN_TO_MILLIS)
	public CurrencyRate refreshCurrency() {
			
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://data.fixer.io/api/latest")
					.queryParam("access_key", Const.FIXER_API_KEY);
					//.queryParam("symbols", symbols);
			

			 RestTemplate restTemplate = new RestTemplate();
			
			CurrencyRate rate = restTemplate.getForObject(builder.toUriString(), CurrencyRate.class);
			return rate;
	}


	public CurrencyConversion convertEurToCurrency(@NotNull Double eur, @NotBlank String convertedTo) {
		
		CurrencyRate rates = getRates();
		
		if(rates == null) return null;
		
		BigDecimal conversionrate = rates.getRates().get(convertedTo);
		
		return conversionrate == null ? null : 
			new CurrencyConversion(rates.getBase(), eur, convertedTo, eur * conversionrate.doubleValue() , rates.getDate());
	}
	
	public CurrencyConversion convertCurrencyToEur(@NotNull Double currencyAmount, @NotBlank String currency) {
		
		CurrencyRate rates = getRates();
		
		if(rates == null) return null;
		
		BigDecimal conversionrate = rates.getRates().get(currency);
		
		return conversionrate == null ? null : 
			new CurrencyConversion(rates.getBase(), currencyAmount / conversionrate.doubleValue(), currency, currencyAmount , 
					rates.getDate());
	}
	
	
}
