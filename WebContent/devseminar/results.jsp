<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="ISO-8859-1">
    <title>Development Seminar</title>
    <link href="style.css" rel="stylesheet" type="text/css">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="functions.js"></script>
</head>

<body>

	<jsp:useBean id="registrationInfo" scope="session" class="devseminar.model.RegistrationInfo" />
	<jsp:useBean id="costInfo" scope="session" class="devseminar.model.CostInfo" />
	
    <div class="main">
        <img src="../images/jhu.jpg">

        <h3>JOHNS HOPKINS ANNUAL SOFTWARE DEVELOPMENT SEMINAR</h3>
        
        <div class="output">
        	<b><jsp:getProperty name="registrationInfo" property="name"/></b>
        </div>

        <div class="output">
        	You are registered as a <b><jsp:getProperty name="registrationInfo" property="employmentStatus"/></b>.
        </div>

        <div class="output">
        	Your e-mail confirmation will be sent to: <b><jsp:getProperty name="registrationInfo" property="email"/></b>.
        </div>

        <table>
        	<tr>
        		<th>Your Courses</th>
        		<th>Cost</th>
        		<th></th>
        	</tr>
       	<%
       		String[] courses = registrationInfo.getCourses();
       		// loop through the courses selected and build out the table
       		for (int i = 0; i < courses.length; i++) {
        %>
        	<tr>
        		<td><%= courses[i] %></td>
        		<td class="money">$<jsp:getProperty name="costInfo" property="employeeStatusCost"/>0</td>
        		<td><button class="remove" value=<%= courses[i] %>>Remove</button></td>       		
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
				<td class="money">$<jsp:getProperty name="costInfo" property="hotelCost"/>0</td>
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
				<td class="money">$<jsp:getProperty name="costInfo" property="parkingCost"/>0</td>
			</tr>
		<%
			}
		%>
			<tr>
				<td></td>
				<td class="money"><b>Total</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>$<jsp:getProperty name="costInfo" property="total"/>0</b></td>
			</tr>
		</table>
		
		<br>
		
        <input id="submit" type="submit" value="Back" onclick="goBack()">
    </div>

</body>
</html>