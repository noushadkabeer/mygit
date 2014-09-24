<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div align="right"> <a href="setUpForInsertOrUpdateProfile" class="btn btn-primary"><i
					class="icon icon-file"></i>Upload Resume</a> <a href="setUpForInsertOrUpdateJob"
					class="btn btn-primary"><i class="icon icon-file"></i>Add Job</a> 
					<a href="setUpForInsertOrUpdateEvent" class="btn btn-primary"><i class="icon icon-file"></i>Add Event</a> 
					<a href="setUpForInsertOrUpdateTask" class="btn btn-primary"><i class="icon icon-file"></i>Add Task</a> </div>
				<div class="page-header">
					<h2>Jobs On Board</h2>
				</div>
				<s:actionmessage cssClass="alert alert-error" />
				<table class="table table-striped">
					<tr>
						<th>Job ID</th>
						<th>Job Experience</th>
						<th>Title</th>
						<th>Location</th>
						<th>Company</th>
						<th>Actions</th>
					</tr>
					<s:iterator value="jobs">
						<tr>
							<td><s:property value="jobId" /></td>
							<td><s:property value="jobExperience" /></td>
							<td><s:property value="jobTitle" /></td>
							<td><s:property value="location" /></td>
							<td><s:property value="company" /></td>
							<td>
								<div class="btn-group">
									<a href="viewJob" class="btn"><i
										class="icon icon-eye-open"></i>View</a> 
										<s:url id="update" action="setUpForInsertOrUpdateJob">
		       		   						<s:param name="job.jobId" value="jobId"/>
		       							</s:url> <s:a href="%{update}" cssClass="btn"><i
										class="icon icon-edit"></i>Edit</s:a>
										<s:url id="delete" action="deleteJobConfirm">
		       		   						<s:param name="job.jobId" value="jobId"/>
		       							</s:url> <s:a href="%{delete}" cssClass="btn btn-danger"><i
										class="icon icon-trash"></i>Delete</s:a> 										 
								</div>
							</td>
						</tr>
					</s:iterator>
				</table>
					
				<!-- Profiles -->
				<div class="page-header">
					<h2>Profiles</h2>
				</div>
				<s:actionmessage cssClass="alert alert-error" />
				<table class="table table-striped">
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Experience</th>
						<th>Education</th>
						<th>Skills</th>
						<th>Interests</th>
						<th>Location</th>
						<th>Address</th>
						<th>Summary</th>
						<th>Actions</th>
					</tr>
					<s:iterator value="profiles">
						<tr>
							<td><s:property value="id" /></td>
							<td><s:property value="name" /></td>
							<td><s:property value="experience" /></td>
							<td><s:property value="education" /></td>
							<td><s:property value="skills" /></td>
							<td><s:property value="interests" /></td>
							<td><s:property value="location" /></td>
							<td><s:property value="address" /></td>
							<td><s:property value="resumeSummary" /></td>
							<td> 
								<div class="btn-group"> 
								<s:url id="viewProfile" action="viewProfile">
		       		   						<s:param name="profile.id" value="id"/>
		       							</s:url> <s:a href="%{viewProfile}" cssClass="btn"><i
										class="icon icon-eye-open"></i>View</s:a> 
										<s:url id="update" action="setUpForInsertOrUpdateProfile">
		       		   						<s:param name="profile.id" value="id"/>
		       							</s:url> <s:a href="%{update}" cssClass="btn"><i
										class="icon icon-edit"></i>Edit</s:a>
										<s:url id="delete" action="deleteProfileConfirm">
		       		   						<s:param name="profile.id" value="id"/>
		       							</s:url> <s:a href="%{delete}" cssClass="btn btn-danger"><i
										class="icon icon-trash"></i>Delete</s:a> 
								</div>
							</td>
						</tr>
					</s:iterator>
				</table>
							
				<!-- Tasks -->
				<div class="page-header">
					<h2>Tasks</h2>
				</div>
				<s:actionmessage cssClass="alert alert-error" />
				<table class="table table-striped">
					<tr>
						<th>Task ID</th>
						<th>Task Name</th>
						<th>Task Details</th>
						<th>Task Status</th>
						<th>Actions</th>
					</tr>
					<s:iterator value="tasks">
						<tr>
							<td><s:property value="taskId" /></td>
							<td><s:property value="taskName" /></td>
							<td><s:property value="taskDetails" /></td>
							<td><s:property value="taskStatus" /></td>
							<td> 
								<div class="btn-group">
									<a href="viewProfile" class="btn"><i
										class="icon icon-eye-open"></i>View</a> <a
										href="editProfile" class="btn"><i
										class="icon icon-edit"></i>Edit</a> <a
										href="deleteProfile" class="btn btn-danger"><i
										class="icon icon-trash"></i>Delete</a>
								</div>
							</td>
						</tr>
					</s:iterator>
				</table> 
				
				<!-- Events -->
				<div class="page-header">
					<h2>Events</h2>
				</div>
				<s:actionmessage cssClass="alert alert-error" />
				<table class="table table-striped">
					<tr>
						<th>Event ID</th>
						<th>Event Title</th>
						<th>Event Details</th>
						<th>Event Schedule</th>
						<th>Event Status</th>
						<th>Actions</th>
					</tr>
					<s:iterator value="events">
						<tr>
							<td><s:property value="eventId" /></td>
							<td><s:property value="eventTitle" /></td>
							<td><s:property value="eventDetails" /></td>
							<td><s:property value="eventSchedule" /></td>
							<td><s:property value="eventStatus" /></td>
							<td> 
								<div class="btn-group">
									<a href="viewProfile" class="btn"><i
										class="icon icon-eye-open"></i>View</a> <a
										href="editProfile" class="btn"><i
										class="icon icon-edit"></i>Edit</a> <a
										href="deleteProfile" class="btn btn-danger"><i
										class="icon icon-trash"></i>Delete</a>
								</div>
							</td>
						</tr>
					</s:iterator>
				</table>
				
			
				
			</div>
			<!--/row-->
		</div>
		<!--/span-->
	</div>
