<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="ISO-8859-1">
    <title>Development Seminar</title>
    <link href="style.css" rel="stylesheet" type="text/css">
    <script src="functions.js"></script>
</head>

<body>
	
	<%@ page import="hw4.model.RegistrationInfo" %>
	<%@	page import="hw4.model.CostInfo" %>
	
	<%
		// get attributes from the request
		RegistrationInfo registrationInfo = (RegistrationInfo) request.getAttribute("registrationInfo");
	%>
	
    <div class="main">
        <img src="../images/jhu.jpg">

        <h3>JOHNS HOPKINS ANNUAL SOFTWARE DEVELOPMENT SEMINAR</h3>
        
        <div class="output">
        	<b><%= registrationInfo.getName() %></b>
        </div>

        <div class="output">
        	You are registered as a <b><%= registrationInfo.getEmploymentStatus() %></b>.
        </div>

        <div class="output">
        	Your e-mail confirmation will be sent to: <b><%= registrationInfo.getEmail() %></b>.
        </div>

        <table>
        	<tr>
        		<th>Your Courses</th>
        		<th></th>
        		<th>Cost</th>
        	</tr>
       	<%
       		// loop through the courses selected and build out the table
       		String[] courses  = registrationInfo.getCourses();
       		for (int i = 0; i < courses.length; i++) {
        %>
        	<tr>
        		<td><%= courses[i]%></td>
        		<td></td>
        		<td class="money">$<%= registrationInfo.getCostInfo().getEmployeeStatusCost() %>0</td>
        	</tr>
        <%
        	}
       	%>
        </table>

		<br>
		
		<table>
		<% 
			// if the user selected a hotel, add that as a table record
			if (registrationInfo.getHotel() != null) {
		%>
			<tr>
				<td>Hotel Accommodation</td>
				<td class="money">$<%= CostInfo.HOTEL %>0</td>
			</tr>
		<%
			}
		%>
		<% 
			// if the user selected parking, add that as a table record
			if (registrationInfo.getParking() != null) {
		%>
			<tr>
				<td>Parking</td>
				<td class="money">$<%= CostInfo.PARKING %>0</td>
			</tr>
		<%
			}
		%>
			<tr>
				<td></td>
				<td class="money"><b>Total</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>$<%= registrationInfo.getCostInfo().getTotal() %>0</b></td>
			</tr>
		</table>
		
		<br>
		
        <input id="submit" type="submit" value="Back" onclick="goBack()">
    </div>

</body>
</html>