package com.expensify.factories;

import com.expensify.Validators.BudgetValidator;
import com.expensify.Validators.SubscriptionValidator;
import com.expensify.database.IDatabase;
import com.expensify.model.ISubscription;
import com.expensify.persistenceLayer.ISubscriptionDAOService;

public interface ISubscriptionFactory {
    ISubscription createSubscription();

    ISubscriptionDAOService createSubscriptionDAOService(IDatabase database);

    ISubscription createSubscription(int subscriptionId, String subscriptionName, int userId, String expiryDate);

}



