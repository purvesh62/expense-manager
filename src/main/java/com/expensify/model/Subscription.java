package com.expensify.model;

import com.expensify.persistenceLayer.ISubscriptionDAOService;
import java.util.List;

public class Subscription implements ISubscription {
    private int subscriptionId;
    private int userId;
    private String email;
    private int subscriptionType;
    private int subscriptionStatus;
    private ISubscriptionDAOService subscriptionDAOService;

    public Subscription(int subscriptionId, int userId, String email, int subscriptionType, int subscriptionStatus) {
        this.subscriptionId = subscriptionId;
        this.userId = userId;
        this.email = email;
        this.subscriptionType = subscriptionType;
        this.subscriptionStatus = subscriptionStatus;
    }

    public Subscription(ISubscriptionDAOService subscriptionDAOService) {
        this.subscriptionDAOService = subscriptionDAOService;
    }

    public void notifyUsers(String emailBody, String subject) {
        SMTPEmailService.instance(
                email,
                emailBody,
                subject

        ).sendEmail();
    }

    @Override
    public List<ISubscription> getDailyExpenseSubscribedUser() {
        return subscriptionDAOService.dailyDailyExpenseSubscribedUsers();
    }

    @Override
    public void notifyBudgetLimitExceeds(int userId) {
        ISubscription subscription = subscriptionDAOService.getBudgetLimitExceedSubscribedUsers(userId);
        subscription.notifyUsers("Your budget limit has been exceeded!!","Budget Limit Exceeds");
//        SMTPEmailService.instance(
//                subscription.email,
//                "Your budget limit has been exceeded!!",
//                "Budget Limit Exceeds"
//
//        ).sendEmail();
    }
}
