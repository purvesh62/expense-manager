package com.expensify.model.factories;

import com.expensify.model.ISubscription;
import com.expensify.model.Subscription;
import com.expensify.model.factories.ISubscriptionFactory;
import com.expensify.persistenceLayer.ISubscriptionDAOService;
import com.expensify.persistenceLayer.SubscriptionDAOService;

public class SubscriptionFactory implements ISubscriptionFactory {
    @Override
    public ISubscription createSubscription() {
        return new Subscription(createSubscriptionDAOService());
    }

    @Override
    public ISubscriptionDAOService createSubscriptionDAOService() {
        return new SubscriptionDAOService();
    }

    @Override
    public ISubscription createSubscription(int id, int user_id, String email, int s_id, int status) {
        return new Subscription(id, user_id, email, s_id, status);
    }
}