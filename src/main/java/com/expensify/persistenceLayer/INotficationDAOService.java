package com.expensify.persistenceLayer;

import com.expensify.model.INotification;

import java.util.List;

public interface INotficationDAOService {
    List<INotification> dailyDailyExpenseSubscribedUsers();

    INotification getBudgetLimitExceedSubscribedUsers(int userId);
}
