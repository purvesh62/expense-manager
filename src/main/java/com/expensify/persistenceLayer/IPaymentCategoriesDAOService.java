package com.expensify.persistenceLayer;

import com.expensify.model.IPaymentCategory;

import java.sql.SQLException;
import java.util.List;

public interface IPaymentCategoriesDAOService {
    List<IPaymentCategory> getAllPaymentCategories() throws SQLException;
}
