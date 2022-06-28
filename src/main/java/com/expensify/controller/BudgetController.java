package com.expensify.controller;

import com.expensify.model.Budget;
import com.expensify.services.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private List<Budget> getAllBudgetDetails(@RequestParam("user_id") int userId) throws SQLException {
        return this.budgetService.getAllBudgetDetailsService(userId);
    }
    @PostMapping
    private void addBudget(@RequestBody Budget newBudget) throws SQLException {
        this.budgetService.saveBudget(newBudget);
    }

    @PutMapping
    private void updateBudget(@RequestBody Budget budget) throws SQLException {
        this.budgetService.updateBudget(budget);
    }

    @DeleteMapping
    private void addBudget(@RequestParam("budget_id") int budgetId) throws SQLException {
        this.budgetService.deleteBudget(budgetId);
    }
}
