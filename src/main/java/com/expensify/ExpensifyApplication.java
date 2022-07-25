package com.expensify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ExpensifyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExpensifyApplication.class, args);
    }
}

