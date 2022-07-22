package com.expensify.model;

import com.expensify.persistenceLayer.ISubscriptionDAOService;

import java.sql.SQLException;
import java.util.List;

public interface ISubscription {

    List<ISubscription> getAllSubscriptionDetails(int userId) throws SQLException;

    ISubscription saveSubscription() throws SQLException;

    ISubscription updateSubscription() throws SQLException;

    void deleteSubscription(int subscriptionId) throws SQLException;

    ISubscriptionDAOService getSubscriptionDAOService();
}