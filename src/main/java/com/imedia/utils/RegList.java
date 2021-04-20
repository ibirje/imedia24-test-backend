package com.imedia.utils;

public class RegList {

	public static final String REGEX_TITLE        = "^([a-zA-Zé\\d\\+]+(\\'|\\s|){0,1})*$";
	public static final String REGEX_PRODUCT_REF  = "^(\\w)*$";
	public static final String REGEX_IMAGE        = "^https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}"
		+ "\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&\\/\\/=]*)(jpg|jpeg|png|svg|tif|gif)$";
}
