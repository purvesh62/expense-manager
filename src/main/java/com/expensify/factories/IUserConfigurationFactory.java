package com.expensify.factories;

import com.expensify.database.IDatabase;
import com.expensify.model.IUserConfiguration;
import com.expensify.persistenceLayer.IUserConfigurationDAOService;

public interface IUserConfigurationFactory {

    IUserConfiguration createUserConfiguration();

    IUserConfigurationDAOService createUserConfigurationDAOService(IDatabase database);

    IUserConfiguration createUserConfiguration(boolean expenseReminder, boolean budgetExceedNotification, boolean subscriptionNotification);
}
