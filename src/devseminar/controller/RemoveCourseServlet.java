package devseminar.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;

import devseminar.service.RegistrationService;

/**
 * This servlet handles removing selected courses.
 *
 * @author Nathan Shih
 * @date Oct 10, 2014
 */
@WebServlet("/devseminar/remove")
public class RemoveCourseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public RemoveCourseServlet() {
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
		
		// get the course to be removed
		String course = request.getParameter("course");
		
		// get the registrationInfo bean from the session
		HttpSession session = request.getSession();
		RegistrationService registrationService = (RegistrationService) session.getAttribute("registrationService");
		
		// remove the course
		registrationService.removeCourse(course);
		session.setAttribute("registrationService", registrationService);
		
		// send data to results.jsp		
		String url = request.getContextPath() + "/devseminar/results.jsp";
		response.sendRedirect(url);
	}
}
