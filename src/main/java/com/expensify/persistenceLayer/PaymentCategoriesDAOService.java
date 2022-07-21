package com.expensify.persistenceLayer;

import com.expensify.database.MySqlDatabase;
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
        this.database = MySqlDatabase.instance();
    }
    public List<PaymentCategory> getAllPaymentCategoriesList() {
        List<PaymentCategory> paymentCategoryList = new ArrayList<>();

        try {
            List<Object> parameterList = new ArrayList<>();
            ResultSet resultSet = database.executeProcedure("CALL get_all_payment_categories()",parameterList);
            if (resultSet != null) {
                while (resultSet.next()) {
                    PaymentCategory paymentCategory= new PaymentCategory();
                    paymentCategory.setPaymentId(resultSet.getInt("p_id"));
                    paymentCategory.setPaymentCategory(resultSet.getString("payment_category"));
                    paymentCategoryList.add(paymentCategory);
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
