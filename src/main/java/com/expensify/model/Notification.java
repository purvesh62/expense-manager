package com.expensify.model;

import com.expensify.persistenceLayer.INotficationDAOService;

import java.util.List;

public class Notification implements INotification {
    private int notificationId;
    private int userId;
    private String email;
    private int notificationType;
    private int notificationStatus;
    private INotficationDAOService notficationDAOService;

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
    public void notifyBudgetLimitExceeds(int userId) {
        INotification notification = notficationDAOService.getBudgetLimitExceedSubscribedUsers(userId);
        notification.notifyUsers("Your budget limit has been exceeded!!", "Budget Limit Exceeds");
    }
}
