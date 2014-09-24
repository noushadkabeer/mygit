package com.lemon.profiler.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.lemon.profiler.model.Job; 
import com.lemon.profiler.service.JobService; 
import com.lemon.profiler.service.impl.JobServiceImpl; 
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class JobAction extends ActionSupport implements Preparable{

	private static final long serialVersionUID = 1L;
	private static JobService jobService = new JobServiceImpl();
	private Job job; 
	private List<Job> jobs;

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	private static final Logger log = Logger.getLogger(JobAction.class);


	public String getAllJobs() {
		jobs = jobService.getAllJobs();
		return "success";
	}

	public String setUpForInsertOrUpdate() {
		if (job != null && !job.getJobId().isEmpty()) {
			System.out.println("job not null. :"+job.getJobId());
			job = jobService.getJob(job.getJobId());
		}
		return "success";
	}

	public String insertOrUpdate() {
		System.out.println("insert or update check..");
		if (!validationSuccessful()) {
			System.out.println("Validation failed..");
			return "input";
		} else {
			if (job.getJobId() == null || job.getJobId().isEmpty()) {
				System.out.println("job insert..");
				jobService.insert(job);
			} else {
				System.out.println(">>>>>>>>>>>>>>>> job Update"
						+ job.jobId);
				jobService.update(job);
			}
		}
		return "success";
	}

	private boolean validationSuccessful() {
		System.out.println("==============================>");
		if (job == null) {
			System.out.println("job null");
			if (log.isDebugEnabled()) {
				log.debug("job - : " + " items found");
			}
		}
		if (job.getJobTitle() == null || job.getJobTitle().trim().length() < 1) {
			// addActionMessage("Name is required");
			return false;
			// }
			// if(this.hasActionMessages()){
			// return false;
		} else {
			return true;
		}
	}

	public String viewJob() {
		System.out.println("View job");
		return "success";
	}

	public String editJob() {
		System.out.println("Edit job");
		return "success";
	}

	public String deleteConfirm() {
		System.out.println(job.jobId);
		job = jobService.getJob(job.getJobId());
		System.out.println("Delete Confirmation..");
		return "success";
	}

	public String deleteJob() {
		String result = jobService.delete(job.jobId);
		System.out.println("Deleting Job" + job.jobId);
		return result;
	}
	
	public String attachJD(){
		job = jobService.getJob(job.getJobId());
		return "success";
	}
	@Override
	public void prepare() throws Exception {
//		if (job != null && job.getJobTitle() != null) {
//			job = jobService.getJob(job.getJobId());
//		}
	}
}
