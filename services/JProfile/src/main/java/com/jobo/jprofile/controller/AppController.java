package com.jobo.jprofile.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jobo.jprofile.model.Profile;
import com.jobo.jprofile.service.ProfileService;

@Controller
@RequestMapping("/")
public class AppController {
	
	@Autowired
	ProfileService m_service;
	
	@Autowired
	MessageSource m_messageSource;

	/*
	 * This method will list all existing employees.
	 */
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String list(ModelMap p_model) {

		List<Profile> l_profiles = m_service.findAllProfiles();
		p_model.addAttribute("profiles", l_profiles);
		return "allprofiles";
	}

	/*
	 * This method will provide the medium to add a new employee.
	 */
	@RequestMapping(value = { "/add" }, method = RequestMethod.GET)
	public String add(ModelMap p_model) {
		Profile l_profile = new Profile();
		p_model.addAttribute("profile", l_profile);
		p_model.addAttribute("edit", false);
		return "registration";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * saving profile in database. It also validates the user input
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String save(@Valid Profile p_profile, BindingResult p_result,
			ModelMap p_model) {

		if (p_result.hasErrors()) {
			return "registration";
		}

		m_service.save(p_profile);

		p_model.addAttribute("success", "Profile " + p_profile.getUname() + " registered successfully");
		return "success";
	}


	/*
	 * This method will provide the medium to update an existing employee.
	 */
	@RequestMapping(value = { "/edit-{p_pfid}-profile" }, method = RequestMethod.GET)
	public String editEmployee(@PathVariable int p_pfid, ModelMap p_model) {
		Profile l_profile = m_service.findById(p_pfid);
		p_model.addAttribute("profile", l_profile);
		p_model.addAttribute("edit", true);
		return "registration";
	}
	
	/*
	 * This method will be called on form submission, handling POST request for
	 * updating profile in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-{p_pfid}-profile" }, method = RequestMethod.POST)
	public String updateEmployee(@Valid Profile p_profile, BindingResult p_result,
			ModelMap p_model, @PathVariable int p_pfid) {

		if (p_result.hasErrors()) {
			return "registration";
		}

		m_service.update(p_profile);

		p_model.addAttribute("success", "Profile " + p_profile.getUname() + " updated successfully");
		return "success";
	}

	
	/*
	 * This method will delete an employee by it's SSN value.
	 */
	@RequestMapping(value = { "/delete-{p_pfid}-profile" }, method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable int p_pfid) {
		m_service.delete(p_pfid);
		return "redirect:/list";
	}


}
