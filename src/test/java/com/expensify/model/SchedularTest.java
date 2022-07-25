package com.expensify.model;

import com.expensify.model.INotification;
import com.expensify.persistenceLayerMock.NotificationDAOServiceMock;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SchedularTest {
    @Test
    public void sendDailyReminderToFillExpenseSuccessTest() {
        NotificationDAOServiceMock notificationDAOServiceMock = new NotificationDAOServiceMock();
        notificationDAOServiceMock.getAllNotificationMock();
        List<INotification> notificationList = notificationDAOServiceMock.dailyDailyExpenseSubscribedUsers();
        Assertions.assertEquals(2,notificationList.size());
    }

    @Test
    public void sendDailyReminderToFillExpenseFailureTest() {
        NotificationDAOServiceMock notificationDAOServiceMock = new NotificationDAOServiceMock();
        List<INotification> notificationList = notificationDAOServiceMock.dailyDailyExpenseSubscribedUsers();
        Assertions.assertEquals(0,notificationList.size());

    }
}
