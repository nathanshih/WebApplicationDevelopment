package devseminar.controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import devseminar.mailutils.MailUtilGmail;
import devseminar.service.RegistrationService;

/**
 * This servlet handles sending of the confirmation email.
 *
 * @author Nathan Shih
 * @date Oct 19, 2014
 */
@WebServlet("/devseminar/confirm")
public class CourseConfirmationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public CourseConfirmationServlet() {
		super();
	}
	
	public void destroy() {
		// do nothing
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get the registrationInfo bean from the session
		HttpSession session = request.getSession();
		RegistrationService registrationService = (RegistrationService) session.getAttribute("registrationService");
		
		// set the registrationService in the session variable
		session.setAttribute("registrationService", registrationService);
		
		// send the email
		try {
			MailUtilGmail.sendMail(registrationService.getRegistrationInfo().getEmail(), 
								   "DO-NOT-REPLY@jhu.edu", 
								   "JHU Software Development Seminar Registration Confirmation", 
								   "Your registration is confirmed for the JHU Software Development Seminar.", 
								   false);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		// send data to confirmation.jsp
		String url = "/devseminar/confirmation.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
