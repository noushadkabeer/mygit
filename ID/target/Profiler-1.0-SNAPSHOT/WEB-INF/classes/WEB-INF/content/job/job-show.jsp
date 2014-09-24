<!DOCTYPE html>
<%@taglib prefix="s" uri="/struts-tags" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Order ${id}</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap-responsive.css" rel="stylesheet">
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <div class="page-header">
                <h1>Order ${id}</h1>
            </div>
            <table class="table table-striped">
               <tr>
                    <td class="span3">Title</td>
                    <td class="span9"><s:property value="jobTitle"/></td>
                </tr>
                <tr>
                    <td class="span3">Job ID</td>
                    <td class="span9"><s:property value="jobId"/></td>
                </tr>                
                <tr>
                    <td class="span3">Experience</td>
                    <td class="span9"><s:property value="jobExperience"/></td>
                </tr>
                <tr>
                    <td class="span3">Location</td>
                    <td class="span9"><s:property value="location"/></td>
                </tr>
                <tr>
                    <td class="span3">Company</td>
                    <td class="span9"><s:property value="company"/></td>
                </tr>
            </table>
	        <a href="${pageContext.request.contextPath}/agencydashboard" class="btn btn-info">
		        <i class="icon icon-arrow-left"></i> Back to Dashboard
	        </a>
        </div><!--/row-->
    </div><!--/span-->
</div><!--/row-->
</body>
</html>
	