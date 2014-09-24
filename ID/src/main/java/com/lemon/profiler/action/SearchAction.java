package com.lemon.profiler.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.lemon.profiler.model.Node;
import com.lemon.profiler.model.Profile;
import com.lemon.profiler.service.ProfileService;
import com.lemon.profiler.service.SearchService;
import com.lemon.profiler.service.impl.ProfileServiceImpl;
import com.lemon.profiler.service.impl.SearchServiceImpl;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class SearchAction extends ActionSupport implements Preparable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SearchService searchService = new SearchServiceImpl();
	private Profile profile;
	private List<Node> nodes;
	private String textToSearch;

	private static final Logger log = Logger.getLogger(SearchAction.class);

	private List<Profile> profiles;

	public String textSearch() {
		log.info("Searching for text :"+textToSearch);
		nodes = searchService.searchText(textToSearch, "", "");
		return "success";
	}	

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	public String getTextToSearch() {
		return textToSearch;
	}

	public void setTextToSearch(String textToSearch) {
		this.textToSearch = textToSearch;
	}
	public List<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}

	public List<Node> getNodes() {
		return nodes;
	}
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
}
