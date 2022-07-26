package com.expensify.persistenceLayer;

import com.expensify.model.ISubscription;

import java.sql.SQLException;
import java.util.List;

public interface ISubscriptionDAOService {

    List<ISubscription> getAllSubscriptionDetails(int userId) throws SQLException;

    boolean addNewSubscription(int userId, String subscriptionName, String expiryDate) throws SQLException;

    boolean updateSubscription(int subscriptionId, String subscriptionName, String expiryDate) throws SQLException;

    boolean deleteSubscription(int subscriptionId) throws SQLException;
}
