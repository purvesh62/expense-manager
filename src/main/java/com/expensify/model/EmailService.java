package com.expensify.model;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailService implements IEmailService {
    private static IEmailService instance;
    private EmailService() {

    }
    public static IEmailService instance() {
        if(null == instance){
            instance = new EmailService();
        }
        return instance;
    }

    @Override
    public void sendEmail(EmailDetails emailDetails) {
        try{
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
            message.setContent(emailDetails.getEmailBody(), "text/html; charset=utf-8");
            message.setFrom(new InternetAddress("expensify2022@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailDetails.getReceipentAddress()));
            message.setSubject(emailDetails.getSubject());

            //sending message
            Transport.send(message);

        } catch(Exception e){
            e.printStackTrace();
            System.out.println("Error while sending mail");
        }
    }
}
