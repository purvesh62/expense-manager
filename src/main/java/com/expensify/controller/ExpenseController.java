package com.expensify.controller;

import com.expensify.factories.BudgetFactory;
import com.expensify.factories.ExpenseCategoryFactory;
import com.expensify.factories.ExpenseFactory;
import com.expensify.factories.WalletFactory;
import com.expensify.model.*;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
            JSONObject userCache = SessionManager.getSession(session);
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
                model.addAttribute("name", userCache.get("name"));
                model.addAttribute("email", userCache.get("email"));
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
    List<IExpense> getExpenses(@RequestParam(value = "start_date") String startDate, @RequestParam(value = "end_date") String endDate, HttpSession session, RedirectAttributes rm) {
        JSONObject userCache = SessionManager.getSession(session);
        if (userCache.containsKey("userId")) {
            int userId = (Integer) userCache.get("userId");
            return expenseObj.getAllUserExpenses(userId, startDate, endDate);
        }
        return null;
    }

    @PostMapping(value = "/add-expense")
    public String addExpense(@ModelAttribute("expense") Expense expense, HttpSession session) throws SQLException, ParseException {
        String msg = null;
        JSONObject userCache = SessionManager.getSession(session);
        if (userCache.containsKey("userId")) {
            int userId = (Integer) userCache.get("userId");
            msg = ExpenseFactory.instance().createExpenseValidator().validate(expense);
            if (msg == null) {
                expense.setExpenseDOAService(expenseObj);
                expense.setUserID(userId);
                boolean status = expense.addUserExpense();
                BudgetFactory.instance().createBudget().checkIfBudgetLimitExceeds(userId, expense.getWalletId(), expense.getExpenseDate());
                return "redirect:/";
            }
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "expense", method = DELETE)
    public String deleteExpense(@RequestParam(value = "expense_id") Integer expenseId, HttpSession session) {
        JSONObject userCache = SessionManager.getSession(session);
        if (userCache.containsKey("userId")) {
            int userId = (Integer) userCache.get("userId");
            boolean status = expenseObj.deleteUserExpense(expenseId);
            return "redirect:/";
        }
        return "redirect:/login";
    }
}
