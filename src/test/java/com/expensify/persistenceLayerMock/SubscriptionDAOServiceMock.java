package com.expensify.persistenceLayerMock;

import com.expensify.factories.SubscriptionFactory;
import com.expensify.model.ISubscription;
import com.expensify.persistenceLayer.ISubscriptionDAOService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionDAOServiceMock implements ISubscriptionDAOService {

    List<ISubscription> subscriptionMockList = new ArrayList<>();

    public void getAllSubscriptionMock(){
        ISubscription subscriptionMock1 = SubscriptionFactory.instance().createSubscription();
        ISubscription subscriptionMock2 = SubscriptionFactory.instance().createSubscription();
        subscriptionMockList.add(subscriptionMock1);
        subscriptionMockList.add(subscriptionMock2);
    }

    public void getNullSubscriptionMock(){
        subscriptionMockList = null;
    }

    @Override
    public List<ISubscription> getAllSubscriptionDetails(int userId) throws SQLException {
        return subscriptionMockList;
    }

    @Override
    public boolean addNewSubscription(int userId, String subscriptionName, String expiryDate) throws SQLException {
        if (userId == 0) {
            return false;
        }
        return true;

    }

    @Override
    public boolean updateSubscription(int subscriptionId, String subscriptionName, String expiryDate) throws SQLException {
        if (subscriptionId == 0) {
            return false;
        }
        return true;

    }

    @Override
    public boolean deleteSubscription(int subscriptionId) throws SQLException {
        if (subscriptionId == 1) {
            return true;
        }
        return false;

    }
}
