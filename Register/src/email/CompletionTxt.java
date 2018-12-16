package email;
 
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
/**
 * @author Crunchify.com
 * 
 */
 
public class CompletionTxt {
 
	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;
	
	static String doc_key = null;
	static String emailConnectStmp = null;
	static String emailConnectURL = null;
	static String emailConnectionPWD = null;
	static String emailConnectionRecipient = null;

	public void config() {
		Properties prop = new Properties();
		try {
		prop.load(new FileInputStream("C:/Users/Frazee/eclipse-workspace/Bookstore/src/resources/properties"));
		emailConnectStmp = prop.getProperty("emailConnectionStmp");
		emailConnectURL = prop.getProperty("emailConnectionURL");
		emailConnectionPWD = prop.getProperty("emailConnectionPWD");
		emailConnectionRecipient = prop.getProperty("emailConnectionRecipient");
		} catch (IOException ex) {
		    ex.printStackTrace();
		}
	}
 
	public void generateAndSendEmail(String txtBal) throws AddressException, MessagingException {
		
		System.out.println("\n\n ===> Your Java Program has just sent an Email successfully. Check your email..");
 
		// Step1
		System.out.println("\n 1st ===> setup Mail Server Properties..");
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		System.out.println("Mail Server Properties have been setup successfully..");
 
		// Step2
		System.out.println("\n\n 2nd ===> get Mail Session..");
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailConnectionRecipient));
		//generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(""));
		generateMailMessage.setSubject("Transaction processing completed");
		String emailBody = "Transaction processing completed, current balance = " + txtBal;
		generateMailMessage.setContent(emailBody, "text/html");
		System.out.println("Mail Session has been created successfully..");
 
		// Step3
		System.out.println("\n\n 3rd ===> Get Session and Send mail");
		Transport transport = getMailSession.getTransport("smtp");
 
		// Enter your correct gmail UserID and Password
		// if you have 2FA enabled then provide App Specific Password
		transport.connect(emailConnectStmp, emailConnectURL, emailConnectionPWD);
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		transport.close();
	}
}