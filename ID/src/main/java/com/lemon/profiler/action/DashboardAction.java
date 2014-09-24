package com.lemon.profiler.action;

import java.util.List;

import com.lemon.profiler.model.Event;
import com.lemon.profiler.model.Job;
import com.lemon.profiler.model.Profile;
import com.lemon.profiler.model.Task;
import com.lemon.profiler.service.EventService;
import com.lemon.profiler.service.JobService;
import com.lemon.profiler.service.ProfileService;
import com.lemon.profiler.service.TaskService;
import com.lemon.profiler.service.impl.EventServiceImpl;
import com.lemon.profiler.service.impl.JobServiceImpl;
import com.lemon.profiler.service.impl.ProfileServiceImpl;
import com.lemon.profiler.service.impl.TaskServiceImpl;


public class DashboardAction {
	JobService jobService;
	ProfileService profileService;
	TaskService taskService;
	EventService eventService;
	
	public List<Job> jobs;
	public List<Task> tasks;
	public List<Profile> profiles;
	public List<Event> events;
		
	
	public String prepareDashboard(){		
		//get the tasks pending
		taskService = new TaskServiceImpl();
		tasks = taskService.getTaskList();
		
		//Resume list
		profileService= new ProfileServiceImpl();
		profiles = profileService.getAllProfiles();
		System.out.println(profiles.size());
		
		//Job listing list
		jobService = new JobServiceImpl();
		jobs = jobService.getAllJobs();
		
		//Event list
		eventService = new EventServiceImpl();
		events = eventService.getAllEvents();
		
		System.out.println("Dashboard Prepared with :"+jobs.size()+" Jobs, "+profiles.size() + " Profiles , "+tasks.size()+ " tasks, "+ events.size()+" events.");
			
		return "success";
	}
	
	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}
	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public List<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

}
