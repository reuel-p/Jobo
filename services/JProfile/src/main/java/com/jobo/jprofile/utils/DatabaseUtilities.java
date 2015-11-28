package com.jobo.jprofile.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

public class DatabaseUtilities {

	public static Timestamp getCurrentSQLTimeStamp(){
		
		//Create a java calendar instance
		//And get a java.util.Date from the calendar instance.
		Calendar calendar = Calendar.getInstance();
		java.util.Date now = calendar.getTime();
		 
		//Return the java current timestamp for SQL type
		return new java.sql.Timestamp(now.getTime());	
	}
	
	public static Date getCurrentSQLDate(){
		
		long time = System.currentTimeMillis();
		return new java.sql.Date(time);
	}
}
