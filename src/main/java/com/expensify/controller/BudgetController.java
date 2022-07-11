package com.expensify.controller;

import com.expensify.model.Budget;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
public class BudgetController {

    private final Budget budget;

    public BudgetController() {
        this.budget = new Budget();
    }

//    @RequestMapping(value="/api/v1/budget", method=RequestMethod.GET, produces="application/json")
//    @ResponseBody
//    private List<Budget> getAllBudgetDetails(@RequestParam("user_id") int userId) throws SQLException {
//        return budget.getAllBudgetDetailsService(userId);
//    }
    @RequestMapping(value="/api/v1/budget", method=RequestMethod.POST, produces="application/json")
    @ResponseBody
    private Budget addBudget(@RequestBody Budget budgetObj) throws SQLException {
        budget.saveBudget(budgetObj);
        return budget;
    }

    @RequestMapping(value="/api/v1/budget", method=RequestMethod.PUT, produces="application/json")
    @ResponseBody
    private Budget updateBudget(@RequestBody Budget budgetObj) throws SQLException {
        budget.updateBudget(budgetObj);
        return budget;
    }

    @RequestMapping(value="/api/v1/budget", method=RequestMethod.DELETE, produces="application/json")
    @ResponseBody
    private void deleteBudget(@RequestParam("budget_id") int budgetId) throws SQLException {
        budget.deleteBudget(budgetId);
    }

    @GetMapping(value="/api/v1/budget/{user_id}", produces="text/html")
    public String getAllBudgetDetails(@PathVariable("user_id") int userId, Model model) throws SQLException {
        List<Budget> budget1 = budget.getAllBudgetDetailsService(userId);
        model.addAttribute("budget" , budget1);
        return "budget";
    }
}
