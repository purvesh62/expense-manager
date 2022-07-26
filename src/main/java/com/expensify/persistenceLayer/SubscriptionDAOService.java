package com.expensify.persistenceLayer;

import com.expensify.database.IDatabase;
import com.expensify.factories.SubscriptionFactory;
import com.expensify.model.ISubscription;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionDAOService implements ISubscriptionDAOService {
    private final IDatabase database;

    public SubscriptionDAOService(IDatabase database) {
        this.database = database;
    }

    public List<ISubscription> getAllSubscriptionDetails(int userId) throws SQLException {
        List<ISubscription> subscriptionList = new ArrayList<>();

        try {
            List<Object> parameterList = new ArrayList<>();
            parameterList.add(userId);

            ResultSet resultSet = database.
                    executeProcedure("CALL get_user_subscription(?)", parameterList);
            if (resultSet != null) {
                while (resultSet.next()) {
                    ISubscription subscription = SubscriptionFactory.instance().createSubscription(
                            resultSet.getInt("subscription_id"),
                            resultSet.getString("subscription_name"),
                            resultSet.getInt("user_id"),
                            String.valueOf(resultSet.getDate("expiry_date"))
                    );
                    subscriptionList.add(subscription);
                }

            }
            return subscriptionList;
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            database.closeConnection();
        }
        return subscriptionList;
    }

    @Override
    public boolean addNewSubscription(int userId, String subscriptionName, String expiryDate) throws SQLException {
        try {
            List<Object> parameterList = new ArrayList<>();
            parameterList.add(userId);
            parameterList.add(subscriptionName);
            parameterList.add(expiryDate);
            try (ResultSet resultSet = database.executeProcedure("CALL add_user_subscription(?,?,?)", parameterList)) {
                return true;
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            database.closeConnection();
        }
        return false;
    }

    @Override
    public boolean updateSubscription(int subscriptionId, String subscriptionName, String expiryDate) throws SQLException {
        try {
            List<Object> parameterList = new ArrayList<>();
            parameterList.add(subscriptionId);
            parameterList.add(subscriptionName);
            parameterList.add(expiryDate);
            try (ResultSet resultSet = database.executeProcedure("CALL update_user_subscription(?,?,?)", parameterList)) {
                return true;
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            database.closeConnection();
        }
        return false;
    }

    @Override
    public boolean deleteSubscription(int subscriptionId) throws SQLException {
        try {

            List<Object> parameterList = new ArrayList<>();
            parameterList.add(subscriptionId);
            try (ResultSet resultSet = database.executeProcedure("CALL delete_user_subscription(?)", parameterList)) {
                return true;
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            database.closeConnection();
        }
        return false;
    }
}

