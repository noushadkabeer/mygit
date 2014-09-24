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
				<s:if test="!profile.id.equals('')">
					<h2>Edit Profile</h2>
				</s:if>
				<s:else>
					<h2>New Profile</h2>
				</s:else>
				</div>
				<s:fielderror cssClass="alert alert-error" />
				
				<s:form>
					<table class="table table-striped">
					<s:if test="!profile.id.equals('')">
						<tr>
							<td class="tdLabel"><s:text name="label.id" /></td>
							<td><s:textfield name="profile.id" size="30" readonly="true" /></td>
						</tr>
					</s:if>
						<tr>
							<td class="tdLabel"><s:text name="label.name" /></td>
							<td><s:textfield name="profile.name" size="30" /></td>
						</tr>
						<tr>
							<td class="tdLabel"><s:text name="label.experience" /></td>
							<td><s:textfield name="profile.experience" size="30" /></td>
						</tr>
						<tr>
							<td class="tdLabel"><s:text name="label.education" /></td>
							<td><s:textfield name="profile.education" size="30" /></td>
						</tr>
						<tr>
							<td class="tdLabel"><s:text name="label.skills" /></td>
							<td><s:textfield name="profile.skills" size="30" /></td>
						</tr>
						<tr>
							<td class="tdLabel"><s:text name="label.interests" /></td>
							<td><s:textfield name="profile.interests" size="30" /></td>
						</tr>
						<tr>
							<td class="tdLabel"><s:text name="label.location" /></td>
							<td><s:textfield name="profile.location" size="30" /></td>
						</tr>
						<tr>
							<td class="tdLabel"><s:text name="label.address" /></td>
							<td><s:textfield name="profile.address" size="30" /></td>
						</tr>
						<tr>
							<td class="tdLabel"><s:text name="label.resumeSummary" /></td>
							<td><s:textfield name="profile.resumeSummary" size="30" /></td>
						</tr>		
										
						<s:if test="!profile.id.equalsIgnoreCase('')">
					      <tr>
								<td>Attach resume</td>
								<td>
								<s:url id="attachResume" action="attachResume">
			       		   						<s:param name="profile.id" value="profile.id"/>
			       							</s:url> <s:a href="%{attachResume}" cssClass="btn btn-info"><i
											class="icon icon-briefcase"></i>Attach Resume</s:a> 														
								
								</td>
							</tr>
					   </s:if> 
						
					</table>		
					<div align="center">			
					<s:submit action="insertOrUpdateProfile" key="button.label.submit" cssClass="btn btn-info" /> 
					<s:reset key="button.label.cancel" cssClass="btn btn-info" />
					
					</div>
				</s:form>
				
				<!-- <s:form id="formUp2" action="uploadDoc.action" namespace="/"
								method="POST" enctype="multipart/form-data">
								<s:file name="fileUpload" label="Select a File to upload" size="40" />
								<!- -<s:submit value="submit" name="submit" /> - ->
								<s:submit id="formSubmit1" formIds="formUp2"
									value="Upload Submit" indicator="indicator" button="true" />
							</s:form>
				
				<!-- 
				<div style="width:400px;height:300px;" align="center"><form action="/file-upload" class="dropzone"
						id="my-awesome-dropzone"></form></div> -->
<a href="${pageContext.request.contextPath}/agencydashboard" class="btn btn-info">
		        <i class="icon icon-arrow-left"></i> Back to Dashboard
	        </a>
			</div>
			<!--/row-->
		</div>
		<!--/span-->
	</div>
	<!--/row-->
</body>
</html>
