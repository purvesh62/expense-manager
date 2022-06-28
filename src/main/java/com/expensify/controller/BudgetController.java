package com.expensify.controller;

import com.expensify.model.Budget;
import com.expensify.services.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/budget")
public class BudgetController {
    private final BudgetService budgetService;

    @Autowired
    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping
    private List<Budget> getAllBudgetDetails(@RequestParam("user_id") int user_id) throws SQLException {
        return this.budgetService.getAllBudgetDetailsService(user_id);
    }
}
