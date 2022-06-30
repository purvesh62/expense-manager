package com.expensify.controller;

import com.expensify.model.Budget;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/budget")
public class BudgetController {
    private final Budget budget;

    public BudgetController() {
        this.budget = new Budget();
    }

    @GetMapping
    private List<Budget> getAllBudgetDetails(@RequestParam("user_id") int userId) throws SQLException {
        return budget.getAllBudgetDetailsService(userId);
    }
    @PostMapping
    private void addBudget(@RequestBody Budget newBudget) throws SQLException {
        budget.saveBudget(newBudget);
    }

    @PutMapping
    private void updateBudget(@RequestBody Budget budget) throws SQLException {
        budget.updateBudget(budget);
    }

    @DeleteMapping
    private void addBudget(@RequestParam("budget_id") int budgetId) throws SQLException {
        budget.deleteBudget(budgetId);
    }
}
