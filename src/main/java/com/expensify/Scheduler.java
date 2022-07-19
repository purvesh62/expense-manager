package com.expensify;

import com.expensify.model.Subscription;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.List;

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduling.enabled")
public class Scheduler {

    @Scheduled(cron = "* * 22 * * *")
    public void sendDailyReminderToFillExpense() {
        Subscription subscription = new Subscription();
        subscription.notifyUsers();
    }
}
