package devseminar.mailutils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailUtilWithAuth {

	public  void sendMail(String to, String from, String subject, String emailMessage) throws MessagingException {
		Message message = new MimeMessage(getSession());

		message.addRecipient(RecipientType.TO, new InternetAddress(to));
		message.addFrom(new InternetAddress[] { new InternetAddress(from) });

		message.setSubject(subject);
		message.setContent(emailMessage, "text/plain");

		Transport.send(message);

	}

	private Session getSession() {
		Authenticator authenticator = new Authenticator();

		Properties properties = new Properties();
		properties.setProperty("mail.smtp.submitter", authenticator.getPasswordAuthentication().getUserName());
		properties.setProperty("mail.smtp.auth", "true");

		properties.setProperty("mail.smtp.host", "smtp.johnshopkins.edu");
		properties.setProperty("mail.smtp.port", "25");

		return Session.getInstance(properties, authenticator);
	}

	private class Authenticator extends javax.mail.Authenticator {
		private PasswordAuthentication authentication;

		public Authenticator() {
			String username = "";
			String password = "";
			authentication = new PasswordAuthentication(username, password);
		}

		protected PasswordAuthentication getPasswordAuthentication() {
			return authentication;
		}
	}
}
