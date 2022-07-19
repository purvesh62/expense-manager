package com.expensify.persistenceLayer;

import com.expensify.database.Database;
import com.expensify.database.IDatabase;
import com.expensify.model.Subscription;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionDAO {
    private final IDatabase mySqlDatabaseManager;

    public SubscriptionDAO() {
        this.mySqlDatabaseManager = Database.instance();
    }

    public List<Subscription> dailyDailyExpenseSubscribedUsers() {
        List<Subscription> userSubscribedList = new ArrayList<>();
        try {
            List<Object> parameterList = new ArrayList<>();

            try (ResultSet resultSet = mySqlDatabaseManager.executeProcedure("CALL get_daily_expense_notification_subscribed_users()", parameterList)) {
                if (resultSet != null) {
                    while (resultSet.next()) {
                        Subscription subscription = new Subscription();
                        subscription.setSubscriptionId(resultSet.getInt("id"));
                        subscription.setUserId(resultSet.getInt("user_id"));
                        subscription.setEmail(resultSet.getString("email"));
                        subscription.setSubscriptionType(resultSet.getInt("s_id"));
                        subscription.setSubscriptionStatus(resultSet.getInt("status"));
                        userSubscribedList.add(subscription);
                    }
                }
            }
            return userSubscribedList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                mySqlDatabaseManager.closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return userSubscribedList;
    }

    public Subscription getBudgetLimitExceedSubscribedUsers(int userId) {
        Subscription subscription = new Subscription();
        try {
            List<Object> parameterList = new ArrayList<>();
            parameterList.add(userId);

            try (ResultSet resultSet = mySqlDatabaseManager.executeProcedure("CALL get_budget_limit_exceed_notification(?)", parameterList)) {
                if (resultSet != null) {
                    while (resultSet.next()) {

                        subscription.setSubscriptionId(resultSet.getInt("id"));
                        subscription.setUserId(resultSet.getInt("user_id"));
                        subscription.setEmail(resultSet.getString("email"));
                        subscription.setSubscriptionType(resultSet.getInt("s_id"));
                        subscription.setSubscriptionStatus(resultSet.getInt("status"));
                    }
                }
            }
            return subscription;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                mySqlDatabaseManager.closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return subscription;
    }
}
