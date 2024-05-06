package vn.edu.hcmuaf.FocusAppProject.Util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

@Component
public class EmailUtil {
    //    String pass="auogzmiqdjuyxbji";
    @Value("${email.from}")
    private String from;
    @Value("${email.password}")
    private String password;

    @Async
    public void sendMail(String to, String content, String subject) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2"); //TLS 587 SSL 465
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        //create Authentication
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };

        Session session = Session.getInstance(properties, auth);

        //Create Message
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.addHeader("Content-tyoe", "text/HTML; charset=UTF-8");
            Address address = new InternetAddress(from, "Focus App");
            msg.setFrom(address);
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            msg.setSubject(subject);
            msg.setContent(content, "text/HTML; charset=UTF-8");

            //send mail
            Transport.send(msg);
            System.out.println("EmailUtil sent successfully");
        } catch (MessagingException e) {
            System.out.println("EmailUtil sending failed");
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            System.out.println("EmailUtil sending failed");
            throw new RuntimeException(e);
        }
    }

}



