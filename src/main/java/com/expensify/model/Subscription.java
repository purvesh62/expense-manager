package com.expensify.model;

import com.expensify.persistenceLayer.ISubscriptionDAOService;

import java.sql.SQLException;
import java.util.List;

public class Subscription implements ISubscription {

    private ISubscriptionDAOService subscriptionDAOService;

    private int userId;

    private int subscriptionId;

    private String subscriptionName;

    private String expiryDate;

    public Subscription(int subscriptionId, String subscriptionName, int userId, String expiryDate) {
        this.userId = userId;
        this.subscriptionId = subscriptionId;
        this.subscriptionName = subscriptionName;
        this.expiryDate = expiryDate;
    }

    public Subscription() {

    }

    public Subscription(ISubscriptionDAOService database) {
        subscriptionDAOService = database;
    }

    public ISubscriptionDAOService getSubscriptionDAOService() {
        return subscriptionDAOService;
    }

    public void setSubscriptionDAOService(ISubscription subscription) {
        this.subscriptionDAOService = subscription.getSubscriptionDAOService();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSubscriptionName() {
        return subscriptionName;
    }

    public void setSubscriptionName(String subscription) {
        this.subscriptionName = subscription;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    @Override
    public List<ISubscription> getAllSubscriptionDetails(int userId) {
        try {
            return subscriptionDAOService.getAllSubscriptionDetails(userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ISubscription saveSubscription() {
        try {
            subscriptionDAOService.addNewSubscription(userId, subscriptionName, expiryDate);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    @Override
    public ISubscription updateSubscription() {
        try {
            subscriptionDAOService.updateSubscription(subscriptionId, subscriptionName, expiryDate);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    @Override
    public boolean deleteSubscription(int subscriptionId) {
        try {
            subscriptionDAOService.deleteSubscription(subscriptionId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }
}