package com.imedia.utils;

public class Utils {

	public void error(Exception ex) {
		System.err.print(ex.getClass().getSimpleName() + "  ");
		System.err.print("[" + this.getClass().getSimpleName() + ".");
		System.err.print(ex.getStackTrace()[0].getMethodName() + ":");
		System.err.print(ex.getStackTrace()[0].getLineNumber() + "] ");
		System.err.println(ex.getMessage());
	}
}
