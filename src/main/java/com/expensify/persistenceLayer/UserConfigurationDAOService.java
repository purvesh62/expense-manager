package com.expensify.persistenceLayer;

import com.expensify.database.IDatabase;
import com.expensify.factories.UserConfigurationFactory;
import com.expensify.model.IUserConfiguration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserConfigurationDAOService implements IUserConfigurationDAOService {
    private final IDatabase database;

    public UserConfigurationDAOService(IDatabase database) {
        this.database = database;
    }

    @Override
    public IUserConfiguration getUserConfigurations(int userId) {
        List<Object> parameterList = new ArrayList<>();
        try {
            parameterList.add(userId);

            try (ResultSet resultSet = this.database.executeProcedure("CALL get_user_expenses(?)", parameterList)) {
                if (resultSet != null) {
                    while (resultSet.next()){
                        IUserConfiguration userConfiguration = UserConfigurationFactory.instance().createUserConfiguration(
                                resultSet.getInt("expense_notification_status") == 1,
                                resultSet.getInt("budget_notification_status") == 1,
                                resultSet.getInt("subscription_notification_status") == 1
                        );
                        return userConfiguration;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                this.database.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean updateUserConfiguration(int userId, int expenseNotificationStatus, int budgetNotificationStatus, int subscriptionNotificationStatus) {
        List<Object> parameterList = new ArrayList<>();
        try {
            parameterList.add(userId);
            parameterList.add(expenseNotificationStatus);
            parameterList.add(budgetNotificationStatus);
            parameterList.add(subscriptionNotificationStatus);
            try (ResultSet resultSet = this.database.executeProcedure("CALL update_user_configuration(?, ?, ?, ?)", parameterList)) {
                if (resultSet != null) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                this.database.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
