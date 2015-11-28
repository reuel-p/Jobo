package com.jobo.jprofile.utils;

public class LogUtilities {

	public static String getMethodName(){
		return Thread.currentThread().getStackTrace()[2].getMethodName();
	}
	
}
