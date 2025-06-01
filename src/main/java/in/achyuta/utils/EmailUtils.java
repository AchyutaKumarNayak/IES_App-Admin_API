package in.achyuta.utils;

<<<<<<< HEAD
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public boolean sendMail(String to,String subject,String body) {
		
		boolean isSent=false;
		try {
			
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper mimeMsgHelper=new MimeMessageHelper(mimeMessage);
			mimeMsgHelper.setTo(to);
			mimeMsgHelper.setSubject(subject);
			mimeMsgHelper.setText(body,true);
			mailSender.send(mimeMessage);
			isSent=true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return isSent;
	}
=======
public class EmailUtils {
>>>>>>> develop

}
