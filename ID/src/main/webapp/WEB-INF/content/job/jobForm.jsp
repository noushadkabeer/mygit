<!DOCTYPE html>
<%@taglib prefix="s" uri="/struts-tags"%>

<html lang="en">
<head>
<meta charset="utf-8">
<title>New Order</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/css/bootstrap-responsive.css"
	rel="stylesheet">
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">

				<div class="page-header">
					<h2>Edit Job</h2>
				</div>
				<s:fielderror cssClass="alert alert-error" />
				<s:form>
					<table class="table table-striped" width="300px;"> 
					<tr>
							<td class="tdLabel"><s:text name="label.jobId" /></td>
							<td><s:textfield name="job.jobId" size="30" readonly="true"/></td>
						</tr>
						<tr>
							<td class="tdLabel"><s:text name="label.jobExperience" /></td>
							<td><s:textfield name="job.jobExperience" size="30" /></td>
						</tr>
						<tr>
							<td class="tdLabel"><s:text name="label.jobTitle" /></td>
							<td><s:textfield name="job.jobTitle" size="30" /></td>
						</tr>
						<tr>
							<td class="tdLabel"><s:text name="label.company" /></td>
							<td><s:textfield name="job.company" size="30" /></td>
						</tr>
						<tr>
							<td class="tdLabel"><s:text name="label.location" /></td>
							<td><s:textfield name="job.location" size="30" /></td>
						</tr>
						<tr>
							<td class="tdLabel"><s:text name="label.jobType" /></td>
							<td><s:textfield name="job.jobType" size="30" /></td>
						</tr>
						<tr>
							<td class="tdLabel"><s:text name="label.salary" /></td>
							<td><s:textfield name="job.salary" size="30" /></td>
						</tr>
						<tr>
							<td class="tdLabel"><s:text name="label.referenceCode" /></td>
							<td><s:textfield name="job.referenceCode" size="30" /></td>
						</tr>
						<tr>
							<td class="tdLabel"><s:text name="label.contactInfo" /></td>
							<td><s:textfield name="job.contactInfo" size="30" /></td>
						</tr> 
						<tr>
							<td class="tdLabel"><s:text name="label.aboutJob" /></td>
							<td><s:textfield name="job.aboutJob" size="30" /></td>
						</tr> 
						<tr>
							<td class="tdLabel"><s:text name="label.noOfVaccancies" /></td>
							<td><s:textfield name="job.noOfVaccancies" size="30" /></td>
						</tr> 
						</table>
						<table>
						<tr>
							<td><s:submit action="insertOrUpdateJob"
									key="button.label.submit" cssClass="butStnd" /> <s:reset key="button.label.cancel" cssClass="butStnd" /></td>
						<tr>
					</table>
				</s:form>

				<a href="${pageContext.request.contextPath}/agencydashboard"
					class="btn btn-info"> <i class="icon icon-arrow-left"></i> Back
					to Dashboard
				</a>
			</div>
			<!--/row-->
		</div>
		<!--/span-->
	</div>
	<!--/row-->
</body>
</html>
