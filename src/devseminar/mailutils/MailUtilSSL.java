package devseminar.mailutils;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class MailUtilSSL {

	private static final String SMTP_HOST_NAME = "smtp.johnshopkins.edu";
	private static final String SMTP_AUTH_USER = "";
	private static final String SMTP_AUTH_PWD  = "";

	public void postMail( String recipients, String subject, String message , String from) throws MessagingException {
		boolean debug = false;

		//Set the host smtp address
		Properties props = new Properties();
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");

		props.put("mail.smtp.port", "25");
		/*     
		props.put("mail.smtp.socketFactory.port", "587");
     	props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
     	props.put("mail.smtp.socketFactory.fallback", "false");
		*/
		Authenticator auth = new SMTPAuthenticator();
		Session session = Session.getDefaultInstance(props, auth);

		session.setDebug(debug);

		// create a message
		Message msg = new MimeMessage(session);

		// set the from and to address
		InternetAddress addressFrom = new InternetAddress(from);
		msg.setFrom(addressFrom);

		InternetAddress toAddress= new InternetAddress(recipients);
		msg.setRecipient(Message.RecipientType.TO, toAddress);


		// Setting the Subject and Content Type
		msg.setSubject(subject);
		msg.setContent(message, "text/plain");
		Transport.send(msg);
	}


	/**
	 * SimpleAuthenticator is used to do simple authentication
	 * when the SMTP server requires it.
	 */
	private class SMTPAuthenticator extends javax.mail.Authenticator {

		public PasswordAuthentication getPasswordAuthentication() {
			String username = SMTP_AUTH_USER;
			String password = SMTP_AUTH_PWD;
			return new PasswordAuthentication(username, password);
		}
	}
}
