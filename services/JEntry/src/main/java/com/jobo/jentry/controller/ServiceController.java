package com.jobo.jentry.controller;

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

import com.jobo.jentry.exceptions.JDataException;
import com.jobo.jentry.model.Entry;
import com.jobo.jentry.service.EntryService;
import com.jobo.jentry.utils.Constants;
import com.jobo.jentry.utils.JResult;
import com.jobo.jentry.utils.LogUtilities;
import com.jobo.jentry.utils.Messages;

@RestController
public class ServiceController {
	
    @Autowired
    EntryService m_eService;  //Service which will do all data retrieval/manipulation work
 
     
    //-------------------Retrieve All Users--------------------------------------------------------
     
    @RequestMapping(value = "/entry/", method = RequestMethod.GET)
    public ResponseEntity<List<Entry>> listAllEntries() {
    	List<Entry> l_entries  = null;
		int l_code = Constants.SUCCESS;
		String l_message = Messages.ENTRY_ALLRETRIEVED;
    	
    	try{
    		l_entries = m_eService.findAllEntries();
    		
			if(l_entries == null || l_entries.size() == 0)
				throw new JDataException("" + this.getClass().getSimpleName() 
						+ "." + LogUtilities.getMethodName() + ": The Profiles are not found");
			
    	}catch(Exception e){
			l_code = Constants.ERROR;
			l_message = Messages.ENTRY_RETRIEVEALL_ERROR + e.getMessage();
			
    	}
    	
    	JResult l_result = new JResult(l_code, l_message);
    	   	
        if(l_entries.isEmpty()){
            return new ResponseEntity<List<Entry>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Entry>>(l_entries, HttpStatus.OK);
    }
 
 
    //-------------------Retrieve Single Entry--------------------------------------------------------
     
    @RequestMapping(value = "/entry/{p_eid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Entry> getEntry(@PathVariable("p_eid") int p_eid) {
    	
    	Entry l_profile = null;
		int l_code = Constants.SUCCESS;
		String l_message = Messages.ENTRY_ALLRETRIEVED;
    	
        System.out.println("Fetching Entry with id " + p_eid);
        try{
        	l_profile = m_eService.findById(p_eid);
        
        }catch(Exception e){
			l_code = Constants.ERROR;
			l_message = Messages.ENTRY_RETRIEVEALL_ERROR + e.getMessage();
        }
        
    	JResult l_result = new JResult(l_code, l_message);
        
        if (l_profile == null) {
            System.out.println("User with id " + p_eid + " not found");
            l_profile = new Entry();
            l_profile.setResult(l_result);
            return new ResponseEntity<Entry>(l_profile, HttpStatus.NOT_FOUND);
        }
        
        l_profile.setResult(l_result);
        return new ResponseEntity<Entry>(l_profile, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/entrybypfid/{p_pfid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Entry> getEntryByPfid(@PathVariable("p_pfid") int p_pfid) {
    	
    	Entry l_profile = null;
		int l_code = Constants.SUCCESS;
		String l_message = Messages.ENTRY_ALLRETRIEVED;
    	
        System.out.println("Fetching Entry with id " + p_pfid);
        try{
        	l_profile = m_eService.findByPFId(p_pfid);
        
        }catch(Exception e){
			l_code = Constants.ERROR;
			l_message = Messages.ENTRY_RETRIEVEALL_ERROR + e.getMessage();
        }
        
    	JResult l_result = new JResult(l_code, l_message);
        
        if (l_profile == null) {
            System.out.println("User with id " + p_pfid + " not found");
            l_profile = new Entry();
            l_profile.setResult(l_result);
            return new ResponseEntity<Entry>(l_profile, HttpStatus.NOT_FOUND);
        }
        
        l_profile.setResult(l_result);
        return new ResponseEntity<Entry>(l_profile, HttpStatus.OK);
    }     
     
    //-------------------Create a User--------------------------------------------------------
     
    @RequestMapping(value = "/entry/", method = RequestMethod.POST)
    public ResponseEntity<Void> createEntry(@RequestBody Entry p_entry, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating EID " + p_entry.getEid());
 
        if (m_eService.isEntryExist(p_entry)) {
            System.out.println("A User with eid " + p_entry.getEid() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
 
        m_eService.save(p_entry);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/entry/{p_eid}").buildAndExpand(p_entry.getEid()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
     
    //------------------- Update a User --------------------------------------------------------
     
    @RequestMapping(value = "/entry/{p_eid}", method = RequestMethod.PUT)
    public ResponseEntity<Entry> updateEntry(@PathVariable("p_eid") int p_eid, @RequestBody Entry p_entry) {
        System.out.println("Updating Entry " + p_eid);

        Entry l_currentEntry = m_eService.findById(p_eid);
         
        if (l_currentEntry==null) {
            System.out.println("User with p_eid " + p_eid + " not found");
            return new ResponseEntity<Entry>(HttpStatus.NOT_FOUND);
        }
 
        l_currentEntry = p_entry;
        
        m_eService.update(l_currentEntry);
        return new ResponseEntity<Entry>(l_currentEntry, HttpStatus.OK);
    }
 
    //------------------- Delete a User --------------------------------------------------------
     
    @RequestMapping(value = "/entry/{p_eid}", method = RequestMethod.DELETE)
    public ResponseEntity<Entry> deleteEntry(@PathVariable("p_eid") int p_eid) {
        System.out.println("Fetching & Deleting Entry with p_eid " + p_eid);
 
        Entry l_entry = m_eService.findById(p_eid);
        if (l_entry == null) {
            System.out.println("Unable to delete. User with id " + p_eid + " not found");
            return new ResponseEntity<Entry>(HttpStatus.NOT_FOUND);
        }
 
        m_eService.delete(p_eid);
        return new ResponseEntity<Entry>(HttpStatus.NO_CONTENT);
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
