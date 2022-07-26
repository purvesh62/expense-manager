package com.expensify.model;

import com.expensify.persistenceLayer.IPaymentCategoriesDAOService;

import java.sql.SQLException;
import java.util.List;

public class PaymentCategory implements IPaymentCategory {
    private IPaymentCategoriesDAOService paymentCategoriesDAOService;
    private int paymentId;
    private String paymentCategory;

    public PaymentCategory() {

    }

    public PaymentCategory(int paymentId, String paymentCategory) {
        this.paymentId = paymentId;
        this.paymentCategory = paymentCategory;
    }

    public PaymentCategory(IPaymentCategoriesDAOService database) {
        paymentCategoriesDAOService = database;
    }

    public IPaymentCategoriesDAOService getPaymentCategoriesDAOService() {
        return paymentCategoriesDAOService;
    }

    public void setPaymentCategoriesDAOService(IPaymentCategoriesDAOService paymentCategoriesDAOService) {
        this.paymentCategoriesDAOService = paymentCategoriesDAOService;
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

    public List<IPaymentCategory> getAllPaymentCategories() {
        try {
            return paymentCategoriesDAOService.getAllPaymentCategories();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
