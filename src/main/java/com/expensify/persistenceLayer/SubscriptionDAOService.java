package com.expensify.persistenceLayer;
import com.expensify.database.IDatabase;
import com.expensify.model.IQuartzNotificationService;
import com.expensify.model.ISubscription;
import com.expensify.model.QuartzNotification;
import com.expensify.model.factories.SubscriptionFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionDAOService implements ISubscriptionDAOService {
    private IDatabase database;
    private IQuartzNotificationService service;

    public SubscriptionDAOService(IDatabase database) {
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
    public void addNewSubscription(int userId, String subscriptionName, String expiryDate) throws SQLException {
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
            service.scheduleExpiryNotification(userId,firstName,email,expiryDate,subscriptionName);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }

    }

    @Override
    public void updateSubscription(int subscriptionId, String subscriptionName, String expiryDate) throws SQLException {
        try {
            List<Object> parameterList = new ArrayList<>();
            parameterList.add(subscriptionId);
            parameterList.add(subscriptionName);
            parameterList.add(expiryDate);
            database.executeProcedure("CALL update_user_subscription(?,?,?)", parameterList);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }
    }


    @Override
    public void deleteSubscription(int subscriptionId) throws SQLException {
        try {
            List<Object> parameterList = new ArrayList<>();
            parameterList.add(subscriptionId);
            database.executeProcedure("CALL delete_user_subscription(?)", parameterList);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }

    }



}
