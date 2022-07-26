package com.expensify.model;

import com.expensify.persistenceLayer.INotficationDAOService;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class Notification implements INotification {
    private int notificationId;
    private int userId;
    private String email;
    private int notificationType;
    private int notificationStatus;
    private INotficationDAOService notficationDAOService;

    private String subscriptionName;

    public Notification(int notificationId, int userId, String email, int notificationType, int notificationStatus) {
        this.notificationId = notificationId;
        this.userId = userId;
        this.email = email;
        this.notificationType = notificationType;
        this.notificationStatus = notificationStatus;
    }

    public Notification(INotficationDAOService notficationDAOService) {
        this.notficationDAOService = notficationDAOService;
    }

    public Notification(String email, String subscriptionName){
        this.email = email;
        this.subscriptionName = subscriptionName;
    }

    public void notifyUsers(String emailBody, String subject) {
        SMTPEmailService.instance(
                email,
                emailBody,
                subject
        ).sendEmail();
    }

    @Override
    public List<INotification> getDailyExpenseSubscribedUser() {
        return notficationDAOService.dailyDailyExpenseSubscribedUsers();
    }

    @Override
    public List<INotification> getUsersWhoseSubscriptionisExpiring(String expiryDate) {
        try {
            return notficationDAOService.getUsersWhoseSubscriptionIsExpiring(expiryDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void notifyBudgetLimitExceeds(int userId) {
        INotification notification = notficationDAOService.getBudgetLimitExceedSubscribedUsers(userId);
        notification.notifyUsers("Your budget limit has been exceeded!!", "Budget Limit Exceeds");
    }

    public String getSubscriptionName() {
        return subscriptionName;
    }

    public void setSubscriptionName(String subscriptionName) {
        this.subscriptionName = subscriptionName;
    }
}
