<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="ISO-8859-1">
    <title>Development Seminar</title>
    <link href="hw3.css" rel="stylesheet" type="text/css">
    <script src="hw3.js"></script>
</head>

<body>

    <%
    	// cost information (employment status and additional fees) variables
    	double employeeCost = 850.00;
    	double studentCost = 1000.00;
    	double speakerCost = 0.00;
    	double otherCost = 1350.00;
    	double hotelCost = 185.00;
    	double parkingCost = 10.00;

    	// get the values submited in the form
    	String fullName = request.getParameter("fullName");
    	String email = request.getParameter("email");
    	String status = request.getParameter("employmentStatus");
    	String courses[] = request.getParameterValues("courses");
    	
    	// choose the correct cost based upon selected employment status
    	double cost = 0.00;
    	if (status.equals("JHU Employee")) {
    		cost = cost + employeeCost;
    	} else if (status.equals("JHU Student")) {
    		cost = cost + studentCost;
    	} else if (status.equals("Speaker")) {
    		// free for speaker
    	} else if (status.equals("Other")) {
    		cost = cost + otherCost;
    	}
    %>

    <div class="main">
        <img src="../images/jhu.jpg">

        <h3>JOHNS HOPKINS ANNUAL SOFTWARE DEVELOPMENT SEMINAR</h3>
        
        <div class="output">
        	<b><%= fullName%></b>
        </div>

        <div class="output">
        	You are registered as a <b><%= status%></b>.
        </div>

        <div class="output">
        	Your e-mail confirmation will be sent to: <b><%= email%></b>.
        </div>

        <table>
        	<tr>
        		<th>Your Courses</th>
        		<th></th>
        		<th>Cost</th>
        	</tr>
       	<%
       		// loop through the courses selected while simulatenously summing up the total and building out the table
       		double total = 0.00;
       		for (int i = 0; i < courses.length; i++) {
       			total = total + cost;
        %>
        	<tr>
        		<td><%= courses[i]%></td>
        		<td></td>
        		<td class="money">$<%= cost%>0</td>
        	</tr>
        <%
        	}
       	%>
        </table>

		<br>
		
		<table>
		<% 
			// if the user selected a hotel, add that as a table record and add it to the total
			if (request.getParameter("hotel") != null) {
				total = total + hotelCost;
		%>
			<tr>
				<td>Hotel Accommodation</td>
				<td class="money">$<%= hotelCost%>0</td>
			</tr>
		<%
			}
		%>
		<% 
			// if the user selected parking, add that as a table record and add it to the total
			if (request.getParameter("parking") != null) {
				total = total + 10;
		%>
			<tr>
				<td>Parking</td>
				<td class="money">$<%= parkingCost%>0</td>
			</tr>
		<%
			}
		%>
			<tr>
				<td></td>
				<td class="money"><b>Total</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>$<%= total%>0</b></td>
			</tr>
		</table>
		
		<br>
		
        <input id="submit" type="submit" value="Back" onclick="goBack()">
    </div>

</body>
</html>