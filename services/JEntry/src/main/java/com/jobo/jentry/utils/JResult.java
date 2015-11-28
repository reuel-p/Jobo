package com.jobo.jentry.utils;

public class JResult {

	private int m_errorCode = 0;
	private String m_errorMessage = "";
	
	public JResult(int p_errorCode, String p_errorMessage) {
		super();
		this.m_errorCode = p_errorCode;
		this.m_errorMessage = p_errorMessage;
	}
	
	public int getErrorCode() {
		return m_errorCode;
	}
	
	public void setErrorCode(int p_errorCode) {
		this.m_errorCode = p_errorCode;
	}
	
	public String getErrorMessage() {
		return m_errorMessage;
	}
	
	public void setErrorMessage(String p_errorMessage) {
		this.m_errorMessage = p_errorMessage;
	}
}
