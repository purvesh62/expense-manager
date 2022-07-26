package com.expensify.model;

import com.expensify.persistenceLayer.IUserConfigurationDAOService;

public class UserConfiguration implements IUserConfiguration {

    private boolean expenseReminder;
    private boolean budgetExceedNotification;
    private boolean subscriptionNotification;

    public boolean getExpenseReminder() {
        return expenseReminder;
    }

    public void setExpenseReminder(boolean expenseReminder) {
        this.expenseReminder = expenseReminder;
    }

    public boolean getBudgetExceedNotification() {
        return budgetExceedNotification;
    }

    public void setBudgetExceedNotification(boolean budgetExceedNotification) {
        this.budgetExceedNotification = budgetExceedNotification;
    }

    public boolean getSubscriptionNotification() {
        return subscriptionNotification;
    }

    public void setSubscriptionNotification(boolean subscriptionNotification) {
        this.subscriptionNotification = subscriptionNotification;
    }

    private IUserConfigurationDAOService userConfigurationDAOService;

    public UserConfiguration() {

    }

    public UserConfiguration(IUserConfigurationDAOService database) {
        userConfigurationDAOService = database;
    }

    public UserConfiguration(boolean expenseReminder, boolean budgetExceedNotification, boolean subscriptionNotification) {
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
        int expenseReminder = 0;
        int budgetExceedNotification = 0;
        int subscriptionNotification = 0;
        if (this.expenseReminder) {
            expenseReminder = 1;
        }
        if (this.budgetExceedNotification) {
            budgetExceedNotification = 1;
        }
        if (this.subscriptionNotification) {
            subscriptionNotification = 1;
        }
        return userConfigurationDAOService.updateUserConfiguration(userId, expenseReminder, budgetExceedNotification, subscriptionNotification);
    }
}
