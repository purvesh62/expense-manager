package com.expensify.controller;

import com.expensify.model.Expense;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/api/v1/expense", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    List<Expense> get(@RequestParam(value = "user_id") int userID, @RequestParam(value = "start_date") String startDate, @RequestParam(value = "end_date") String endDate) {
        try {
            return this.expense.getAllUserExpenses(userID, startDate, endDate);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/api/v1/expense", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    Expense post(@RequestBody Expense expense) {
        return expense.addUserExpense();
    }


    @GetMapping(path = "/expenses/{userID}", produces = "text/html")
    public String userExpenses(@PathVariable int userID, Model model) throws SQLException {
        // Put data into model and return view name
        LocalDate currentdate = LocalDate.now();
        String startDate = currentdate.getYear() + "-01-" + currentdate.lengthOfMonth();
        String endDate = currentdate.getYear() + "-" + currentdate.getMonth().ordinal() + "-" + currentdate.lengthOfMonth();

        List<Expense> expenses = new ArrayList<>();
        expenses = this.expense.getAllUserExpenses(userID, startDate, endDate);
        model.addAttribute("expenses", expenses);
        return "temp";
    }

}
