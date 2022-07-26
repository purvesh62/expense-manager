package com.expensify.factories;

import com.expensify.database.IDatabase;
import com.expensify.database.MySqlDatabase;
import com.expensify.model.IUserConfiguration;
import com.expensify.model.UserConfiguration;
import com.expensify.persistenceLayer.IUserConfigurationDAOService;
import com.expensify.persistenceLayer.UserConfigurationDAOService;

public class UserConfigurationFactory implements IUserConfigurationFactory {

    @Override
    public IUserConfiguration createUserConfiguration() {
        IDatabase database = MySqlDatabase.instance();
        return new UserConfiguration(createUserConfigurationDAOService(database));
    }

    @Override
    public IUserConfigurationDAOService createUserConfigurationDAOService(IDatabase database) {
        return new UserConfigurationDAOService(database);
    }
}
