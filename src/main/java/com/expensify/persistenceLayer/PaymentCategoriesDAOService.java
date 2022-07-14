package com.expensify.persistenceLayer;

import com.expensify.database.Database;
import com.expensify.database.IDatabase;
import com.expensify.model.PaymentCategory;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class PaymentCategoriesDAOService {
    private final IDatabase database;

    public PaymentCategoriesDAOService() {
        this.database = Database.getInstance();
    }
    public List<PaymentCategory> getAllExpenseCategoriesList() {
        List<PaymentCategory> paymentCategoryList = new ArrayList<>();

        try {
            List<Object> parameterList = new ArrayList<>();
            ResultSet resultSet = database.executeProcedure("CALL get_all_expense_categories()",parameterList);
            if (resultSet != null) {
                while (resultSet.next()) {
                    PaymentCategory category= new PaymentCategory();
                    category.setPaymentId(resultSet.getInt("c_id"));
                    category.setPaymentCategory(resultSet.getString("expense_category"));
                    paymentCategoryList.add(category);
                }
            }
            return paymentCategoryList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                database.closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        return paymentCategoryList;
    }

}
