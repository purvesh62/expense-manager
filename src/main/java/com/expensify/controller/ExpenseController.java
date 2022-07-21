package com.expensify.controller;

import com.expensify.SessionManager;
import com.expensify.model.*;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.sql.SQLException;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;

@Controller
public class ExpenseController {
    private Expense expense;

    private final IWalletFactory walletFactory;

    private final IExpenseCategoryFactory expenseCategoryFactory;

    public ExpenseController() {

        this.expense = new Expense();
        this.walletFactory = new WalletFactory();
        this.expenseCategoryFactory = new ExpenseCategoryFactory();
    }

    @RequestMapping(value = "expense", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    List<Expense> get(@RequestParam(value = "start_date") String startDate, @RequestParam(value = "end_date") String endDate, HttpSession session) {
        try {
            JSONObject userCache = SessionManager.getSession(session);
            if (userCache.containsKey("userId")) {
                int userId = (Integer) userCache.get("userId");
                return this.expense.getAllUserExpenses(userId, startDate, endDate);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "expense", method = DELETE, produces = "application/json")
    public String delete(@RequestParam(value = "expense_id") Integer expenseId, HttpSession session) {
        JSONObject userCache = SessionManager.getSession(session);
        if (userCache.containsKey("userId")) {
            int userId = (Integer) userCache.get("userId");
            expense.setExpenseID(expenseId);
            expense.deleteExpense();
            return "redirect:/";
        }
        return "redirect:/";
    }

    @PostMapping(value = "/add-expense")
    public String post(@ModelAttribute("expense") Expense expense, BindingResult result, HttpSession session) {
        System.out.println(expense);
        JSONObject userCache = SessionManager.getSession(session);
        if (userCache.containsKey("userId")) {
            int userId = (Integer) userCache.get("userId");
            expense.setUserID(userId);
            if (expense.getAmount() == null) {
                expense.addUserExpense();
            } else {
                expense.addUserExpense();
            }
            return "redirect:/";
        }
        return null;
    }


    @GetMapping(path = "/", produces = "text/html")
    public String userExpenses(Model model, HttpSession session) {
        try {
            // TODO: Remove later
            JSONObject userCache = new JSONObject();
            userCache.put("userId", 5);
            SessionManager.setSession(session, userCache);

            userCache = SessionManager.getSession(session);
            if (userCache.containsKey("userId")) {
                LocalDate currentdate = LocalDate.now();
                String startDate = currentdate.getYear() + "-" + (currentdate.getMonth().ordinal() + 1) + "-01";
                String endDate = currentdate.getYear() + "-" + (currentdate.getMonth().ordinal() + 1) + "-" + currentdate.lengthOfMonth();

                List<Expense> expenses = this.expense.getAllUserExpenses((Integer) userCache.get("userId"), startDate, endDate);
                model.addAttribute("expenseData", expenses);

                List<ExpenseCategory> expenseCategoriesList = expenseCategoryFactory.createExpenseCategory().getAllExpenseCategories();
                model.addAttribute("expenseCategoriesList", expenseCategoriesList);

                List<Wallet> walletList = walletFactory.createWallet().getAllWalletDetails((Integer) userCache.get("userId"));
                model.addAttribute("walletList", walletList);


                model.addAttribute("expense", new Expense());
                return "index";
            } else {
                return "redirect:/login";
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return "error";
    }
}
