package com.expensify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

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

