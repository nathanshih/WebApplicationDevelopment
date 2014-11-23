package devseminar.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
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
            
            document.open();
            document.add(new Paragraph(Double.toString(registrationService.getCostInfo().getTotal())));
            document.add(new Paragraph("foo bar baz"));
            document.close();
           
            // setting some response headers
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            response.setHeader("content-disposition", "inline; filename=confirmation.pdf");
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
