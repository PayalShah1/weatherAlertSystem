<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3>Alert Added Successfully!!</h3>
<h3>Alert Details</h3>
<table>
<tr>
	<td><span> User Name :</span></td>
	<td><span>${alert.user.userName}</span></td>
</tr>
<tr>
	<td><span> Email :</span></td>
	<td><span>${alert.user.email}</span></td>
</tr>
<tr>
	<td><span> Contact Number :</span></td>
	<td><span>${alert.user.contactNumber}</span></td>
</tr>
<tr>
	<td><span> Location :</span></td>
	<td><span>${alert.location.cityName}</span></td>
</tr>
<tr>	
	<td><span> Start Date :</span></td>
	<td><span>${alert.startTime}</span></td>
</tr>
<tr>	
	<td><span> End Date :</span></td>
	<td><span>${alert.endTime}</span></td>
</tr>
<tr>
	<td><span> Lower Threshold :</span></td>
	<td><span>${alert.lowerThreshold}</span></td>
</tr>
<tr>
	<td><span> Higher Threshold :</span></td>
	<td><span>${alert.higherThreshold}</span></td>
</tr>
<tr>
	<td><span> Notification Type :</span></td>
	<td><span>${alert.notificationType.type}</span></td>
</tr>
</table>

<form action="/">
<input value="Go to Home Page" type="submit"/>
</form>
</body>
</html>