package com.expensify.persistenceLayer;

import com.expensify.database.MySqlDatabase;
import com.expensify.database.IDatabase;
import com.expensify.model.*;
import com.expensify.model.factories.IPaymentCategoryFactory;
import com.expensify.model.factories.IWalletFactory;
import com.expensify.model.factories.WalletFactory;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentCategoriesDAOService implements IPaymentCategoriesDAOService {
    private final IDatabase database;

    public PaymentCategoriesDAOService(IDatabase database) {
        this.database = database;
    }
    public List<IPaymentCategory> getAllPaymentCategories() {
        List<IPaymentCategory> paymentCategoryList = new ArrayList<>();

        try {
            List<Object> parameterList = new ArrayList<>();
            ResultSet resultSet = database.executeProcedure("CALL get_all_payment_categories()",parameterList);
            if (resultSet != null) {
                while (resultSet.next()) {
                    IPaymentCategoryFactory paymentCategoryFactory = new PaymentCategoryFactory();
                    IPaymentCategory paymentCategory = paymentCategoryFactory.createPaymentCategory(
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
            try {
                database.closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        return paymentCategoryList;
    }

}
