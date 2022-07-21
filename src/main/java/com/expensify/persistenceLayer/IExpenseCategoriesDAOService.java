package com.expensify.persistenceLayer;
import com.expensify.model.IExpenseCategory;

import java.sql.SQLException;
import java.util.List;

public interface IExpenseCategoriesDAOService {
    List<IExpenseCategory> getAllExpenseCategories() throws SQLException;
}
