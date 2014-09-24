package com.lemon.profiler.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.lemon.profiler.model.Profile;
import com.lemon.profiler.service.ProfileService;
import com.lemon.profiler.service.impl.ProfileServiceImpl;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class ProfileAction extends ActionSupport implements Preparable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ProfileService profileService = new ProfileServiceImpl();
	private Profile profile;

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	private static final Logger log = Logger.getLogger(ProfileAction.class);

	private List<Profile> profiles;

	public List<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}

	public String getAllProfiles() {
		profiles = profileService.getAllProfiles();
		return "success";
	}

	public String setUpForInsertOrUpdate() {
		if (profile != null && !profile.getId().isEmpty()) {
			System.out.println("Profile not null.");
			profile = profileService.getProfile(profile.getId());
		}
		return "success";
	}

	public String insertOrUpdate() {
		System.out.println("insert or update check..");
		if (!validationSuccessful()) {
			System.out.println("Validation failed..");
			return "input";
		} else {
			if (profile.getId() == null || profile.getId().isEmpty()) {
				System.out.println("Profile insert..");
				profileService.insert(profile);
			} else {
				System.out.println(">>>>>>>>>>>>>>>> Profile Update"
						+ profile.id);
				profileService.update(profile);
			}
		}
		return "success";
	}

	private boolean validationSuccessful() { 
		
		if (profile == null) {
			System.out.println("profile null");
			if (log.isDebugEnabled()) {
				log.debug("profile - : " + " items found");
			}
		}
		if (profile.getName() == null || profile.getName().trim().length() < 1) {
			 addActionMessage("Name is required");
			return false;
		}
		
		if(this.hasActionMessages()){
			 return false;
		} else {
			return true;
		}
	}

	public String viewProfile() {
		
		if(profile!=null || !profile.getId().isEmpty()){
			profile = profileService.getProfile(profile.getId());
		}
		System.out.println("View Profile");
		return "success";
	}

	public String editProfile() {
		System.out.println("Edit Profile");
		return "success";
	}

	public String deleteConfirm() {
		System.out.println(profile.id);
		profile = profileService.getProfile(profile.getId());
		System.out.println("Delete Confirmation..");
		return "success";
	}

	public String deleteProfile() {
		String result = profileService.delete(profile.id);
		System.out.println("Deleting Profile" + profile.id);
		return result;
	}

	public String attachResume(){
		System.out.println(profile.getId());
		profile = profileService.getProfile(profile.getId());
		return "success";
	}
	@Override
	public void prepare() throws Exception {
//		if (profile != null && profile.getName() != null) {
//			profile = profileService.getProfile(profile.getName());
//		}
	}

}
