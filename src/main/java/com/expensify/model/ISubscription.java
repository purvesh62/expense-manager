package com.expensify.model;

import com.expensify.persistenceLayer.ISubscriptionDAOService;

import java.util.List;

public interface ISubscription {

    List<ISubscription> getAllSubscriptionDetails(int userId);

    boolean saveSubscription();

    boolean updateSubscription();

    boolean deleteSubscription(int subscriptionId);

    ISubscriptionDAOService getSubscriptionDAOService();
}