package com.expensify.persistenceLayer;

import com.expensify.model.IUserConfiguration;

public interface IUserConfigurationDAOService {
    IUserConfiguration getUserConfigurations(int userId);

    boolean updateUserConfiguration(int userId, int expenseNotificationStatus, int budgetNotificationStatus, int subscriptionNotificationStatus);
}
