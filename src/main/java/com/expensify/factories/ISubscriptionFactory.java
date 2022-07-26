package com.expensify.factories;

import com.expensify.database.IDatabase;
import com.expensify.database.MySqlDatabase;
import com.expensify.model.IExpenseCategory;
import com.expensify.model.ISubscription;
import com.expensify.persistenceLayer.ExpenseCategoriesDAOService;
import com.expensify.persistenceLayer.IExpenseCategoriesDAOService;
import com.expensify.persistenceLayer.ISubscriptionDAOService;


import java.sql.SQLException;
import java.util.List;

public interface ISubscriptionFactory {
    ISubscription createSubscription();

    //       ISubscription createSubscription();
    ISubscriptionDAOService createSubscriptionDAOService(IDatabase database);

    ISubscription createSubscription(int subscriptionId, String subscriptionName, int userId, String expiryDate);
}



