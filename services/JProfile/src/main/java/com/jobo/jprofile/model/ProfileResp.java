package com.jobo.jprofile.model;

import java.util.List;

import com.jobo.jprofile.utils.JResult;

public class ProfileResp {

	private JResult m_result = new JResult(-1, "");
	List<Profile> m_profiles;
	Profile m_profile;
	
	public List<Profile> getProfiles() {
		return m_profiles;
	}

	public void setProfiles(List<Profile> m_profiles) {
		this.m_profiles = m_profiles;
	}

	public Profile getProfile() {
		return m_profile;
	}

	public void setProfile(Profile m_profile) {
		this.m_profile = m_profile;
	}
	
	public JResult getResult() {
		return m_result;
	}

	public void setResult(JResult p_result) {
		this.m_result = p_result;
	}
	
}
