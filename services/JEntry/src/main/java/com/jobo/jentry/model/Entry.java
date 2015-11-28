package com.jobo.jentry.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Table;

import com.jobo.jentry.exceptions.JDataException;
import com.jobo.jentry.utils.DatabaseUtilities;
import com.jobo.jentry.utils.LogUtilities;
import com.jobo.jentry.utils.JResult;

@Entity
@Table(appliesTo = "jentry")
public class Entry {

	private static final long serialVersionUID = 1997753363232807009L;
	private JResult m_result = new JResult(-1, "");
    
	private int m_eid;
	private int m_pfid;
	private String 	m_jobtitle = "";
	private String 	m_company = "";
	private String 	m_adwhere = "";
	private Date m_addate = DatabaseUtilities.getCurrentSQLDate();
	private String 	m_cname1 = "";
	private String 	m_cphone1 = "";
	private String 	m_cemail1 = "";
	private String 	m_cname2 = "";
	private String 	m_cphone2 = "";
	private String 	m_cemail2 = "";
	private Byte 	m_cvsent = 0;
	private String 	m_replyrcvd = "";
	private String 	m_intvwdates = "";
	private Date 	m_followupdt = DatabaseUtilities.getCurrentSQLDate();
	private Byte 	m_followup = 0;
	private String 	m_comments = "";
	private Timestamp 	m_tmstamp = DatabaseUtilities.getCurrentSQLTimeStamp();
	
	public Entry() {
		super();
	}
	
