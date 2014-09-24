package com.lemon.profiler.model;

import java.io.Serializable;

public class Profile implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String id;	
	public String name;
	public String experience;
	public String education;
	public String skills;
	public String interests;
	public String location;
	public String address;
	public String resumeSummary;
	
	public Attachment attachments;


	public Profile(){}
	
//	public Profile(String id, String name, String experience, String education,
//			String skills, String interests, String location, String address,
//			String resumeSummary) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.experience = experience;
//		this.education = education;
//		this.skills = skills;
//		this.interests = interests;
//		this.location = location;
//		this.address = address;
//		this.resumeSummary = resumeSummary;
//	}
	
	
	
	public Profile(String id, String name, String experience, String education,
			String skills, String interests, String location, String address,
			String resumeSummary, Attachment attachments) {
		super();
		this.id = id;
		this.name = name;
		this.experience = experience;
		this.education = education;
		this.skills = skills;
		this.interests = interests;
		this.location = location;
		this.address = address;
		this.resumeSummary = resumeSummary;
		this.attachments = attachments;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	public String getInterests() {
		return interests;
	}
	public void setInterests(String interests) {
		this.interests = interests;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getResumeSummary() {
		return resumeSummary;
	}
	public void setResumeSummary(String resumeSummary) {
		this.resumeSummary = resumeSummary;
	}	
	public Attachment getAttachments() {
		return attachments;
	}
	public void setAttachments(Attachment attachments) {
		this.attachments = attachments;
	}
}
