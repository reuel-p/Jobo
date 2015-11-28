package com.jobo.jprofile.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.jobo.jprofile.model.Profile;

@Repository("profileDao")
public class ProfileDaoImpl extends AbstractDao<Integer, Profile> implements ProfileDao {

	public void save(Profile p_profile) {
		persist(p_profile);
	}
	
/*	public void update(Profile p_profile) {
		update(p_profile);
	}*/

	public void delete(int p_pfid) {
		Query query = getSession().createSQLQuery("delete from Employee where pfid = :pfid");
		query.setInteger("pfid", p_pfid);
		query.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<Profile> findAllProfiles() {
		Criteria criteria = createEntityCriteria();
		return (List<Profile>) criteria.list();
	}

	public Profile findByID(int p_pfid) {
		return getByKey(p_pfid);
	}

}