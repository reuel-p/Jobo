package com.jobo.jprofile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.jobo.jprofile.exceptions.JDataException;
import com.jobo.jprofile.utils.Constants;
import com.jobo.jprofile.utils.LogUtilities;
import com.jobo.jprofile.utils.Messages;
import com.jobo.jprofile.service.ProfileService;
import com.jobo.jprofile.utils.JResult;
import com.jobo.jprofile.model.Profile;
import com.jobo.jprofile.model.ProfileResp;

@RestController
public class ServiceController {
	
    @Autowired
    ProfileService m_pService;  //Service which will do all data retrieval/manipulation work
 
     
    //-------------------Retrieve All Users--------------------------------------------------------
     
    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<Profile>> listAllProfiles() {
    	List<Profile> l_profiles  = null;
		int l_code = Constants.SUCCESS;
		String l_message = Messages.PROFILE_ALLRETRIEVED;
    	
    	try{
    		l_profiles = m_pService.findAllProfiles();
    		
			if(l_profiles == null || l_profiles.size() == 0)
				throw new JDataException("" + this.getClass().getSimpleName() 
						+ "." + LogUtilities.getMethodName() + ": The Profiles are not found");
			
    	}catch(Exception e){
			l_code = Constants.ERROR;
			l_message = Messages.PROFILE_RETRIEVEALL_ERROR + e.getMessage();
			
    	}
    	
    	JResult l_result = new JResult(l_code, l_message);
    	   	
        if(l_profiles.isEmpty()){
            return new ResponseEntity<List<Profile>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Profile>>(l_profiles, HttpStatus.OK);
    }
 
 
    //-------------------Retrieve Single User--------------------------------------------------------
     
    @RequestMapping(value = "/user/{p_pfid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfileResp> getProfile(@PathVariable("p_pfid") int p_pfid) {
    	
    	Profile l_profile = null;
		int l_code = Constants.SUCCESS;
		String l_message = Messages.PROFILE_ALLRETRIEVED;
    	
        System.out.println("Fetching User with id " + p_pfid);
        try{
        	l_profile = m_pService.findById(p_pfid);
        
        }catch(Exception e){
			l_code = Constants.ERROR;
			l_message = Messages.PROFILE_RETRIEVEALL_ERROR + e.getMessage();
        }
        
    	JResult l_result = new JResult(l_code, l_message);
        ProfileResp l_profileResp = new ProfileResp();
    	
        if (l_profile == null) {
            System.out.println("User with id " + p_pfid + " not found");
            l_result.setErrorCode(1);
            l_result.setErrorMessage(Messages.PROFILE_RETRIEVEALL_ERROR + "User not found");
            l_profileResp.setResult(l_result);
            return new ResponseEntity<ProfileResp>(l_profileResp, HttpStatus.OK);
        }
        
        l_profileResp.setResult(l_result);
        l_profileResp.setProfile(l_profile);
        return new ResponseEntity<ProfileResp>(l_profileResp, HttpStatus.OK);
    }
     
    //-------------------Create a User--------------------------------------------------------
     
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<Void> createProfile(@RequestBody Profile p_profile, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + p_profile.getUname());
 
        if (m_pService.isUserExist(p_profile)) {
            System.out.println("A User with name " + p_profile.getUname() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
 
        m_pService.save(p_profile);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{p_pfid}").buildAndExpand(p_profile.getPfid()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
     
    //------------------- Update a User --------------------------------------------------------
     
    @RequestMapping(value = "/user/{p_pfid}", method = RequestMethod.PUT)
    public ResponseEntity<Profile> updateProfile(@PathVariable("p_pfid") int p_pfid, @RequestBody Profile p_profile) {
        System.out.println("Updating User " + p_pfid);
         
        Profile l_currentProfile = m_pService.findById(p_pfid);
         
        if (l_currentProfile==null) {
            System.out.println("User with p_pfid " + p_pfid + " not found");
            return new ResponseEntity<Profile>(HttpStatus.NOT_FOUND);
        }
 
        l_currentProfile = p_profile;
        
        m_pService.update(l_currentProfile);
        return new ResponseEntity<Profile>(l_currentProfile, HttpStatus.OK);
    }
 
    //------------------- Delete a User --------------------------------------------------------
     
    @RequestMapping(value = "/user/{p_pfid}", method = RequestMethod.DELETE)
    public ResponseEntity<Profile> deleteProfile(@PathVariable("p_pfid") int p_pfid) {
        System.out.println("Fetching & Deleting User with p_pfid " + p_pfid);
 
        Profile l_profile = m_pService.findById(p_pfid);
        if (l_profile == null) {
            System.out.println("Unable to delete. User with id " + p_pfid + " not found");
            return new ResponseEntity<Profile>(HttpStatus.NOT_FOUND);
        }
 
        m_pService.delete(p_pfid);
        return new ResponseEntity<Profile>(HttpStatus.NO_CONTENT);
    }
 
     
    //------------------- Delete All Users --------------------------------------------------------
     
//    @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
//    public ResponseEntity<Profile> deleteAllProfiles() {
//        System.out.println("Deleting All Users");
// 
//        m_pService.delete();
//        return new ResponseEntity<Profile>(HttpStatus.NO_CONTENT);
//    }
// 

}
