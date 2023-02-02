<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome to temperature alert system</title>
<style type="text/css">
	h3{
		padding: 10px;
		margin: 10px;
	}
	label {
		width: 300px;
		margin: 5px;
	}
	
	button {
			padding: 10px;
			margin: 10px;
	}
	
	input {
		margin: 5px;
	}
	select {
		margin:5px;
	}
</style>
</head>
<body>
<h3>Register Temperature Alerts</h3>
<div>
<form:form action="register" method="post" modelAttribute="alert" onsubmit="return validateForm()">
<table>
<tr>
	<td><form:label path="user.userName"> User Name :</form:label></td>
	<td><form:input path="user.userName" type="text"/></td>
	<td><form:errors path="user.userName" cssStyle="color:red"/></td>
</tr>
<tr>
	<td><form:label path="user.email"> Email :</form:label></td>
	<td><form:input path="user.email" /></td>
	<td><form:errors path="user.email" cssStyle="color:red"/></td>
</tr>
<tr>
	<td><form:label path="user.contactNumber"> Contact Number :</form:label></td>
	<td><form:input path="user.contactNumber"/></td>
	<td><form:errors path="user.contactNumber" cssStyle="color:red"/></td>
</tr>
<tr>
	<td><form:label path="location"> Location :</form:label></td>
	<td><form:select path="location" items="${locationList}">
		<!-- <form:options itemValue="location.cityName"/> -->
	</form:select></td>
	<td><form:errors path="location" cssStyle="color:red"/></td>
</tr>
<tr>	
	<td><form:label path="startTime"> Start Date (yyyy-mm-dd hh:mm:ss) :</form:label></td>
	<td><form:input path="startTime"/></td>
	<td><form:errors path="startTime" cssStyle="color:red"/></td>
</tr>
<tr>	
	<td><form:label path="endTime"> End Date (yyyy-mm-dd hh:mm:ss) :</form:label></td>
	<td><form:input path="endTime"/></td>
	<td><form:errors path="endTime" cssStyle="color:red"/></td>
</tr>
<tr>
	<td><form:label path="lowerThreshold"> Lower Threshold :</form:label></td>
	<td><form:input path="lowerThreshold"/></td>
	<td><form:errors path="lowerThreshold" cssStyle="color:red"/></td>
</tr>
<tr>
	<td><form:label path="higherThreshold"> Higher Threshold :</form:label></td>
	<td><form:input path="higherThreshold"/></td>
	<td><form:errors path="higherThreshold" cssStyle="color:red"/></td>
</tr>
<tr>
	<td><form:label path="notificationType"> Notification Type :</form:label></td>
	<td><form:select path="notificationType" items="${notificationTypeList}"></form:select></td>
	<td><form:errors path="notificationType" cssStyle="color:red"/></td>
</tr>
	<tr>
	<td><form:button>Register</form:button></td>
	</tr>
</table>
</form:form>

</div>

</body>
</html>