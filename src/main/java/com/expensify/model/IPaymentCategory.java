package com.expensify.model;

import java.sql.SQLException;
import java.util.List;

public interface IPaymentCategory {

    List<IPaymentCategory> getAllPaymentCategories() throws SQLException;
}
