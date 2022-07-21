package com.expensify.model;

import com.expensify.persistenceLayer.ISubscriptionDAOService;

import java.util.List;

public interface ISubscriptionFactory {
    ISubscription createSubscription();
    ISubscriptionDAOService createSubscriptionDAOService();

    ISubscription createSubscription(int id, int user_id, String email, int s_id, int status);
}
