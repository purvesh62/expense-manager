package com.expensify;

import com.expensify.model.IExpense;
import com.expensify.persistenceLayerMock.ExpenseDAOServiceMock;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.ListIterator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ExpensifyApplicationTests {

    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void shouldAnswerWithFalse() {
        assertFalse(false);
    }

    @Test
    public void checkGetAllUserExpenses() {
        ExpenseDAOServiceMock mock = new ExpenseDAOServiceMock();
        List<IExpense> expenseList = mock.getAllUserExpenses(1, "2020-07-22", "2020-07-22");
        if (expenseList == null) {
            assertTrue(false);
        }
        assertTrue(true);
    }
}