	public Entry (int p_eid,
			int p_pfid,
			String 	p_jobtitle,
			String 	p_company,
			String 	p_adwhere,
			Date 	p_addate,
			String 	p_cname1,
			String 	p_cphone1,
			String 	p_cemail1,
			String 	p_cname2,
			String 	p_cphone2,
			String 	p_cemail2,
			Byte 	p_cvsent,
			String 	p_replyrcvd,
			String 	p_intvwdates,
			Date 	p_followupdt,
			Byte 	p_followup,
			String 	p_comments,
			Timestamp 	p_tmstamp) throws JDataException{
			
			try{			
				setEid(p_eid);
				setPfid(p_pfid);
				setJobtitle(p_jobtitle);
				setCompany(p_company);
				setAdwhere(p_adwhere);
				setAddate(p_addate);
				setCname1(p_cname1);
				setCphone1(p_cphone1);
				setCemail1(p_cemail1);
				setCname2(p_cname2);
				setCphone2(p_cphone2);
				setCemail2(p_cemail2);
				setCvsent(p_cvsent);
				setReplyrcvd(p_replyrcvd);
				setIntvwdates(p_intvwdates);
				setFollowupdt(p_followupdt);
				setFollowup(p_followup);
				setComments(p_comments);
				setTmstamp(p_tmstamp);
			}catch(JDataException JDE){
				throw new JDataException("" + this.getClass().getSimpleName() 
						+ "." + LogUtilities.getMethodName() + ": Error in one of the setters -> " + JDE.getMessage());
			}
		}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "eid", unique = true, nullable = false)
	public int getEid() {
		return m_eid;
	}

	public void setEid(int p_eid) throws JDataException{
		
		if(p_eid < 0)
			throw new JDataException("" + this.getClass().getSimpleName() 
					+ "." + LogUtilities.getMethodName() + ": p_eid is lesser than zero");
		this.m_eid = p_eid;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "pfid", unique = true, nullable = false)
	public long getPfid() {
		return m_pfid;
	}

	public void setPfid(int p_pfid) throws JDataException {
		if(p_pfid < 0)
			throw new JDataException("" + this.getClass().getSimpleName() 
					+ "." + LogUtilities.getMethodName() + ": pfid is lesser than zero");
		this.m_pfid = p_pfid;
	}

	@Column(name = "jobtitle")
	public String getJobtitle() {
		return m_jobtitle;
	}

	public void setJobtitle(String p_jobtitle) {
		this.m_jobtitle = p_jobtitle;
	}

	@Column(name = "company")
	public String getCompany() {
		return m_company;
	}

	public void setCompany(String p_company) {
		this.m_company = p_company;
	}
	
	@Column(name = "adwhere")
	public String getAdwhere() {
		return m_adwhere;
	}

	public void setAdwhere(String p_adwhere) {
		this.m_adwhere = p_adwhere;
	}

	@Column(name = "addate")
	public Date getAddate() {
		return m_addate;
	}

	public void setAddate(Date p_addate) throws JDataException {
		
		if (!(p_addate instanceof Date))
			throw new JDataException("" + this.getClass().getSimpleName() 
					+ "." + LogUtilities.getMethodName() + ": Variable is not a valid Date");
		this.m_addate = p_addate;
	}

	@Column(name = "cname1")
	public String getCname1() {
		return m_cname1;
	}

	public void setCname1(String p_cname1) {
		this.m_cname1 = p_cname1;
	}

	@Column(name = "cphone1")
	public String getCphone1() {
		return m_cphone1;
	}
	
	public void setCphone1(String p_cphone1) {
		this.m_cphone1 = p_cphone1;
	}

	@Column(name = "cemail1")
	public String getCemail1() {
		return m_cemail1;
	}
	
	public void setCemail1(String p_cemail1) {
		this.m_cemail1 = p_cemail1;
	}

	@Column(name = "cname2")
	public String getCname2() {
		return m_cname2;
	}
	
	public void setCname2(String p_cname2) {
		this.m_cname2 = p_cname2;
	}

	@Column(name = "cphone2")
	public String getCphone2() {
		return m_cphone2;
	}

	public void setCphone2(String p_cphone2) {
		this.m_cphone2 = p_cphone2;
	}

	@Column(name = "cemail2")
	public String getCemail2() {
		return m_cemail2;
	}

	public void setCemail2(String p_cemail2) {
		this.m_cemail2 = p_cemail2;
	}

	@Column(name = "cvsent")
	public Byte getCvsent() {
		return m_cvsent;
	}
	
	public Boolean isCvsent(){
		
		if(m_cvsent == 1)
			return true;
		else
			return false;
	}
	
	public void setCvsent(Byte p_cvsent) throws JDataException {
		if(p_cvsent != 0 || p_cvsent != 1)
			throw new JDataException("" + this.getClass().getSimpleName() 
					+ "." + LogUtilities.getMethodName() + ": CV Sent field is not equal to 1 or 0");
		this.m_cvsent = p_cvsent;
	}

	@Column(name = "replyrcvd")
	public String getReplyrcvd() {
		return m_replyrcvd;
	}

	public void setReplyrcvd(String p_replyrcvd) {
		this.m_replyrcvd = p_replyrcvd;
	}

	@Column(name = "intvwdates")
	public String getIntvwdates() {
		return m_intvwdates;
	}
	
	public void setIntvwdates(String p_intvwdates) {
		this.m_intvwdates = p_intvwdates;
	}

	@Column(name = "followupdt")
	public Date getFollowupdt() {
		return m_followupdt;
	}

	public void setFollowupdt(Date p_followupdt) throws JDataException{
		
		if (!(p_followupdt instanceof Date))
			throw new JDataException("" + this.getClass().getSimpleName() 
					+ "." + LogUtilities.getMethodName() + ": Variable is not a valid Date");
		this.m_followupdt = p_followupdt;
	}

	@Column(name = "followup")
	public Byte getFollowup() {
		return m_followup;
	}
	
	public Boolean isFollowup(){
	
		if(m_followup == 1)
			return true;
		else
			return false;
	}
	
	public void setFollowup(Byte p_followup) throws JDataException {
		
		if(p_followup != 0 || p_followup != 1)
			throw new JDataException("" + this.getClass().getSimpleName() 
					+ "." + LogUtilities.getMethodName() + ": followup field is not equal to 1 or 0");
		this.m_followup = p_followup;
	}

	@Column(name = "comments")
	public String getComments() {
		return m_comments;
	}
	
	public void setComments(String p_comments) {
		this.m_comments = p_comments;
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

	public JResult getResult() {
		return m_result;
	}

	public void setResult(JResult m_result) {
		this.m_result = m_result;
	}

	
}
