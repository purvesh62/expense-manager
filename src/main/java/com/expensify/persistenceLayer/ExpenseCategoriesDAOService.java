package com.expensify.persistenceLayer;

import com.expensify.database.Database;
import com.expensify.database.IDatabase;
import com.expensify.model.ExpenseCategory;
import com.expensify.model.Wallet;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExpenseCategoriesDAOService {
    private final IDatabase database;
    public ExpenseCategoriesDAOService() {
        this.database = Database.getInstance();
    }
    public List<ExpenseCategory> getAllExpenseCategoriesList() {
        List<ExpenseCategory> categoryList = new ArrayList<>();

        try {
            List<Object> parameterList = new ArrayList<>();
            ResultSet resultSet = database.executeProcedure("CALL get_all_expense_categories()",parameterList);
            if (resultSet != null) {
                while (resultSet.next()) {
                    ExpenseCategory category= new ExpenseCategory();
                    category.setCategoryID(resultSet.getInt("c_id"));
                    category.setCategoryName(resultSet.getString("expense_category"));
                    categoryList.add(category);
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
