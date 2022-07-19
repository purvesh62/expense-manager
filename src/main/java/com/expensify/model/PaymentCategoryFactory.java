package com.expensify.model;

import com.expensify.persistenceLayer.PaymentCategoriesDAOService;
import org.springframework.stereotype.Service;

@Service
public class PaymentCategoryFactory implements IPaymentCategoryFactory{
    @Override
    public PaymentCategory makePaymentCategory() {
        return new PaymentCategory(this);
    }
    @Override
    public PaymentCategoriesDAOService makePaymentCategoryDAOService() {
        return new PaymentCategoriesDAOService(this);
    }
}
