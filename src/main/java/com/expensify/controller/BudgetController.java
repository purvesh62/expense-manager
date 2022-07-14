package com.expensify.controller;

import com.expensify.SessionManager;
import com.expensify.model.Budget;
import com.expensify.model.BudgetFactory;
import com.expensify.model.IBudgetFactory;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BudgetController {

    private final IBudgetFactory budgetFactory  = new BudgetFactory();

//    @RequestMapping(value="/api/v1/budget", method=RequestMethod.GET, produces="application/json")
//    @ResponseBody
//    private List<Budget> getAllBudgetDetails(@RequestParam("user_id") int userId) throws SQLException {
//        return budget.getAllBudgetDetailsService(userId);
//    }
//    @RequestMapping(value="/api/v1/budget", method=RequestMethod.POST, produces="application/json")
//    @ResponseBody
//    private Budget addBudget(@RequestBody Budget budgetObj) throws SQLException {
//        budget.createBudget().saveBudget(budgetObj);
//        return budgetObj;
//    }

//    @RequestMapping(value="/api/v1/budget", method=RequestMethod.PUT, produces="application/json")
//    @ResponseBody
//    private Budget updateBudget(@RequestBody Budget budgetObj) throws SQLException {
//        budget.updateBudget(budgetObj);
//        return budget;
//    }

    @PostMapping(value="/api/v1/budget")
    private String updateBudget(@ModelAttribute("budget") Budget budget) throws SQLException {
        budget.updateBudget();
        return "redirect:/api/v1/budget/1";
    }

//    @RequestMapping(value="/api/v1/budget", method=RequestMethod.DELETE, produces="application/json")
//    @ResponseBody
//    private void deleteBudget(@RequestParam("budget_id") int budgetId) throws SQLException {
//        budget.deleteBudget(budgetId);
//    }
//
    @GetMapping(value="/api/v1/budget", produces="text/html")
    public String getAllBudgetDetails(Model model, HttpSession session) throws SQLException {
        JSONObject userCache = SessionManager.getSession(session);

        if(userCache.containsKey("userId")) {
            int userId = (Integer)userCache.get("userId");
            LocalDate currentdate = LocalDate.now();
            String startDate = currentdate.getYear() + "-" + (currentdate.getMonth().ordinal() + 1) + "-01";
            String endDate = currentdate.getYear() + "-" + (currentdate.getMonth().ordinal() + 1) + "-" + currentdate.lengthOfMonth();
            System.out.println(startDate + endDate);
            List<Budget> budgetList = budgetFactory.createBudget().getAllBudgetDetailsService(userId,startDate,endDate);
            model.addAttribute("budgetList" , budgetList);
            return "budget";
        } else {
            return "index";
        }

    }

    @GetMapping(value="/api/v1/budget/budgetId/{budget_id}", produces="text/html")
    private String getBudgetById(@PathVariable("budget_id") int budgetId, Model model) throws SQLException {
       Budget b = budgetFactory.createBudget().getBudgetById(budgetId);
       model.addAttribute("budget",b);
       return "updateBudget";
    }
}
