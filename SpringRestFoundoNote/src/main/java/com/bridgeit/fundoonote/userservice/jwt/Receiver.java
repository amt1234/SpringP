package com.bridgeit.fundoonote.userservice.jwt;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

import com.bridgeit.fundoonote.userservice.model.EmailInfo;

@Component
public class Receiver {

	
	public static void sendEmail(Session session, String toEmail, String subject, String body){
		try
	    {
	      MimeMessage msg = new MimeMessage(session);
	      //set message headers
	      msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
	      msg.addHeader("format", "flowed");
	      msg.addHeader("Content-Transfer-Encoding", "8bit");

	      msg.setFrom(new InternetAddress("poonamgadugale2017@gmail.com", "poonam gadugale"));

	      msg.setSubject(subject, "UTF-8");

	     msg.setText(body, "UTF-8");
	  
	      msg.setSentDate(new Date());

	      msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
	 
	      System.out.println("Message is ready");
	      Transport.send(msg);  

	      System.out.println("EMail Sent Successfully!!");
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }
	}
	
	public static void receiverMessageDemo(EmailInfo emailInfo)
	{
		
		final String fromEmail = "fundoo.note12@gmail.com"; // requires valid gmail id
		final String password = "fundoonote12"; // correct password for gmail id
		final String toEmail = emailInfo.getEmail();// can be any email id

		System.out.println("SSLEmail Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
		props.put("mail.smtp.socketFactory.port", "465"); // SSL Port
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // SSL Factory Class
		props.put("mail.smtp.auth", "true"); // Enabling SMTP Authentication
		props.put("mail.smtp.port", "465"); // SMTP Port

		Authenticator auth = new Authenticator() {
			// override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};

		Session session = Session.getDefaultInstance(props, auth);
		System.out.println("Session created");
		sendEmail(session, toEmail, "SSLEmail Testing Subject", emailInfo.getUrl()+emailInfo.getToken());
	}
	 /*public static void sendMail(final EmailInfo emailInfo) {  
         
	        MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {  
	              
	                public void prepare(MimeMessage mimeMessage) throws Exception {  
	                   mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(emailInfo.getEmail()));  
	                   mimeMessage.setSubject("Verification");  
	                   mimeMessage.setText(emailInfo.getUrl()+emailInfo.getToken());  
	                }  
	        };  
	        mailSender.send(messagePreparator);  
	    }  */
}
