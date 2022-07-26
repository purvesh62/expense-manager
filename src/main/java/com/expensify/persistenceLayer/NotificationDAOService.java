package com.expensify.persistenceLayer;

import com.expensify.database.IDatabase;
import com.expensify.model.INotification;
import com.expensify.factories.NotificationFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAOService implements INotficationDAOService {
    private final IDatabase database;

    public NotificationDAOService(IDatabase database) {
        this.database = database;
    }

    @Override
    public List<INotification> dailyDailyExpenseSubscribedUsers() {
        List<INotification> userSubscribedList = new ArrayList<>();
        try {
            List<Object> parameterList = new ArrayList<>();

            try (ResultSet resultSet = database.executeProcedure("CALL get_daily_expense_notification_subscribed_users()", parameterList)) {
                if (resultSet != null) {
                    while (resultSet.next()) {
                        INotification notification = NotificationFactory.instance().createNotification(
                                resultSet.getInt("id"),
                                resultSet.getInt("user_id"),
                                resultSet.getString("email"),
                                resultSet.getInt("notification_id"),
                                resultSet.getInt("status")
                        );
                        userSubscribedList.add(notification);
                    }
                }
            }
            return userSubscribedList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                database.closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return userSubscribedList;
    }

    @Override
    public INotification getBudgetLimitExceedSubscribedUsers(int userId) {
        INotification notification = NotificationFactory.instance().createNotification();
        try {
            List<Object> parameterList = new ArrayList<>();
            parameterList.add(userId);

            try (ResultSet resultSet = database.executeProcedure("CALL get_budget_limit_exceed_notification(?)", parameterList)) {
                while (resultSet.next()) {
                    notification = NotificationFactory.instance().createNotification(
                            resultSet.getInt("id"),
                            resultSet.getInt("user_id"),
                            resultSet.getString("email"),
                            resultSet.getInt("notification_id"),
                            resultSet.getInt("status")
                    );
                }
            }
            return notification;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                database.closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return notification;
    }
}
