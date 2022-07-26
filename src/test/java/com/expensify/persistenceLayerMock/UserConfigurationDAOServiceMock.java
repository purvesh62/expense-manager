package com.expensify.persistenceLayerMock;

import com.expensify.factories.UserConfigurationFactory;
import com.expensify.model.IUserConfiguration;
import com.expensify.persistenceLayer.IUserConfigurationDAOService;

public class UserConfigurationDAOServiceMock implements IUserConfigurationDAOService {
    @Override
    public IUserConfiguration getUserConfigurations(int userId) {
        if (userId == 1) {
            IUserConfiguration userConfiguration = UserConfigurationFactory.instance().createUserConfiguration(true, true, true);
            return userConfiguration;
        }
        return null;
    }

    @Override
    public boolean updateUserConfiguration(int userId, int expenseNotificationStatus, int budgetNotificationStatus, int subscriptionNotificationStatus) {
       if(userId == 0){
           return false;
       }
       return true;
    }
}
