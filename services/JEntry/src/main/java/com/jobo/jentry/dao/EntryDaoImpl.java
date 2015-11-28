package com.jobo.jentry.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.jobo.jentry.model.Entry;

@Repository("entryDao")
public class EntryDaoImpl extends AbstractDao<Integer, Integer, Entry> implements EntryDao {

	public void save(Entry p_entry) {
		persist(p_entry);
	}
	
/*	public void update(Profile p_profile) {
		update(p_profile);
	}*/

	public void delete(int p_eid) {
		Query query = getSession().createSQLQuery("delete from jentry where eid = :eid");
		query.setInteger("eid", p_eid);
		query.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<Entry> findAllEntries() {
		Criteria criteria = createEntityCriteria();
		return (List<Entry>) criteria.list();
	}

	public Entry findByID(int p_eid) {
		return getByKey(p_eid);
	}
	
	public Entry findByPFID(int p_pfid) {
		return getByFKey(p_pfid);
	}
	

}
