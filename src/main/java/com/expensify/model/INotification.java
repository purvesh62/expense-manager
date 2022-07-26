package com.expensify.model;

import java.time.LocalDate;
import java.util.List;

public interface INotification {
    void notifyUsers(String emailBody, String subject);

    void notifyBudgetLimitExceeds(int userId);

    List<INotification> getDailyExpenseSubscribedUser();

    List<INotification> getUsersWhoseSubscriptionisExpiring(String expiryDate);
}
