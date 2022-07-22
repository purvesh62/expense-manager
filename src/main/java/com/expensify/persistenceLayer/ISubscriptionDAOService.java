package com.expensify.persistenceLayer;

import com.expensify.model.ISubscription;
import com.expensify.model.IWallet;

import java.sql.SQLException;
import java.util.List;

public interface ISubscriptionDAOService {

    List<ISubscription> getAllSubscriptionDetails(int userId) throws SQLException;

    void addNewSubscription(int userId, String subscriptionName, String expiryDate) throws SQLException;

    void updateSubscription(int subscriptionId, String subscriptionName, String expiryDate) throws SQLException;

    void deleteSubscription(int subscriptionId) throws SQLException;

    ISubscription getSubscriptionById(int subscriptionId) throws SQLException;
}
