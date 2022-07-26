package com.expensify.model;

import com.expensify.persistenceLayer.IUserConfigurationDAOService;

public class UserConfiguration implements IUserConfiguration {

    private int expenseReminder;
    private int budgetExceedNotification;
    private int subscriptionNotification;

    public int getExpenseReminder() {
        return expenseReminder;
    }

    public void setExpenseReminder(int expenseReminder) {
        this.expenseReminder = expenseReminder;
    }

    public int getBudgetExceedNotification() {
        return budgetExceedNotification;
    }

    public void setBudgetExceedNotification(int budgetExceedNotification) {
        this.budgetExceedNotification = budgetExceedNotification;
    }

    public int getSubscriptionNotification() {
        return subscriptionNotification;
    }

    public void setSubscriptionNotification(int subscriptionNotification) {
        this.subscriptionNotification = subscriptionNotification;
    }

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
    public IUserConfigurationDAOService getUserConfigurationDAOService() {
        return userConfigurationDAOService;
    }

    public void setUserConfigurationDAOService(IUserConfiguration userConfiguration) {
        this.userConfigurationDAOService = userConfiguration.getUserConfigurationDAOService();
    }

    @Override
    public IUserConfiguration getUserConfiguration(int userId) {
        return userConfigurationDAOService.getUserConfigurations(userId);
    }

    @Override
    public boolean setUserConfiguration(int userId) {
        return userConfigurationDAOService.updateUserConfiguration(userId, expenseReminder, budgetExceedNotification, subscriptionNotification);
    }
}
