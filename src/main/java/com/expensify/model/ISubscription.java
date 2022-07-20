package com.expensify.model;

import java.util.List;

public interface ISubscription {
    void notifyUsers(String emailBody, String subject);

    void notifyBudgetLimitExceeds(int userId);

    List<ISubscription> getDailyExpenseSubscribedUser();
}
