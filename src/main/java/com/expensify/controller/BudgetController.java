package com.expensify.controller;

import com.expensify.model.Budget;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/budget")
public class BudgetController {
    @GetMapping
    private List<Budget> getAllBudgetDetails(@RequestParam("user_id") int userId) throws SQLException {
        Budget budget = new Budget();
        return budget.getAllBudgetDetailsService(userId);
    }
    @PostMapping
    private Budget addBudget(@RequestBody Budget budgetObj) throws SQLException {
        Budget budget = new Budget();
        budget.saveBudget(budgetObj);
        return budget;
    }

    @PutMapping
    private Budget updateBudget(@RequestBody Budget budgetObj) throws SQLException {
        Budget budget = new Budget();
        budget.updateBudget(budgetObj);
        return budget;
    }

    @DeleteMapping
    private void deleteBudget(@RequestParam("budget_id") int budgetId) throws SQLException {
        Budget budget = new Budget();
        budget.deleteBudget(budgetId);
    }
}
