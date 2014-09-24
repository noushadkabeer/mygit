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
					<h2>Edit Profile</h2>
				</div>
				<s:fielderror cssClass="alert alert-error" />
				
				<s:form>
					<table class="table table-striped">
						<tr>
							<td class="tdLabel"><s:text name="label.id" /></td>
							<td><s:textfield name="profile.id" size="30" readonly="true" /></td>
						</tr>
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

						<tr>
							<td>Attach resume</td>
							<td><s:text name="profile.id"></s:text>
							<s:url id="attachResume" action="attachResume">
		       		   						<s:param name="profile.id" value="profile.id"/>
		       							</s:url> <s:a href="%{attachResume}" cssClass="btn btn-danger"><i
										class="icon icon-trash"></i>Attach Resume</s:a> 														
							
							</td>
						</tr>
					</table>
					
					<table>
						<tr>
							<td><s:submit action="insertOrUpdateProfile"
									key="button.label.submit" cssClass="butStnd" /> <s:reset
									key="button.label.cancel" cssClass="butStnd" /></td>
						<tr>
					</table>
				</s:form>
				
				<s:form id="formUp2" action="uploadDoc.action" namespace="/"
								method="POST" enctype="multipart/form-data">
								<s:file name="fileUpload" label="Select a File to upload" size="40" />
								<!--<s:submit value="submit" name="submit" /> -->
								<s:submit id="formSubmit1" formIds="formUp2"
									value="Upload Submit" indicator="indicator" button="true" />
							</s:form>
				
				<!-- 
				<div style="width:400px;height:300px;" align="center"><form action="/file-upload" class="dropzone"
						id="my-awesome-dropzone"></form></div> -->
				

			</div>
			<!--/row-->
		</div>
		<!--/span-->
	</div>
	<!--/row-->
</body>
</html>
