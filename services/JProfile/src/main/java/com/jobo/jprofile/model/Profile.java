package com.jobo.jprofile.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jobo.jprofile.exceptions.JDataException;
import com.jobo.jprofile.utils.DatabaseUtilities;
import com.jobo.jprofile.utils.JResult;
import com.jobo.jprofile.utils.LogUtilities;


@Entity
@Table(name="jprofile")
public class Profile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int m_pfid = 0;
	private String 	m_fname = "";
	private String 	m_mname = "";
	private String 	m_lname = "";
	private String 	m_pname = "";
	private String 	m_uname = "";
	private String 	m_email = "";
	private String 	m_phone = "";
	private String 	m_cell = "";
	private String 	m_adl1 = "";
	private String 	m_adl2 = "";
	private String 	m_suburb = "";
	private String 	m_city = "";
	private String 	m_region = "";
	private String 	m_country = "";
	private String 	m_postalcd = "";
	private String 	m_password = "";
	private Timestamp 	m_tmstamp = DatabaseUtilities.getCurrentSQLTimeStamp();
	
		
	public Profile() {
		super();
	}

	public Profile (int p_pfid,
		String 	p_fname,
		String 	p_mname,
		String 	p_lname,
		String 	p_pname,
		String 	p_uname,
		String 	p_email,
		String 	p_phone,
		String 	p_cell,
		String 	p_adl1,
		String 	p_adl2,
		String 	p_suburb,
		String 	p_city,
		String 	p_region,
		String 	p_country,
		String 	p_postalcd,
		String 	p_password,
		Timestamp 	p_tmstamp) throws JDataException{
		
		try{	
			setPfid(p_pfid);
			setFname(p_fname);
			setMname(p_mname);
			setLname(p_lname);
			setPname(p_pname);
			setUname(p_uname);
			setEmail(p_email);
			setPhone(p_phone);
			setCell(p_cell);
			setAdl1(p_adl1);
			setAdl2(p_adl2);
			setSuburb(p_suburb);
			setCity(p_city);
			setRegion(p_region);
			setCountry(p_country);
			setPostalcd(p_postalcd);
			setPassword(p_password);
			setTmstamp(p_tmstamp);
		}catch(JDataException JDE){
			throw new JDataException("" + this.getClass().getSimpleName() 
					+ "." + LogUtilities.getMethodName() + ": Error in one of the setters -> " + JDE.getMessage());
		}
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "pfid", unique = true, nullable = false)
	public int getPfid() {
		return m_pfid;
	}
	
	public void setPfid(int p_pfid) throws JDataException {
			
		if(p_pfid < 0)
			throw new JDataException("" + this.getClass().getSimpleName() 
					+ "." + LogUtilities.getMethodName() + ": pfid is lesser than zero");
		this.m_pfid = p_pfid;
	}

	@Column(name = "fname")
	public String getFname() {
		return m_fname;
	}
	
	public void setFname(String p_fname) {
		
		this.m_fname = p_fname;
	}

	@Column(name = "mname")
	public String getMname() {
		return m_mname;
	}
	
	public void setMname(String p_mname) {
		this.m_mname = p_mname;
	}
	
	@Column(name = "lname")
	public String getLname() {
		return m_lname;
	}

	public void setLname(String p_lname) {
		this.m_lname = p_lname;
	}
	
	@Column(name = "pname")
	public String getPname() {
		return m_pname;
	}

	public void setPname(String p_pname) {
		this.m_pname = p_pname;
	}
	
	@Column(name = "uname", unique = true, nullable = false)
	public String getUname() {
		return m_uname;
	}

	public void setUname(String p_uname) throws JDataException {
		
		if(p_uname == null)
			throw new JDataException("" + this.getClass().getSimpleName() 
					+ "." + LogUtilities.getMethodName() + ": Username is null");
		this.m_uname = p_uname;
	}
	
	@Column(name = "email", unique = true, nullable = false)
	public String getEmail() {
		return m_email;
	}

	public void setEmail(String p_email) throws JDataException {
		
		if(p_email == null)
			throw new JDataException("" + this.getClass().getSimpleName() 
					+ "." + LogUtilities.getMethodName() + ": Email is null");
		this.m_email = p_email;
	}

	@Column(name = "phone")
	public String getPhone() {
		return m_phone;
	}

	public void setPhone(String p_phone) {
		this.m_phone = p_phone;
	}

	@Column(name = "cell")
	public String getCell() {
		return m_cell;
	}

	public void setCell(String p_cell) {
		this.m_cell = p_cell;
	}

	@Column(name = "adl1")
	public String getAdl1() {
		return m_adl1;
	}
	
	public void setAdl1(String p_adl1) {
		this.m_adl1 = p_adl1;
	}
	
	@Column(name = "adl2")
	public String getAdl2() {
		return m_adl2;
	}

	public void setAdl2(String p_adl2) {
		this.m_adl2 = p_adl2;
	}
	
	@Column(name = "suburb")
	public String getSuburb() {
		return m_suburb;
	}

	public void setSuburb(String p_suburb) {
		this.m_suburb = p_suburb;
	}

	@Column(name = "city")
	public String getCity() {
		return m_city;
	}
	
	public void setCity(String p_city) {
		this.m_city = p_city;
	}

	@Column(name = "region")
	public String getRegion() {
		return m_region;
	}
	
	public void setRegion(String p_region) {
		this.m_region = p_region;
	}

	@Column(name = "country")
	public String getCountry() {
		return m_country;
	}
	
	public void setCountry(String p_country) {
		this.m_country = p_country;
	}
	
	@Column(name = "postalcd")
	public String getPostalcd() {
		return m_postalcd;
	}

	public void setPostalcd(String p_postalcd) {
		this.m_postalcd = p_postalcd;
	}
	
	@Column(name = "password")
	public String getPassword() {
		return m_password;
	}

	public void setPassword(String p_password) throws JDataException {
		
		if(p_password == null)
			throw new JDataException("" + this.getClass().getSimpleName() 
					+ "." + LogUtilities.getMethodName() + ": Password is null");
		this.m_password = p_password;
	}

	@Column(name = "tmstamp")
	public Timestamp getTmstamp() {
		return m_tmstamp;
	}
	
	public void setTmstamp(Timestamp p_tmstamp) throws JDataException {
		
		if (!(p_tmstamp instanceof Timestamp))
			throw new JDataException("" + this.getClass().getSimpleName() 
					+ "." + LogUtilities.getMethodName() + ": Variable is not a valid timestamp");
		this.m_tmstamp = p_tmstamp;
	}
	
}
	
