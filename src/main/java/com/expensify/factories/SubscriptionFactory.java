package com.expensify.factories;

import com.expensify.Validators.SubscriptionValidator;
import com.expensify.database.IDatabase;
import com.expensify.database.MySqlDatabase;
import com.expensify.model.ISubscription;
import com.expensify.model.Subscription;
import com.expensify.persistenceLayer.ISubscriptionDAOService;
import com.expensify.persistenceLayer.SubscriptionDAOService;


public class SubscriptionFactory implements ISubscriptionFactory {

    private static SubscriptionFactory subscriptionFactory;

    private SubscriptionFactory() {

    }

    public static SubscriptionFactory instance() {
        if (subscriptionFactory == null) {
            subscriptionFactory = new SubscriptionFactory();
        }
        return subscriptionFactory;
    }

    @Override
    public ISubscription createSubscription() {
        IDatabase database = MySqlDatabase.instance();
        return new Subscription(createSubscriptionDAOService(database));
    }

    @Override
    public ISubscriptionDAOService createSubscriptionDAOService(IDatabase database) {
        return new SubscriptionDAOService(database);
    }

    @Override
    public ISubscription createSubscription(int subscriptionId, String subscriptionName, int userId, String expiryDate) {
        return new Subscription(subscriptionId, subscriptionName, userId, expiryDate);
    }
}
