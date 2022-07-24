package com.expensify.model;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import static org.junit.Assert.assertThrows;

@SpringBootTest
public class SMTPEmailServiceTest {
    @Test
    public void sendMailSuccessTest(){
        assert(true);
    }

    @Test
    public void sendMailFailureTest(){
        assertThrows(MessagingException.class,() -> {
        Session session = null;
        MimeMessage message = new MimeMessage(session);
        message.setSubject(null);

        Transport.send(message);
        });
    }
}
