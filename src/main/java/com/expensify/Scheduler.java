package com.expensify;

import com.expensify.model.INotification;
import com.expensify.model.factories.NotificationFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.ListIterator;

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduling.enabled")
public class Scheduler {

    @Scheduled(cron = "* 10 * 10 * *")
    public void sendDailyReminderToFillExpense() {
        List<INotification> notificationList = NotificationFactory.instance().createNotification().getDailyExpenseSubscribedUser();
        ListIterator<INotification> iter = notificationList.listIterator();
        while (iter.hasNext()) {
            INotification notification = (INotification) iter.next();
            notification.notifyUsers("Reminder to add expense", "Reminder");
        }
    }
}
