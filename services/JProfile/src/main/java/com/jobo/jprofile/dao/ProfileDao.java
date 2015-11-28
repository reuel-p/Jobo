package com.jobo.jprofile.dao;

import java.util.List;

import com.jobo.jprofile.model.Profile;

public interface ProfileDao {
	
	void save(Profile p_profile);
	
	//void update(Profile p_profile);
	
	void delete(int p_pfid);
	
	Profile findByID(int p_pfid);
	
	List<Profile> findAllProfiles();
	
	//ArrayList<Profile> getProfilesForPage(long p_prev, long p_next);  

}