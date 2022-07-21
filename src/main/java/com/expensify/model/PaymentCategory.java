package com.expensify.model;

import com.expensify.persistenceLayer.PaymentCategoriesDAOService;

import java.util.List;

public class PaymentCategory {
    private int paymentId;
    private String paymentCategory;
    private PaymentCategoriesDAOService paymentCategoriesDAOService;
    private final IPaymentCategoryFactory factory;

    public PaymentCategory(IPaymentCategoryFactory factory) {
        this.factory = factory;
        this.paymentCategoriesDAOService = factory.createPaymentCategoryDAOService();
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentCategory() {
        return paymentCategory;
    }

    public void setPaymentCategory(String paymentCategory) {
        this.paymentCategory = paymentCategory;
    }

    public List<PaymentCategory> getAllPaymentCategoriesList(){
        return paymentCategoriesDAOService.getAllPaymentCategoriesList();
    }
}
