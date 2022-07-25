package com.expensify.model;

import com.expensify.persistenceLayerMock.SubscriptionDAOServiceMock;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.quartz.SchedulerException;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.List;

@SpringBootTest
public class SubscriptionTest {
    @Test
    public void getAllSubscriptionDetailsServiceSuccessTest() throws SQLException, SchedulerException {
        SubscriptionDAOServiceMock subscriptionDAOServiceMock = new SubscriptionDAOServiceMock();
        subscriptionDAOServiceMock.getAllSubscriptionMock();
        List<ISubscription> allSubscriptionDetails = subscriptionDAOServiceMock.getAllSubscriptionDetails(1);
        Assertions.assertEquals(2, allSubscriptionDetails.size());
    }

    @Test
    public void getAllSubscriptionDetailsServiceFailureTest() throws SQLException {
        SubscriptionDAOServiceMock subscriptionDAOServiceMock = new SubscriptionDAOServiceMock();
        List<ISubscription> allSubscriptionDetails = subscriptionDAOServiceMock.getAllSubscriptionDetails(1);
        Assertions.assertEquals(0,allSubscriptionDetails.size());
    }

    @Test
    public void saveWalletSuccessTest() throws SQLException {
        SubscriptionDAOServiceMock subscriptionDAOServiceMock = new SubscriptionDAOServiceMock();
        boolean result = subscriptionDAOServiceMock.addNewSubscription(1,"Netflix", "2022-10-01");
        Assertions.assertEquals(true, result);

    }

    @Test
    public void saveWalletFailureTest() throws SQLException {
        SubscriptionDAOServiceMock subscriptionDAOServiceMock = new SubscriptionDAOServiceMock();
        boolean result = subscriptionDAOServiceMock.addNewSubscription(0,"Netflix", "2022-10-01");
        Assertions.assertEquals(false, result);
    }

    @Test
    public void updateWalletSuccessTest() throws SQLException {
        SubscriptionDAOServiceMock subscriptionDAOServiceMock = new SubscriptionDAOServiceMock();
        boolean result = subscriptionDAOServiceMock.updateSubscription(1,"Netflix", "2022-10-01");
        Assertions.assertEquals(true, result);
    }

    @Test
    public void updateWalletFailureTest() throws SQLException {
        SubscriptionDAOServiceMock subscriptionDAOServiceMock = new SubscriptionDAOServiceMock();
        boolean result = subscriptionDAOServiceMock.updateSubscription(0,"Netflix", "2022-10-01");
        Assertions.assertEquals(false, result);

    }

    @Test
    public void deleteWalletSuccessTest() throws SQLException {
        SubscriptionDAOServiceMock subscriptionDAOServiceMock = new SubscriptionDAOServiceMock();
        boolean result = subscriptionDAOServiceMock.deleteSubscription(1);
        Assertions.assertEquals(true, result);
    }

    @Test
    public void deleteWalletFailureTest() throws SQLException {
        SubscriptionDAOServiceMock subscriptionDAOServiceMock = new SubscriptionDAOServiceMock();
        boolean result = subscriptionDAOServiceMock.deleteSubscription(0);
        Assertions.assertEquals(false, result);
    }


}
