package com.expensify.model;

import com.expensify.persistenceLayer.PaymentCategoriesDAOService;

public interface IPaymentCategoryFactory {
    PaymentCategory makePaymentCategory();

    PaymentCategoriesDAOService makePaymentCategoryDAOService();

}
