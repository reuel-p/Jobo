package com.jobo.jprofile.exceptions;

public class JDataException extends Exception{

	private static final long serialVersionUID = 1997753363232807009L;

	public JDataException(){
	}
	
	public JDataException(String message){
		super(message);
	}
	
	public JDataException(Throwable cause){
		super(cause);
	}
	
	public JDataException(String message, Throwable cause){
		super(message, cause);
	}
	
	public JDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
