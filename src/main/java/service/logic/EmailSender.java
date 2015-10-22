package service.logic;


import org.springframework.mail.javamail.JavaMailSender;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EmailSender implements Sender
{
	private JavaMailSender mailSender;

	public void setMailSender(JavaMailSender mailSender)
	{
		this.mailSender = mailSender;
	}

	public void send(String destination, String subject, String text)
	{

		 MimeMessage message = mailSender.createMimeMessage();


		try
		{
			message.setFrom(new InternetAddress("goszak.monitoring.service@gmail.com"));
			message.addRecipient(Message.RecipientType.TO,	new InternetAddress(destination));/////////////
			message.setSubject(subject);
			message.setText(text, "utf-8", "html");


			mailSender.send(message);
		}
		catch (MessagingException e)
		{
			e.printStackTrace();
		}

	}


}
