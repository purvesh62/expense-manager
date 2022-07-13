package com.expensify.controller;

import com.expensify.SessionManager;
import com.expensify.model.Expense;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ExpenseController {
    private final Expense expense;

    public ExpenseController() {
        this.expense = new Expense();
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

    @RequestMapping(value = "expense", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    Expense post(@RequestBody Expense expense, HttpSession session) {
        JSONObject userCache = SessionManager.getSession(session);
        if (userCache.containsKey("userId")) {
            int userId = (Integer) userCache.get("userId");
            expense.setUserID(userId);
            return expense.addUserExpense();
        }
        return null;
    }


    @GetMapping(path = "/expense", produces = "text/html")
    public String userExpenses(@PathVariable int userID, Model model, HttpSession session) throws SQLException {
        JSONObject userCache = SessionManager.getSession(session);
        if (userCache.containsKey("userId")) {
            LocalDate currentdate = LocalDate.now();
            String startDate = currentdate.getYear() + "-01-" + currentdate.lengthOfMonth();
            String endDate = currentdate.getYear() + "-" + currentdate.getMonth().ordinal() + "-" + currentdate.lengthOfMonth();

            List<Expense> expenses = new ArrayList<>();
            expenses = this.expense.getAllUserExpenses(userID, startDate, endDate);
            model.addAttribute("expenses", expenses);
            return "temp";
        } else {
            return "redirect:/login";
        }
    }
}
