package com.expensify.persistenceLayerMock;

import com.expensify.model.IBudget;
import com.expensify.model.factories.BudgetFactory;
import com.expensify.persistenceLayer.IBudgetDAOService;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class BudgetDAOServiceMock implements IBudgetDAOService {
    List<IBudget> budgetMockList = new ArrayList<>();

    public void getAllBudgetMock() {
        IBudget budgetMock1 = BudgetFactory.instance().createBudget();
        IBudget budgetMock2 = BudgetFactory.instance().createBudget();
        budgetMockList.add(budgetMock1);
        budgetMockList.add(budgetMock2);
    }


    @Override
    public List<IBudget> getAllBudgetDetails(int userId, String startDate, String endDate) throws SQLException {
        return budgetMockList;
    }

    @Override
    public boolean addNewBudget(int walletId, int userId, float budgetLimit, String month) throws SQLException {
        if (walletId == 0 || userId == 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateBudget(int budgetId, int walletId, float budgetLimit) throws SQLException {
        if (walletId == 0 || budgetId == 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteBudget(int budgetId) throws SQLException {
        if (budgetId == 1) {
            return true;
        }
        return false;
    }

    @Override
    public IBudget getBudgetById(int budgetId) throws SQLException {
        if (budgetId == 1) {
            IBudget budget = BudgetFactory.instance().createBudget(1, 1, "CIBC", 1, 1000, 500);
            return budget;
        }
        return null;
    }

    @Override
    public int checkIfBudgetLimitExceeds(int userId,int walletId, String expenseDate) throws ParseException, SQLException {
        if(userId == 0 || walletId == 0 || expenseDate.length() == 0){
            return 0;
        }
        return 1;
    }
}