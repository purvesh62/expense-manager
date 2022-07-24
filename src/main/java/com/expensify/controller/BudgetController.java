package com.expensify.controller;

import com.expensify.SessionManager;
import com.expensify.model.*;
import com.expensify.model.factories.BudgetFactory;
import com.expensify.model.factories.WalletFactory;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class BudgetController {
    private IBudget budgetObj;

    public BudgetController() {
        budgetObj = BudgetFactory.instance().createBudget();
    }

    @PostMapping(value = "/budget")
    private String updateBudget(@ModelAttribute("budget") Budget budget, HttpSession session) {
        JSONObject userCache = SessionManager.getSession(session);
        if (userCache.containsKey("userId")) {
            budget.setBudgetDAOService(budgetObj);
            boolean status = budget.updateBudget();
            if (status) {
                return "redirect:/budget";
            }
        }
        return "error";
    }

    @PostMapping(value = "/budget/add")
    private String addBudget(@ModelAttribute("budget") Budget budget, HttpSession session) {
        JSONObject userCache = SessionManager.getSession(session);
        if (userCache.containsKey("userId")) {
            budget.setBudgetDAOService(budgetObj);
            budget.setUserId(1);
            boolean status = budget.saveBudget();
            if (status) {
                return "redirect:/budget";
            }
        }
        return "error";
    }

    @GetMapping(value = "/budget/delete/{budget_id}", produces = "text/html")
    private String deleteBudget(@PathVariable("budget_id") int budgetId, HttpSession session) {
        JSONObject userCache = SessionManager.getSession(session);
        if (userCache.containsKey("userId")) {
            boolean status = budgetObj.deleteBudget(budgetId);
            if (status) {
                return "redirect:/budget";
            }
        }
        return "error";
    }

    @GetMapping(value = "/budget", produces = "text/html")
    public String getAllBudgetDetails(@RequestParam("month") Optional<String> month, Model model, HttpSession session) {
        JSONObject userCache = SessionManager.getSession(session);
        if (userCache.containsKey("userId")) {
            int userId = (Integer) userCache.get("userId");

            String monthNumber = month.orElse("");
            String startDate = null;
            String endDate = null;
            String dateToDisplay = null;
            int currentMonth = 0;

            if (monthNumber.length() > 0) {
                LocalDate currentDate = LocalDate.now();
                LocalDate newDate = LocalDate.of(currentDate.getYear(), Integer.parseInt(monthNumber), 01);

                startDate = newDate.getYear() + "-" + (newDate.getMonth().ordinal() + 1) + "-01";
                endDate = newDate.getYear() + "-" + (newDate.getMonth().ordinal() + 1) + "-" + newDate.lengthOfMonth();

                dateToDisplay = newDate.getMonth().toString() + "," + newDate.getYear();
                currentMonth = newDate.getMonth().ordinal() + 1;
            } else {
                LocalDate currentdate = LocalDate.now();
                startDate = currentdate.getYear() + "-" + (currentdate.getMonth().ordinal() + 1) + "-01";
                endDate = currentdate.getYear() + "-" + (currentdate.getMonth().ordinal() + 1) + "-" + currentdate.lengthOfMonth();
                System.out.println(startDate + endDate);
                dateToDisplay = currentdate.getMonth().toString() + "," + currentdate.getYear();
                currentMonth = currentdate.getMonth().ordinal() + 1;
            }

            List<IBudget> budgetList = budgetObj.getAllBudgetDetailsService(userId, startDate, endDate);
            model.addAttribute("budgetList", budgetList);
            model.addAttribute("dateToDisplay", dateToDisplay);
            model.addAttribute("currentMonth", currentMonth);
            return "budget";
        } else {
            return "redirect:/";
        }

    }

    @GetMapping(value = "/budget/budgetId/{budget_id}", produces = "text/html")
    private String getBudgetById(@PathVariable("budget_id") int budgetId, Model model, HttpSession session) {
        JSONObject userCache = SessionManager.getSession(session);
        if (userCache.containsKey("userId")) {
            int userId = (Integer) userCache.get("userId");
            IBudget budgetDetails = budgetObj.getBudgetById(budgetId);
            List<IWallet> walletList = WalletFactory.instance().createWallet().getAllWalletDetails(userId);
            model.addAttribute("budget", budgetDetails);
            model.addAttribute("wallet", walletList);
            return "updateBudget";
        }
        return "error";
    }

    @GetMapping(value = "/budget/add", produces = "text/html")
    private String addBudgetPage(Model model, HttpSession session) {
        JSONObject userCache = SessionManager.getSession(session);
        if (userCache.containsKey("userId")) {
            int userId = (Integer) userCache.get("userId");
            List<IWallet> walletList = WalletFactory.instance().createWallet().getAllWalletDetails(userId);
            IBudget budget = budgetObj;
            model.addAttribute("wallet", walletList);
            model.addAttribute("budget", budget);
            return "addBudget";
        }
        return "error";

    }
}
