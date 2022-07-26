package com.expensify.model;

import java.sql.SQLException;
import java.util.List;

public interface IExpenseCategory {
    List<IExpenseCategory> getAllExpenseCategoriesList();
}
