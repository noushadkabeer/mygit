<!DOCTYPE html>
<%@taglib prefix="s" uri="/struts-tags" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <title>New Order</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap-responsive.css" rel="stylesheet">
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
	        <div class="page-header">
		        <h2>View Profile</h2>
	        </div>
	        <s:fielderror  cssClass="alert alert-error"/>

	        <s:form method="post" action="%{#request.contextPath}/insertOrUpdateProfile" cssClass="form-horizontal" theme="simple">
                <div class="control-group">
                    <label class="control-label" for="name">Name</label>
                    <div class="controls">
                        <s:textfield id="name" name="profile.name"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="amount">Experience</label>
                    <div class="controls">
                            <s:textfield id="experience" name="profile.experience" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="amount">Education</label>
                    <div class="controls">
                            <s:textfield id="education" name="profile.education" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="amount">Skills</label>
                    <div class="controls">
                            <s:textfield id="skills" name="profile.skills" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="amount">Interests</label>
                    <div class="controls">
                            <s:textfield id="interests" name="profile.interests" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="amount">Location</label>
                    <div class="controls">
                            <s:textfield id="location" name="profile.location" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="amount">Address</label>
                    <div class="controls">
                            <s:textfield id="address" name="profile.address" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="amount">Resume Summary</label>
                    <div class="controls">
                            <s:textfield id="resumeSummary" name="profile.resumeSummary" />
                    </div>
                </div>
                <div class="form-actions">
                    <s:submit cssClass="btn btn-primary"/>
                </div>
            </s:form>
	        <a href="${pageContext.request.contextPath}/agencydashboard" class="btn btn-info">
		        <i class="icon icon-arrow-left"></i> Back to Dashboard
	        </a>
        </div><!--/row-->
    </div><!--/span-->
</div><!--/row-->
</body>
</html>
	