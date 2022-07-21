package com.expensify.persistenceLayer;

import com.expensify.model.ISubscription;
import com.expensify.model.Subscription;

import java.util.List;

public interface ISubscriptionDAOService {
    List<ISubscription> dailyDailyExpenseSubscribedUsers();
    ISubscription getBudgetLimitExceedSubscribedUsers(int userId);
}
