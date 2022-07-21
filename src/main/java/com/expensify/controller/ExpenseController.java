package com.expensify.controller;

import com.expensify.SessionManager;
import com.expensify.database.IDatabase;
import com.expensify.database.MySqlDatabase;
import com.expensify.model.*;
import com.expensify.model.factories.BudgetFactory;
import com.expensify.model.factories.ExpenseFactory;
import com.expensify.model.factories.IBudgetFactory;
import com.expensify.model.factories.WalletFactory;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.time.LocalDate;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;

@Controller
public class ExpenseController {

    private final IExpense expenseObj;

    public ExpenseController() {
        expenseObj = ExpenseFactory.instance().createExpense();
    }


    @GetMapping(path = "/", produces = "text/html")
    public String userExpenses(Model model, HttpSession session) {
        try {
            // TODO: Remove later
            JSONObject userCache = new JSONObject();
            userCache.put("userId", 1);
            SessionManager.setSession(session, userCache);

            userCache = SessionManager.getSession(session);
            if (userCache.containsKey("userId")) {
                LocalDate currentdate = LocalDate.now();
                String startDate = currentdate.getYear() + "-" + (currentdate.getMonth().ordinal() + 1) + "-01";
                String endDate = currentdate.getYear() + "-" + (currentdate.getMonth().ordinal() + 1) + "-" + currentdate.lengthOfMonth();

                List<IExpense> expenses = expenseObj.getAllUserExpenses((Integer) userCache.get("userId"), startDate, endDate);
                model.addAttribute("expenseData", expenses);

                List<IExpenseCategory> expenseCategoriesList = ExpenseCategoryFactory.instance().createExpenseCategory().getAllExpenseCategoriesList();
                model.addAttribute("expenseCategoriesList", expenseCategoriesList);

                List<IWallet> walletList = WalletFactory.instance().createWallet().getAllWalletDetails((Integer) userCache.get("userId"));
                model.addAttribute("walletList", walletList);
                model.addAttribute("expense", expenseObj);

                return "home";
            } else {
                return "redirect:/login";
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return "error";
    }

    @RequestMapping(value = "expense", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    List<IExpense> getExpenses(@RequestParam(value = "start_date") String startDate, @RequestParam(value = "end_date") String endDate, HttpSession session) {
        JSONObject userCache = SessionManager.getSession(session);
        if (userCache.containsKey("userId")) {
            int userId = (Integer) userCache.get("userId");
            return expenseObj.getAllUserExpenses(userId, startDate, endDate);
        }
        return null;
    }

    @PostMapping(value = "/add-expense")
    public String addExpense(@ModelAttribute("expense") Expense expense, HttpSession session) throws SQLException, ParseException {
        JSONObject userCache = SessionManager.getSession(session);
        if (userCache.containsKey("userId")) {
            int userId = (Integer) userCache.get("userId");

            expense.setExpenseDOAService(expenseObj);
            expense.setUserID(userId);
            if (expense.getAmount() == null) {
                boolean status = expense.addUserExpense();
            } else {
                boolean status = expense.addUserExpense();
            }

            BudgetFactory.instance().createBudget().checkIfBudgetLimitExceeds(expense);

            return "redirect:/";
        }
        return null;
    }

    @RequestMapping(value = "expense", method = DELETE, produces = "application/json")
    public HashMap<String, Boolean> deleteExpense(@RequestParam(value = "expense_id") Integer expenseId, HttpSession session) {
        HashMap<String, Boolean> response = new HashMap<String, Boolean>();
        JSONObject userCache = SessionManager.getSession(session);
        if (userCache.containsKey("userId")) {
            int userId = (Integer) userCache.get("userId");
            boolean status = expenseObj.deleteUserExpense(expenseId);
            if (status) {
                response.put("status", true);
                return response;
            }
        }
        response.put("status", true);
        return response;
    }

}
