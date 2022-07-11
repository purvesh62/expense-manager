package com.expensify.controller;

import com.expensify.model.Expense;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/expense")
public class ExpenseController {
    private final Expense expense;

    public ExpenseController() {
        this.expense = new Expense();
    }

    @GetMapping
    List<Expense> get(@RequestParam(value = "user_id") int userName, @RequestParam(value = "start_date") String startDate, @RequestParam(value = "end_date") String endDate) {
        try {
            return this.expense.getAllUserExpenses(userName, startDate, endDate);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @PostMapping
    Expense post(@RequestBody Expense expense) {
        return expense.addUserExpense();
    }
}
