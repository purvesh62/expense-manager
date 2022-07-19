package com.expensify.persistenceLayer;

import com.expensify.database.Database;
import com.expensify.database.IDatabase;
import com.expensify.model.Expense;
import com.expensify.model.Subscription;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DailyExpenseReminderDOA {
    private final IDatabase mySqlDatabaseManager;

    public DailyExpenseReminderDOA() {
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
}
