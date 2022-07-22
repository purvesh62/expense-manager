package com.expensify.model;

import com.expensify.persistenceLayer.IExpenseCategoriesDAOService;

import java.sql.SQLException;
import java.util.List;

public interface IExpenseCategory {
    List<IExpenseCategory> getAllExpenseCategoriesList() throws SQLException;;

    IExpenseCategoriesDAOService getExpenseCategoriesDAOService();
}
