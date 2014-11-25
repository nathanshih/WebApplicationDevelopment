package devseminar.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import devseminar.service.RegistrationService;

/**
 * This servlet handles the creation of the PDF confirmation.
 *
 * @author Nathan Shih
 * @since Nov 22, 2014
 */
@WebServlet("/devseminar/pdf")
public class PdfServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get the registrationInfo bean from the session
		HttpSession session = request.getSession();
		RegistrationService registrationService = (RegistrationService) session.getAttribute("registrationService");
		
		try {
			Document document = new Document();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            
            // build content of the PDF
            document.open();
            // add image
            String relativeWebPath = "/images/jhu.jpg";
            String absoluteDiskPath = getServletContext().getRealPath(relativeWebPath);
            Image jhuImage = Image.getInstance(absoluteDiskPath);
            jhuImage.setAlignment(Element.ALIGN_CENTER);
            document.add(jhuImage);
            // add title
            Paragraph title = new Paragraph("JOHN HOPKINS ANNUAL SOFTWARE DEVELOPMENT SEMINAR");
            title.setIndentationLeft(40);
            title.setAlignment(Element.ALIGN_LEFT);
            title.setSpacingAfter(40);
            document.add(title);
            // add name
            Paragraph name = new Paragraph(registrationService.getRegistrationInfo().getName());
            name.setIndentationLeft(40);
            name.setAlignment(Element.ALIGN_LEFT);
            name.setSpacingAfter(10);
            document.add(name);
            // add role
            Paragraph role = new Paragraph("You are registered for the following courses as a " + registrationService.getRegistrationInfo().getEmploymentStatus() + ":");
            role.setIndentationLeft(40);
            role.setAlignment(Element.ALIGN_LEFT);
            role.setSpacingAfter(10);
            document.add(role);
            // add cost table
            PdfPTable costTable = new PdfPTable(2);           
            PdfPCell costItems = new PdfPCell(new Phrase("Courses"));
            PdfPCell costAmounts = new PdfPCell(new Phrase("Cost"));
            costItems.setBorder(Rectangle.BOTTOM);
            costAmounts.setBorder(Rectangle.BOTTOM);
            costTable.addCell(costItems);
            costTable.addCell(costAmounts);
            costTable.setHeaderRows(1);
            List<String> courses = registrationService.getRegistrationInfo().getCourses();
            for (String course : courses) {
            	PdfPCell courseName = new PdfPCell(new Phrase(course));
            	courseName.setBorder(Rectangle.NO_BORDER);
            	PdfPCell courseCost = new PdfPCell(new Phrase("$" + Double.toString(registrationService.getCostInfo().getEmployeeStatusCost()) + "0"));
            	courseCost.setBorder(Rectangle.NO_BORDER);
            	costTable.addCell(courseName);
            	costTable.addCell(courseCost);
            }
            PdfPCell emptyCell = new PdfPCell();
            emptyCell.setMinimumHeight(10);
            emptyCell.setBorder(Rectangle.NO_BORDER);
            costTable.addCell(emptyCell);
            costTable.addCell(emptyCell);
            if (!StringUtils.isEmpty(registrationService.getRegistrationInfo().getHotel())) {
            	PdfPCell hotel = new PdfPCell(new Phrase("Hotel Accommodation"));
            	hotel.setBorder(Rectangle.NO_BORDER);
            	PdfPCell hotelCost = new PdfPCell(new Phrase("$" + Double.toString(registrationService.getCostInfo().getHotelCost()) + "0"));
            	hotelCost.setBorder(Rectangle.NO_BORDER);
            	costTable.addCell(hotel);
            	costTable.addCell(hotelCost);
            }
            if (!StringUtils.isEmpty(registrationService.getRegistrationInfo().getParking())) {
            	PdfPCell parking = new PdfPCell(new Phrase("Parking"));
            	parking.setBorder(Rectangle.NO_BORDER);
            	PdfPCell parkingCost = new PdfPCell(new Phrase("$" + Double.toString(registrationService.getCostInfo().getParkingCost()) + "0"));
            	parkingCost.setBorder(Rectangle.NO_BORDER);
            	costTable.addCell(parking);
            	costTable.addCell(parkingCost);
            }
            PdfPCell total = new PdfPCell(new Phrase("Total:"));
            total.setBorder(Rectangle.TOP);
            PdfPCell totalCost = new PdfPCell(new Phrase("$" + Double.toString(registrationService.getCostInfo().getTotal()) + "0"));
            totalCost.setBorder(Rectangle.TOP);
            costTable.addCell(total);
            costTable.addCell(totalCost);
            document.add(costTable);
            document.close();
           
            // setting some response headers
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            response.setHeader("content-disposition", "attachment; filename=confirmation.pdf");
            // setting the content type
            response.setContentType("application/pdf");
            // the contentlength
            response.setContentLength(baos.size());
            // write ByteArrayOutputStream to the ServletOutputStream
            ServletOutputStream os = response.getOutputStream();
            baos.writeTo(os);
            os.flush();
            os.close();
		} catch (DocumentException de) {
			throw new IOException(de.getMessage());
		}
	}
}
