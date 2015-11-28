package com.jobo.jentry.utils;

public class LogUtilities {

	public static String getMethodName(){
		return Thread.currentThread().getStackTrace()[2].getMethodName();
	}
	
}
