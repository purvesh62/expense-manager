package com.expensify.persistenceLayer;

import com.expensify.database.IDatabase;
import com.expensify.model.IQuartzNotificationService;
import com.expensify.model.ISubscription;
import com.expensify.model.QuartzNotification;
import com.expensify.model.factories.SubscriptionFactory;
import org.quartz.JobKey;
import org.quartz.SchedulerException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionDAOService implements ISubscriptionDAOService {
    private final IDatabase database;
    private final IQuartzNotificationService service;

    public SubscriptionDAOService(IDatabase database) throws SchedulerException {
        this.database = database;
        service = new QuartzNotification();
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();

        }
        return subscriptionList;
    }

    @Override
    public boolean addNewSubscription(int userId, String subscriptionName, String expiryDate) throws SQLException {
        try {
            String firstName = null;
            String email = null;
            List<Object> parameterList = new ArrayList<>();
            parameterList.add(userId);
            parameterList.add(subscriptionName);
            parameterList.add(expiryDate);
            database.executeProcedure("CALL add_user_subscription(?,?,?)", parameterList);
            parameterList.remove(subscriptionName);
            parameterList.remove(expiryDate);
            ResultSet resultSet = database.executeProcedure("CALL get_all_user_details(?)", parameterList);
            if (resultSet != null) {
                while (resultSet.next()) {
                    firstName = resultSet.getString("first_name");
                    email = resultSet.getString("email");

                }
            }
            service.scheduleExpiryNotification(userId, firstName, email, expiryDate, subscriptionName);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }
        return false;

    }


    @Override
    public boolean updateSubscription(int subscriptionId, String subscriptionName, String expiryDate) throws SQLException {
        try {
            String firstName = null;
            String email = null;
            int userId = 0;
            String oldSubscriptionName = null;
            String oldExpiryDate = null;
            List<Object> parameterList = new ArrayList<>();
            parameterList.add(subscriptionId);
            ResultSet resultSet = database.executeProcedure("CALL get_userid_from_subscription(?)", parameterList);
            if (resultSet != null) {
                while (resultSet.next()) {
                    firstName = resultSet.getString("first_name");
                    email = resultSet.getString("email");
                    userId = resultSet.getInt("user_id");
                    oldSubscriptionName = resultSet.getString("subscription_name");
                    oldExpiryDate = resultSet.getString("expiry_date");
                }
            }
            service.deleteJob(userId, oldSubscriptionName, oldExpiryDate);
            parameterList.add(subscriptionName);
            parameterList.add(expiryDate);
            database.executeProcedure("CALL update_user_subscription(?,?,?)", parameterList);
            service.scheduleExpiryNotification(userId, firstName, email, expiryDate, subscriptionName);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }
        return false;
    }


    @Override
    public boolean deleteSubscription(int subscriptionId) throws SQLException {
        try {
            String expiryDate = null;
            int userId = 0;
            String subscriptionName = null;
            List<Object> parameterList = new ArrayList<>();
            parameterList.add(subscriptionId);
            ResultSet resultSet = database.executeProcedure("CALL get_userid_from_subscription(?)", parameterList);
            if (resultSet != null) {
                while (resultSet.next()) {
                    userId = resultSet.getInt("user_id");
                    subscriptionName = resultSet.getString("subscription_name");
                    expiryDate = resultSet.getString("expiry_date");
                }
            }
            service.deleteJob(userId, subscriptionName, expiryDate);
            database.executeProcedure("CALL delete_user_subscription(?)", parameterList);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }
        return false;

    }


}
