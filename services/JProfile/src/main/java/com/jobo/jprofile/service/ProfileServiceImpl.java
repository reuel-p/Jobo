package com.jobo.jprofile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobo.jprofile.dao.ProfileDao;
import com.jobo.jprofile.model.Profile;

@Service("profileService")
@Transactional
public class ProfileServiceImpl implements ProfileService {
	
	@Autowired
	private ProfileDao m_pdao;
	
	public Profile findById(int p_pfid) {
		return m_pdao.findByID(p_pfid);
	}

	public void save(Profile p_profile) {
		m_pdao.save(p_profile);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void update(Profile p_profile) {
		Profile l_profile = m_pdao.findByID(p_profile.getPfid());
		if(l_profile!=null){
			l_profile = p_profile;
		}
	}

	public void delete(int p_pfid) {
		m_pdao.delete(p_pfid);
	}
	
	public List<Profile> findAllProfiles() {
		return m_pdao.findAllProfiles();
	}

    public boolean isUserExist(Profile p_profile) {
        return findById(p_profile.getPfid())!=null;
    }
}
