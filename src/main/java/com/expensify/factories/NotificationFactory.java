package com.expensify.factories;

import com.expensify.database.IDatabase;
import com.expensify.database.MySqlDatabase;
import com.expensify.model.INotification;
import com.expensify.model.Notification;
import com.expensify.persistenceLayer.INotficationDAOService;
import com.expensify.persistenceLayer.NotificationDAOService;

public class NotificationFactory implements INotificationFactory {

    private static INotificationFactory notificationFactory = null;

    private NotificationFactory() {

    }

    public static INotificationFactory instance() {
        if (notificationFactory == null) {
            notificationFactory = new NotificationFactory();
        }
        return notificationFactory;
    }

    @Override
    public INotification createNotification() {
        IDatabase database = MySqlDatabase.instance();
        return new Notification(createNotificationDAOService(database));
    }

    @Override
    public INotficationDAOService createNotificationDAOService(IDatabase database) {
        return new NotificationDAOService(database);
    }

    @Override
    public INotification createNotification(String email, String subscriptionName) {
        return new Notification(email, subscriptionName);
    }

    @Override
    public INotification createNotification(int id, int user_id, String email, int s_id, int status) {
        return new Notification(id, user_id, email, s_id, status);
    }
}
