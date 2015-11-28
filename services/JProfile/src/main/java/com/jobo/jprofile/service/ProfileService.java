package com.jobo.jprofile.service;

import java.util.List;

import com.jobo.jprofile.model.Profile;

public interface ProfileService {
	
	Profile findById(int id);
	
	void save(Profile p_profile);
	
	void update(Profile p_profile);
	
	void delete(int p_pfid);

	List<Profile> findAllProfiles(); 
	
    public boolean isUserExist(Profile p_profile);
	
}
