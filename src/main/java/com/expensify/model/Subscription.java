package com.expensify.model;

import com.expensify.EmailService;
import com.expensify.persistenceLayer.DailyExpenseReminderDOA;

import java.util.List;
import java.util.ListIterator;

public class Subscription {

    private int subscriptionId;

    private int userId;

    private String email;
    private int subscriptionType;

    private int subscriptionStatus;

    private DailyExpenseReminderDOA expenseReminderDOA;

    public Subscription() {
        this.subscriptionId = 0;
        this.userId = 0;
        this.subscriptionType = 0;
        this.subscriptionStatus = 0;
        expenseReminderDOA = new DailyExpenseReminderDOA();
    }

    public int getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(int subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public int getSubscriptionStatus() {
        return subscriptionStatus;
    }

    public void setSubscriptionStatus(int subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
    }

    public void notifyUsers() {
        List<Subscription> subscriptionList = this.getDailyExpenseSubscribedUser();
        ListIterator<Subscription> iter = subscriptionList.listIterator();
        if (iter.hasNext()) {
            Subscription subscription = (Subscription) iter.next();
            EmailDetails emailDetails = new EmailDetails(subscription.getEmail(), "Have you filled it yet?", "Daily Expense Reminder");

            EmailService emailService = new EmailService();
            emailService.sendMail(emailDetails);
        }


    }

    public List<Subscription> getDailyExpenseSubscribedUser() {
        return expenseReminderDOA.dailyDailyExpenseSubscribedUsers();
    }
}
