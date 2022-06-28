package com.expensify.controller;

import com.expensify.model.Expense;
import com.expensify.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/expense")
public class ExpenseController {
    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    private List<Expense> get(
            @RequestParam(value = "user_id") int userName,
            @RequestParam(value = "start_date") String startDate,
            @RequestParam(value = "end_date") String endDate
    ) throws SQLException {

        return this.expenseService.getAllUserExpenses(userName, startDate, endDate);
    }
}
