package com.expensify.model;

import com.expensify.persistenceLayer.IUserConfigurationDAOService;

public class UserConfiguration implements IUserConfiguration {

    private int expenseReminder;
    private int budgetExceedNotification;
    private int subscriptionNotification;

    private IUserConfigurationDAOService userConfigurationDAOService;

    public UserConfiguration(IUserConfigurationDAOService database) {
        userConfigurationDAOService = database;
    }

    public UserConfiguration(int expenseReminder, int budgetExceedNotification, int subscriptionNotification) {
        this.expenseReminder = expenseReminder;
        this.budgetExceedNotification = budgetExceedNotification;
        this.subscriptionNotification = subscriptionNotification;
    }

    @Override
    public IUserConfiguration getUserConfiguration(int userId) {
        return userConfigurationDAOService.getUserConfigurations(userId);
    }
}
