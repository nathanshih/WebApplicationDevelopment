package devseminar.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;

import devseminar.service.RegistrationService;
import devseminar.service.RegistrationServiceImpl;

/**
 * This servlet handles all registration requests and responses.
 *
 * @author Nathan Shih
 * @date Sep 24, 2014
 */
@WebServlet("/devseminar/registration")
public class RegistrationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    public RegistrationServlet() {
        super();
    }

	public void destroy() {
		// do nothing
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// method not used so return actionable response to caller
		response.setStatus(HttpStatus.SC_METHOD_NOT_ALLOWED);
		response.setHeader(HttpHeaders.ALLOW, HttpPost.METHOD_NAME);
		response.getOutputStream().print("Use " + HttpPost.METHOD_NAME + " method instead.");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// thread safe declaration of the RegistrationService object
		RegistrationService registrationService = new RegistrationServiceImpl();
		
		// get request parameters
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		List<String> courses = Arrays.asList(request.getParameterValues("courses"));
		String employmentStatus = request.getParameter("employmentStatus");
		String hotel = request.getParameter("hotel");
		String parking = request.getParameter("parking");
		
		// based on UI requirements, both course and employment status are required
		// first check that a course is selected
		if (courses == null || courses.isEmpty()) {
			response.setStatus(HttpStatus.SC_BAD_REQUEST);
			response.getOutputStream().print("At least 1 course must be selected.");

		// next check that an employment status is selected
		} else if (StringUtils.isEmpty(employmentStatus)) {
			response.setStatus(HttpStatus.SC_BAD_REQUEST);
			response.getOutputStream().print("An employment status needs to be selected.");
		
		// everything looks good, continue processing request
		} else {
			// store information
			registrationService.setRegistrationInfo(name, email, courses, employmentStatus, hotel, parking);
								
			// send the registration information
			HttpSession session = request.getSession();	
			session.setAttribute("registrationService", registrationService);
			
			// send data to results.jsp		
			String url = request.getContextPath() + "/devseminar/results.jsp";
			response.sendRedirect(url);
		}
	}
}
