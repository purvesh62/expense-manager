package com.expensify;

import com.expensify.model.EmailDetails;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@SpringBootApplication
@EnableScheduling
public class ExpensifyApplication {

    public static void main(String[] args) {

//		EmailService emailService = new EmailService();
        SpringApplication.run(ExpensifyApplication.class, args);
//		EmailDetails emailDetails = new EmailDetails();
//		emailDetails.setEmailBody("texttttt");
//		emailDetails.setReceipentAddress("bansarishah1997@gmail.com");
//		emailDetails.setSubject("mail from expensify");
//		emailService.sendMail(emailDetails);
    }
}

