package com.expensify.persistenceLayerMock;

import com.expensify.factories.ExpenseCategoryFactory;
import com.expensify.model.IExpenseCategory;
import com.expensify.persistenceLayer.IExpenseCategoriesDAOService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExpenseCategoriesDAOServiceMock implements IExpenseCategoriesDAOService {

    List<IExpenseCategory> expenseCategoryMockList = new ArrayList<>();

    public void getAllExpenseCategoryMock() {
        IExpenseCategory expenseCategoryMock1 = ExpenseCategoryFactory.instance().createExpenseCategory();
        expenseCategoryMockList.add(expenseCategoryMock1);
    }

    public void getNullExpenseCategoryMock(){
          expenseCategoryMockList = null;
    }

    @Override
    public List<IExpenseCategory> getAllExpenseCategories() throws SQLException {
        return expenseCategoryMockList;
    }

}
