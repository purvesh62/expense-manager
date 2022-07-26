package com.expensify.model;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SMTPEmailService implements IEmailService {
    private static IEmailService instance;

    private String receipentAddress;

    private String emailBody;

    private String subject;

    private SMTPEmailService() {

    }

    private SMTPEmailService(String receipentAddress, String emailBody, String subject) {
        this.receipentAddress = receipentAddress;
        this.emailBody = emailBody;
        this.subject = subject;
    }

    public static IEmailService instance(String receipentAddress, String emailBody, String subject) {
        if(null == instance){
            instance = new SMTPEmailService(receipentAddress,emailBody,subject);
        }
        return instance;
    }

    @Override
    public void sendEmail() {
        try{
            String userName = System.getenv("SMTP_USERNAME");
            String password = System.getenv("SMTP_PASSWORD");
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("expensify2022@gmail.com", "xxyidjhdiekxglrq");
                }
            });

            //create message using session
            MimeMessage message = new MimeMessage(session);
            message.setContent(emailBody, "text/html; charset=utf-8");
            message.setFrom(new InternetAddress("expensify2022@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receipentAddress));
            message.setSubject(subject);

            //sending message
            Transport.send(message);

        } catch(MessagingException e){
            e.printStackTrace();
            System.out.println("Error while sending mail");
        }
    }
}
