package com.expensify.factories;

import com.expensify.database.IDatabase;
import com.expensify.model.INotification;
import com.expensify.persistenceLayer.INotficationDAOService;

public interface INotificationFactory {
    INotification createNotification();

    INotficationDAOService createNotificationDAOService(IDatabase database);

    INotification createNotification(int id, int user_id, String email, int s_id, int status);

    INotification createNotification(String email, String subscriptionName);
}
