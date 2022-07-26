package com.expensify.factories;

import com.expensify.database.IDatabase;
import com.expensify.database.MySqlDatabase;
import com.expensify.model.IUserConfiguration;
import com.expensify.model.UserConfiguration;
import com.expensify.persistenceLayer.IUserConfigurationDAOService;
import com.expensify.persistenceLayer.UserConfigurationDAOService;

public class UserConfigurationFactory implements IUserConfigurationFactory {

    private static IUserConfigurationFactory userConfigurationFactory = null;

    private UserConfigurationFactory(){

    }

    public static IUserConfigurationFactory instance(){
        if(userConfigurationFactory == null){
            return new UserConfigurationFactory();
        }
        return userConfigurationFactory;
    }

    @Override
    public IUserConfiguration createUserConfiguration() {
        IDatabase database = MySqlDatabase.instance();
        return new UserConfiguration(createUserConfigurationDAOService(database));
    }

    @Override
    public IUserConfigurationDAOService createUserConfigurationDAOService(IDatabase database) {
        return new UserConfigurationDAOService(database);
    }

    @Override
    public IUserConfiguration createUserConfiguration(boolean expenseReminder, boolean budgetExceedNotification, boolean subscriptionNotification) {
        return new UserConfiguration(expenseReminder, budgetExceedNotification, subscriptionNotification);
    }
}
