package com.jobo.jentry.dao;

import java.util.List;

import com.jobo.jentry.model.Entry;

public interface EntryDao {
	
	void save(Entry p_entry);
	
	//void update(Profile p_profile);
	
	void delete(int p_eid);
	
	Entry findByID(int p_eid);
	
	Entry findByPFID(int p_pfid);
	
	List<Entry> findAllEntries();
	
	//ArrayList<Profile> getProfilesForPage(long p_prev, long p_next);  

}
