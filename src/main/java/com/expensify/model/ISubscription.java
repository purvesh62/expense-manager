package com.expensify.model;

import com.expensify.persistenceLayer.ISubscriptionDAOService;

import java.sql.SQLException;
import java.util.List;

public interface ISubscription {

    List<ISubscription> getAllSubscriptionDetails(int userId);

    ISubscription saveSubscription();

    ISubscription updateSubscription();

    void deleteSubscription(int subscriptionId);

    ISubscriptionDAOService getSubscriptionDAOService();
}