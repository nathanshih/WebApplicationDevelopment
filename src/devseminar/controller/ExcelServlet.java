package devseminar.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import devseminar.service.RegistrationService;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * This servlet handles the creation of the Excel confirmation.
 * 
 * @author Nathan Shih
 * @since Nov 23, 2014
 */
@WebServlet("/devseminar/excel")
public class ExcelServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get the registrationInfo bean from the session
		HttpSession session = request.getSession();
		RegistrationService registrationService = (RegistrationService) session.getAttribute("registrationService");
			
		OutputStream out = null;
		try {
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=confirmation.xls");
			
			// build the Excel confirmation
			WritableWorkbook w = Workbook.createWorkbook(response.getOutputStream());
			WritableSheet s = w.createSheet("Confirmation", 0);
			int column1 = 0;
			int column2 = 4;
			int row = 0;
			s.addCell(new Label(column1, row, "JOHN HOPKINS ANNUAL SOFTWARE DEVELOPMENT SEMINAR"));
			row = row + 2;
			s.addCell(new Label(column1, row, registrationService.getRegistrationInfo().getName()));
			row = row + 2;
			s.addCell(new Label(column1, row, "You are registered for the following courses as a " + registrationService.getRegistrationInfo().getEmploymentStatus() + ":"));
			List<String> courses = registrationService.getRegistrationInfo().getCourses();
			row++;
            for (String course : courses) {
            	s.addCell(new Label(column1, row, course));
            	s.addCell(new Label(column2, row, "$" + Double.toString(registrationService.getCostInfo().getEmployeeStatusCost()) + "0"));
            	row++;
            }
            row++;
            if (!StringUtils.isEmpty(registrationService.getRegistrationInfo().getHotel())) {
            	s.addCell(new Label(column1, row, "Hotel Accommodation"));
            	s.addCell(new Label(column2, row, "$" + Double.toString(registrationService.getCostInfo().getHotelCost()) + "0"));
            	row++;
            }
            if (!StringUtils.isEmpty(registrationService.getRegistrationInfo().getParking())) {
            	s.addCell(new Label(column1, row, "Parking"));
            	s.addCell(new Label(column2, row, "$" + Double.toString(registrationService.getCostInfo().getParkingCost()) + "0"));
            	row++;
            }
            s.addCell(new Label(column1, row, "Total:"));
            s.addCell(new Label(column2, row, "$" + Double.toString(registrationService.getCostInfo().getTotal()) + "0"));
			w.write();
			w.close();
		} catch (Exception e) {
			throw new ServletException("Exception in Excel Sample Servlet", e);
		} finally {
			if (out != null)
				out.close();
		}
	}
}
