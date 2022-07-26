package com.expensify.model;

import com.expensify.factories.NotificationFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.util.List;
import java.util.ListIterator;

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduling.enabled")
class Scheduler {

    /*
    Executes the CRON job.
    Notation:
            ────────────────────────── second (0-59)
            │ ┌───────────── minute (0 - 59)
            │ │ ┌───────────── hour (0 - 23)
            │ │ │ ┌───────────── day of the month (1 - 31)
            │ │ │ │ ┌───────────── month (1 - 12) (or JAN-DEC)
            │ │ │ │ │ ┌───────────── day of the week (0 - 7)
            │ │ │ │ │ │          (or MON-SUN -- 0 or 7 is Sunday)
            │ │ │ │ │ │
            * * * * * *
    cron(               );
     */
    @Scheduled(cron = "0 30 19 * * *")
    public void sendDailyReminderToFillExpense() {
        List<INotification> notificationList = NotificationFactory.instance().createNotification().getDailyExpenseSubscribedUser();
        ListIterator<INotification> iter = notificationList.listIterator();
        while (iter.hasNext()) {
            INotification notification = (INotification) iter.next();
            notification.notifyUsers("Reminder to add expense", "Reminder");
        }
    }

    @Scheduled(cron = "0 35 19 * * *")
    public void sendSubscriptionExpiryNotification() {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        List<INotification> notificationList = NotificationFactory.instance().createNotification().getUsersWhoseSubscriptionisExpiring(String.valueOf(tomorrow));
        ListIterator<INotification> iter = notificationList.listIterator();
        while (iter.hasNext()) {
            Notification notification = (Notification) iter.next();
            notification.notifyUsers("Your "+ notification.getSubscriptionName()+ " Subscription expires Tommorrow", "Subscription Expiry");
        }
    }

}
