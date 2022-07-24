package com.expensify.persistenceLayerMock;

import com.expensify.model.INotification;
import com.expensify.model.factories.NotificationFactory;
import com.expensify.persistenceLayer.INotficationDAOService;

import java.util.ArrayList;
import java.util.List;

public class NotificationDAOServiceMock implements INotficationDAOService {

    List<INotification> notificationMockList = new ArrayList<>();

    public void getAllNotificationMock() {
        INotification notificationMock1 = NotificationFactory.instance().createNotification();
        INotification notificationMock2 = NotificationFactory.instance().createNotification();

        notificationMockList.add(notificationMock1);
        notificationMockList.add(notificationMock2);
    }

    @Override
    public List<INotification> dailyDailyExpenseSubscribedUsers() {
        return notificationMockList;
    }

    @Override
    public INotification getBudgetLimitExceedSubscribedUsers(int userId) {
        if(userId == 0){
            return null;
        }else {
            return NotificationFactory.instance().createNotification(1,1,"test@gmail.com",1,1);
        }
    }
}
