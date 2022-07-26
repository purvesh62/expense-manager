package com.expensify.model;

import com.expensify.persistenceLayerMock.NotificationDAOServiceMock;
import com.expensify.persistenceLayerMock.SMTPEmailServiceMock;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class NotificationTest {
    @Test
    public void notifyUsersTest() {
        SMTPEmailServiceMock.instance("test@gmail.com", "Test Email", "Test Email Subject").sendEmail();
        assert (true);
    }

    @Test
    public void getDailyExpenseSubscribedUserSuccessTest() {
        NotificationDAOServiceMock notificationDAOServiceMock = new NotificationDAOServiceMock();
        notificationDAOServiceMock.getAllNotificationMock();
        List<INotification> notificationList = notificationDAOServiceMock.dailyDailyExpenseSubscribedUsers();
        Assertions.assertEquals(2, notificationList.size());

    }

    @Test
    public void getDailyExpenseSubscribedUserFailureTest() {
        NotificationDAOServiceMock notificationDAOServiceMock = new NotificationDAOServiceMock();
        notificationDAOServiceMock.getNullNotificationMock();
        List<INotification> notificationList = notificationDAOServiceMock.dailyDailyExpenseSubscribedUsers();
        Assertions.assertEquals(null, notificationList);
    }

    @Test
    public void notifyBudgetLimitExceedsSuccessTest() {
        NotificationDAOServiceMock notificationDAOServiceMock = new NotificationDAOServiceMock();
        INotification notification = notificationDAOServiceMock.getBudgetLimitExceedSubscribedUsers(1);

        Assertions.assertNotEquals(null, notification);
    }

    @Test
    public void notifyBudgetLimitExceedsFailureTest() {
        NotificationDAOServiceMock notificationDAOServiceMock = new NotificationDAOServiceMock();
        INotification notification = notificationDAOServiceMock.getBudgetLimitExceedSubscribedUsers(0);
        Assertions.assertEquals(null, notification);
    }

    @Test
    public void getUsersWhoseSubscriptionIsExpiringSuccessTest(){
        NotificationDAOServiceMock notificationDAOServiceMock = new NotificationDAOServiceMock();
        notificationDAOServiceMock.getAllNotificationMock();
        List<INotification> notificationList =  notificationDAOServiceMock.getUsersWhoseSubscriptionIsExpiring("2023-04-05");
        Assertions.assertEquals(2, notificationList.size());
    }
    @Test
    public void getUsersWhoseSubscriptionIsExpiringFailureTest(){
        NotificationDAOServiceMock notificationDAOServiceMock = new NotificationDAOServiceMock();
        notificationDAOServiceMock.getNullNotificationMock();
        List<INotification> notificationList =  notificationDAOServiceMock.getUsersWhoseSubscriptionIsExpiring("2020-04-05");
        Assertions.assertEquals(null , notificationList);
    }

}
