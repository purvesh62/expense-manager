package com.expensify.persistenceLayer;

import com.expensify.database.MySqlDatabase;
import com.expensify.database.IDatabase;
import com.expensify.model.*;
import com.expensify.model.factories.IExpenseCategoryFactory;
import com.expensify.model.factories.IPaymentCategoryFactory;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ExpenseCategoriesDAOService implements IExpenseCategoriesDAOService {
    private final IDatabase database;
    public ExpenseCategoriesDAOService(IDatabase database) {
        this.database = database;
    }
    public List<IExpenseCategory> getAllExpenseCategories() {
        List<IExpenseCategory> categoryList = new ArrayList<>();

        try {
            List<Object> parameterList = new ArrayList<>();
            ResultSet resultSet = database.executeProcedure("CALL get_all_expense_categories()",parameterList);
            if (resultSet != null) {
                while (resultSet.next()) {
                    IExpenseCategoryFactory expenseCategoryFactory = new ExpenseCategoryFactory();
                    IExpenseCategory expenseCategory = expenseCategoryFactory.createExpenseCategory(
                            resultSet.getInt("c_id"),
                            resultSet.getString("expense_category")
                    );
                    categoryList.add(expenseCategory);
                }
            }
            return categoryList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                database.closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        return categoryList;
    }

}
