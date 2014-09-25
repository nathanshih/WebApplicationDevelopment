package hw4.controller;

import hw4.service.RegistrationService;
import hw4.service.RegistrationServiceImpl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/hw4/registration")
public class RegistrationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	private RegistrationService registrationService;
	private Gson gson;
	
    public RegistrationServlet() {
        super();

        registrationService = new RegistrationServiceImpl();
        gson = new Gson();
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getOutputStream().print(gson.toJson(registrationService.getRegistrationInfo()));		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get request parameters
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String[] courses = request.getParameterValues("courses");
		String employmentStatus = request.getParameter("employmentStatus");
		String hotel = request.getParameter("hotel");
		String parking = request.getParameter("parking");
		
		// store information
		registrationService.setRegistrationInfo(name, email, courses, employmentStatus, hotel, parking);
		
		// send the registration information 
		request.setAttribute("registrationInfo", registrationService.getRegistrationInfo());
		
		// forward the response		
		String url = "/hw4/results.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
