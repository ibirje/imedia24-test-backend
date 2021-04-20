package com.imedia.dto;

import java.util.Date;

public class CurrencyConversion {

	String baseCurrency;
	Double baseAmount;
	
	String convertedCurrency;
	Double convertedAmount;
	
	Date date;
	
		
	
	public CurrencyConversion() { }

	public CurrencyConversion(String baseCurrency, Double baseAmount, 
			String convertedCurrency, Double convertedAmount, Date date) {
		
		this.baseCurrency = baseCurrency;
		this.baseAmount = baseAmount;
		this.convertedCurrency = convertedCurrency;
		this.convertedAmount = convertedAmount;
		this.date = date;
	}

	public String getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public Double getBaseAmount() {
		return baseAmount;
	}

	public void setBaseAmount(Double baseAmount) {
		this.baseAmount = baseAmount;
	}

	public String getConvertedCurrency() {
		return convertedCurrency;
	}

	public void setConvertedCurrency(String convertedCurrency) {
		this.convertedCurrency = convertedCurrency;
	}

	public Double getConvertedAmount() {
		return convertedAmount;
	}

	public void setConvertedAmount(Double convertedAmount) {
		this.convertedAmount = convertedAmount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
