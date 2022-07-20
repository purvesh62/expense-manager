package com.expensify.persistenceLayer;

import com.expensify.database.Database;
import com.expensify.database.IDatabase;
import com.expensify.model.IPaymentCategoryFactory;
import com.expensify.model.PaymentCategory;
import com.expensify.model.WalletFactory;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class PaymentCategoriesDAOService {
    private final IDatabase database;

    private final IPaymentCategoryFactory factory;

    public PaymentCategoriesDAOService(IPaymentCategoryFactory factory) {
        this.database = Database.instance();
        this.factory = factory;
    }
    public List<PaymentCategory> getAllPaymentCategoriesList() {
        List<PaymentCategory> paymentCategoryList = new ArrayList<>();

        try {
            List<Object> parameterList = new ArrayList<>();
            ResultSet resultSet = database.executeProcedure("CALL get_all_payment_categories()",parameterList);
            if (resultSet != null) {
                while (resultSet.next()) {
                    PaymentCategory paymentCategory= factory.makePaymentCategory();
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
