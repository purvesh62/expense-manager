package com.expensify;

import com.expensify.model.ISubscription;
import com.expensify.model.factories.SubscriptionFactory;
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
        List<ISubscription> subscriptionList = new SubscriptionFactory().createSubscription().getDailyExpenseSubscribedUser();
        ListIterator<ISubscription> iter = subscriptionList.listIterator();
        if (iter.hasNext()) {
            ISubscription subscription = (ISubscription) iter.next();
            subscription.notifyUsers("Reminder to add expense","Reminder");
        }
    }
}
