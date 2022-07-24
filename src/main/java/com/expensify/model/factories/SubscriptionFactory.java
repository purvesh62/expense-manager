package com.expensify.model.factories;

import com.expensify.database.IDatabase;
import com.expensify.database.MySqlDatabase;
import com.expensify.model.*;
import com.expensify.persistenceLayer.IPaymentCategoriesDAOService;
import com.expensify.persistenceLayer.ISubscriptionDAOService;
import com.expensify.persistenceLayer.PaymentCategoriesDAOService;
import com.expensify.persistenceLayer.SubscriptionDAOService;
import org.quartz.SchedulerException;

public class SubscriptionFactory implements ISubscriptionFactory {

    private static SubscriptionFactory subscriptionFactory;

    private SubscriptionFactory() {

    }

    public static SubscriptionFactory instance() {
        if (subscriptionFactory == null) {
            subscriptionFactory = new SubscriptionFactory();
        }
        return subscriptionFactory;
    }

    @Override
    public ISubscription createSubscription() throws SchedulerException {
        IDatabase database = MySqlDatabase.instance();
        return new Subscription(createSubscriptionDAOService(database));

    }

    @Override
    public ISubscriptionDAOService createSubscriptionDAOService(IDatabase database) throws SchedulerException {
        return new SubscriptionDAOService(database);
    }

    @Override
    public ISubscription createSubscription(int subscriptionId, String subscriptionName, int userId, String expiryDate) {
        return new Subscription(subscriptionId, subscriptionName, userId, expiryDate);
    }

    public static class PaymentCategoryFactory implements IPaymentCategoryFactory {

        private static PaymentCategoryFactory paymentCategoryFactory;

        private  PaymentCategoryFactory(){

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
