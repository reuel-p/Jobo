package com.jobo.jentry.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobo.jentry.dao.EntryDao;
import com.jobo.jentry.model.Entry;

@Service("entryService")
@Transactional
public class EntryServiceImpl implements EntryService {
	
	@Autowired
	private EntryDao m_edao;
	
	public Entry findById(int p_eid) {
		return m_edao.findByID(p_eid);
	}

	public Entry findByPFId(int p_pfid) {
		return m_edao.findByPFID(p_pfid);
	}
	
	public void save(Entry p_profile) {
		m_edao.save(p_profile);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void update(Entry p_entry) {
		Entry l_entry = m_edao.findByID(p_entry.getEid());
		if(p_entry!=null){
			l_entry = p_entry;
		}
	}

	public void delete(int p_eid) {
		m_edao.delete(p_eid);
	}
	
	public List<Entry> findAllEntries() {
		return m_edao.findAllEntries();
	}

    public boolean isEntryExist(Entry p_entry) {
        return findById(p_entry.getEid())!=null;
    }

}

