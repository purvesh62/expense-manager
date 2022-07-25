package com.expensify.model;

import com.expensify.factories.ExpenseFactory;
import com.expensify.persistenceLayerMock.ExpenseDAOServiceMock;
import org.junit.jupiter.api.Test;

public class ExpenseTest {

    @Test
    public void getAllUserExpenses() {
        IExpense expense = ExpenseFactory.instance().createExpense();
//        expense.getExpenseDOAService().getAllUserExpenses(
//                ""
//        );
    }
}
