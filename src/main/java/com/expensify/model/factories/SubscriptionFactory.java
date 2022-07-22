package com.expensify.model.factories;

import com.expensify.database.IDatabase;
import com.expensify.database.MySqlDatabase;
import com.expensify.model.*;
import com.expensify.persistenceLayer.IPaymentCategoriesDAOService;
import com.expensify.persistenceLayer.ISubscriptionDAOService;
import com.expensify.persistenceLayer.PaymentCategoriesDAOService;
import com.expensify.persistenceLayer.SubscriptionDAOService;

public class SubscriptionFactory implements ISubscriptionFactory {

    private static SubscriptionFactory subscriptionFactory;

    public SubscriptionFactory() {

    }

    public static SubscriptionFactory instance() {
        if (subscriptionFactory == null) {
            subscriptionFactory = new SubscriptionFactory();
        }
        return subscriptionFactory;
    }

    @Override
    public ISubscription createSubscription() {
        IDatabase database = MySqlDatabase.instance();
        return new Subscription(createSubscriptionDAOService(database));

    }

    @Override
    public ISubscriptionDAOService createSubscriptionDAOService(IDatabase database) {
        return new SubscriptionDAOService(database);
    }

    @Override
    public ISubscription createSubscription(int subscriptionId, String subscriptionName, int userId, String expiryDate) {
        return new Subscription(subscriptionId, subscriptionName, userId, expiryDate);
    }

    public static class PaymentCategoryFactory implements IPaymentCategoryFactory {

        private static PaymentCategoryFactory paymentCategoryFactory;

        public PaymentCategoryFactory(){

        }
        public static PaymentCategoryFactory instance() {
            if (paymentCategoryFactory == null) {
                paymentCategoryFactory = new PaymentCategoryFactory();
            }
            return paymentCategoryFactory;
        }
        @Override
        public IPaymentCategory createPaymentCategory() {
            IDatabase database = MySqlDatabase.instance();
            return new PaymentCategory(createPaymentCategoriesDAOService(database));
        }
        @Override
        public IPaymentCategoriesDAOService createPaymentCategoriesDAOService(IDatabase database) {
            return new PaymentCategoriesDAOService(database);
        }

        @Override
        public IPaymentCategory createPaymentCategory(int p_id, String paymentCategory) {
            return new PaymentCategory(p_id, paymentCategory);
        }
    }
}
