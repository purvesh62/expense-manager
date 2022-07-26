package com.expensify.persistenceLayer;

import com.expensify.database.IDatabase;
import com.expensify.factories.PaymentCategoryFactory;
import com.expensify.model.IPaymentCategory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentCategoriesDAOService implements IPaymentCategoriesDAOService {
    private final IDatabase database;

    public PaymentCategoriesDAOService(IDatabase database) {
        this.database = database;
    }

    public List<IPaymentCategory> getAllPaymentCategories() throws SQLException {
        List<IPaymentCategory> paymentCategoryList = new ArrayList<>();
        try {
            List<Object> parameterList = new ArrayList<>();
            ResultSet resultSet = database.executeProcedure("CALL get_all_payment_categories()", parameterList);
            if (resultSet != null) {
                while (resultSet.next()) {
                    IPaymentCategory paymentCategory = PaymentCategoryFactory.instance().createPaymentCategory(
                            resultSet.getInt("p_id"),
                            resultSet.getString("payment_category")
                    );

                    paymentCategoryList.add(paymentCategory);
                }
            }
            return paymentCategoryList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }

        return paymentCategoryList;
    }
}
