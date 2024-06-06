package vn.edu.hcmuaf.FocusAppProject.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import vn.edu.hcmuaf.FocusAppProject.FocusAppProjectApplication;
import vn.edu.hcmuaf.FocusAppProject.service.CourseScheduleService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Properties;

@Component
public class EmailUtil {
    @Value("${email.from}")
    private String from;
    @Value("${email.password}")
    private String password;
    @Autowired
    private EmailTemplateUtil emailTemplateUtil ;
//    private String from = "wealthuring@gmail.com";
//    private String password = "oaunszrvljseonid";
//
//    private EmailTemplateUtil emailTemplateUtil = new EmailTemplateUtil();

    @Async
    public void sendMail(String to, String subject, String templateName, Map<String, String> values) throws Exception {
        String content = emailTemplateUtil.getEmailTemplate(templateName, values);
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "587");

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

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(FocusAppProjectApplication.class, args);
        CourseScheduleService courseScheduleService = context.getBean(CourseScheduleService.class);
        try {
            courseScheduleService.sendCourseNotifications(4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



