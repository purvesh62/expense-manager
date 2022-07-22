package com.expensify.model;

import com.expensify.persistenceLayer.IPaymentCategoriesDAOService;

import java.sql.SQLException;
import java.util.List;

public interface IPaymentCategory {

    List<IPaymentCategory> getAllPaymentCategories() throws SQLException;

    IPaymentCategoriesDAOService getPaymentCategoriesDAOService();
}
