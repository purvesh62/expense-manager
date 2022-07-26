package com.expensify.factories;

import com.expensify.database.IDatabase;
import com.expensify.model.IBudget;
import com.expensify.model.IUserConfiguration;
import com.expensify.persistenceLayer.IBudgetDAOService;
import com.expensify.persistenceLayer.IUserConfigurationDAOService;

public interface IUserConfigurationFactory {

    IUserConfiguration createUserConfiguration();
    IUserConfigurationDAOService createUserConfigurationDAOService(IDatabase database);

    IUserConfiguration createUserConfiguration(int expenseReminder, int budgetExceedNotification, int subscriptionNotification);
}
