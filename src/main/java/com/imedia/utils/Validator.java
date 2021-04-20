package com.imedia.utils;

import java.util.Collection;

public class Validator {
	
	
	public boolean isNullOrEmpty(Collection<?> pl) {
		return pl == null || pl.isEmpty();
	}

	public boolean isNullOrEmpty(String str) {
		return str == null || str.trim().isEmpty();
	}
	
	
}
