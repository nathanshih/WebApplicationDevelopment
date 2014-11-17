<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Development Seminar</title>
    <link href="../styles/style.css" rel="stylesheet" type="text/css">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="../scripts/functions.js"></script>
</head>

<body>

	<jsp:useBean id="registrationService" scope="session" class="devseminar.service.RegistrationServiceImpl" />
	
    <div class="main">
        <img src="../images/jhu.jpg">

        <h3>JOHNS HOPKINS ANNUAL SOFTWARE DEVELOPMENT SEMINAR</h3>
        
        <div class="output">
        	<b><%= registrationService.getRegistrationInfo().getName() %></b>
        </div>

        <div class="output">
        	You are registered as a <b><%= registrationService.getRegistrationInfo().getEmploymentStatus() %></b>.
        </div>

        <div class="output">
        	Your e-mail confirmation will be sent to: <b><%= registrationService.getRegistrationInfo().getEmail() %></b>.
        </div>

        <table>
        	<tr>
        		<th>Your Courses</th>
        		<th>Cost</th>
        		<th></th>
        	</tr>
       	<%
       		String[] courses = registrationService.getCourses();
       		// loop through the courses selected and build out the table
       		for (int i = 0; i < courses.length; i++) {
        %>
        	<tr>
        		<td><%= courses[i] %></td>
        		<td class="money">$<%= registrationService.getRegistrationInfo().getCostInfo().getEmployeeStatusCost() %>0</td>
        		<td><button class="remove" value="<%= courses[i] %>">Remove</button></td>       		
        	</tr>
        <%
        	}
       	%>
        </table>

		<br>
		
		<table>
		<% 
			// if the user selected a hotel, add that as a table record
			if (registrationService.getRegistrationInfo().getHotel() != null) {
		%>
			<tr>
				<td>Hotel Accommodation</td>
				<td class="money">$<%= registrationService.getRegistrationInfo().getCostInfo().getHotelCost() %>0</td>
			</tr>
		<%
			}
		%>
		<% 
			// if the user selected parking, add that as a table record
			if (registrationService.getRegistrationInfo().getParking() != null) {
		%>
			<tr>
				<td>Parking</td>
				<td class="money">$<%= registrationService.getRegistrationInfo().getCostInfo().getParkingCost() %>0</td>
			</tr>
		<%
			}
		%>
			<tr>
				<td></td>
				<td class="money"><b>Total</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>$<%= registrationService.getRegistrationInfo().getCostInfo().getTotal() %>0</b></td>
			</tr>
		</table>
		
		<br>
		
		<div class="button">
	        <input id="edit" class="back" type="submit" value="Edit Information">
	        <input id="add" class="back" type="submit" value="Add More Courses">
	        <input id="checkout" type="submit" value="Checkout">
		</div>
    </div>

</body>
</html>