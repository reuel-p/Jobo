package com.jobo.jentry.service;

import java.util.List;

import com.jobo.jentry.model.Entry;

public interface EntryService {
	
	Entry findById(int id);
	
	Entry findByPFId(int id);
	
	void save(Entry p_entry);
	
	void update(Entry p_entry);
	
	void delete(int p_pfid);

	List<Entry> findAllEntries(); 
	
    public boolean isEntryExist(Entry p_entry);
	
}

