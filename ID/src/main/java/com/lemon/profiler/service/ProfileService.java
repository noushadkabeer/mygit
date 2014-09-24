package com.lemon.profiler.service;

import java.util.List; 

import com.lemon.profiler.model.Profile;

public interface ProfileService {
	public List<Profile> getAllProfiles();
	public Profile getProfile(String id);
	public Profile getProfileByName(String name);
    public void update(Profile profile);
    public void insert(Profile profile);
    public String delete(String id);
    public List<Profile> searchProfile(String searchString, String pageNum, String pageSize);
}
