<%@ include file="/init.jsp"%>

<portlet:actionURL name="newEmployee" var="actionNewEmployee" />

<br />

<form action="${actionNewEmployee}" method="Post">
	Name: <input type="text" name="<portlet:namespace/>name" /> <input
		type="submit" value="Submit">
</form>
<br />