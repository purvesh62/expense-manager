//package com.expensify.controller;
//
//import com.expensify.SessionManager;
//import com.expensify.database.Database;
//import com.expensify.database.IDatabase;
//import com.expensify.model.*;
//import com.expensify.persistenceLayer.BudgetDAOServiceFactory;
//import com.expensify.persistenceLayer.IBudgetDAOService;
//import com.expensify.persistenceLayer.IBudgetDAOServiceFactory;
//import org.json.simple.JSONObject;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpSession;
//import java.sql.SQLException;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Controller
//public class BudgetController {
//
//    private IBudgetDAOServiceFactory budgetDAOServiceFactory = new BudgetDAOServiceFactory();
//    private IDatabase database = Database.instance();
//    private IBudgetFactory budgetFactory  = new BudgetFactory();
//
//    private final IWalletFactory walletFactory = new WalletFactory();
//
//
////    @RequestMapping(value="/api/v1/budget", method=RequestMethod.GET, produces="application/json")
////    @ResponseBody
////    private List<Budget> getAllBudgetDetails(@RequestParam("user_id") int userId) throws SQLException {
////        return budget.getAllBudgetDetailsService(userId);
////    }
////    @RequestMapping(value="/api/v1/budget", method=RequestMethod.POST, produces="application/json")
////    @ResponseBody
////    private Budget addBudget(@RequestBody Budget budgetObj) throws SQLException {
////        budget.createBudget().saveBudget(budgetObj);
////        return budgetObj;
////    }
//
////    @RequestMapping(value="/api/v1/budget", method=RequestMethod.PUT, produces="application/json")
////    @ResponseBody
////    private Budget updateBudget(@RequestBody Budget budgetObj) throws SQLException {
////        budget.updateBudget(budgetObj);
////        return budget;
////    }
//
//    @PostMapping(value="/budget")
//    private String updateBudget(@ModelAttribute("budget") Budget budget) throws SQLException {
//        IBudgetDAOService budgetDAOService = budgetDAOServiceFactory.createBudgetDAOService(database);
//        budget.setBudgetDAOService(budgetDAOService);
//        budget.updateBudget();
//        return "redirect:/budget/1";
//    }
//
//    @PostMapping(value="/budget/add")
//    private String addBudget(@ModelAttribute("budget") Budget budget) throws SQLException {
//        System.out.println(budget);
//        IBudgetDAOService budgetDAOService = budgetDAOServiceFactory.createBudgetDAOService(database);
//        budget.setBudgetDAOService(budgetDAOService);
//        budget.setUserId(1);
//        budget.saveBudget();
//        return "redirect:/budget/1";
//    }
//
//    @GetMapping(value="/budget/delete/{budget_id}", produces="text/html")
//    private String deleteBudget(@PathVariable("budget_id") int budgetId) throws SQLException {
//        budgetFactory.createBudget(budgetDAOServiceFactory,database).deleteBudget(budgetId);
//        return "redirect:/budget/1";
//    }
//
//    @GetMapping(value="/budget/{user_id}", produces="text/html")
//    public String getAllBudgetDetails(@PathVariable("user_id") int userId, @RequestParam("month") Optional<String> month, Model model, HttpSession session) throws SQLException {
////        JSONObject userCache = SessionManager.getSession(session);
////
////        if(userCache.containsKey("userId")) {
////            int userId = (Integer)userCache.get("userId");
//
//            String monthNumber = month.orElse("");
//            String startDate = null;
//            String endDate = null;
//            String dateToDisplay = null;
//            int currentMonth = 0;
//
//            if(monthNumber.length() > 0) {
//                LocalDate currentDate = LocalDate.now();
//                LocalDate newDate = LocalDate.of(currentDate.getYear(),Integer.parseInt(monthNumber),01);
//
//                startDate = newDate.getYear() + "-" + (newDate.getMonth().ordinal() + 1) + "-01";
//                endDate = newDate.getYear() + "-" + (newDate.getMonth().ordinal() + 1) + "-" + newDate.lengthOfMonth();
//
//                dateToDisplay = newDate.getMonth().toString() + "," + newDate.getYear();
//                currentMonth = newDate.getMonth().ordinal() + 1;
//            }else {
//                LocalDate currentdate = LocalDate.now();
//                startDate = currentdate.getYear() + "-" + (currentdate.getMonth().ordinal() + 1) + "-01";
//                endDate = currentdate.getYear() + "-" + (currentdate.getMonth().ordinal() + 1) + "-" + currentdate.lengthOfMonth();
//                System.out.println(startDate + endDate);
//                dateToDisplay = currentdate.getMonth().toString() + "," + currentdate.getYear();
//                currentMonth = currentdate.getMonth().ordinal() + 1;
//            }
//
//            List<IBudget> budgetList = budgetFactory.createBudget(budgetDAOServiceFactory,database).getAllBudgetDetailsService(userId,startDate,endDate);
//            model.addAttribute("budgetList" , budgetList);
//            model.addAttribute("dateToDisplay",dateToDisplay);
//            model.addAttribute("currentMonth",currentMonth);
//            return "budget";
////        } else {
////            return "index";
////        }
//
//    }
//
//    @GetMapping(value="/budget/budgetId/{budget_id}", produces="text/html")
//    private String getBudgetById(@PathVariable("budget_id") int budgetId, Model model) throws SQLException {
//       IBudget budgetDetails = budgetFactory.createBudget(budgetDAOServiceFactory,database).getBudgetById(budgetId);
//       List<Wallet> walletList = walletFactory.makeWallet().getAllWalletDetails(1);
//       model.addAttribute("budget",budgetDetails);
//       model.addAttribute("wallet",walletList);
//       return "updateBudget";
//    }
//
//    @GetMapping(value="/budget/add", produces="text/html")
//    private String addBudgetPage(Model model) throws SQLException {
//        List<Wallet> walletList = walletFactory.makeWallet().getAllWalletDetails(1);
//        IBudget budget = budgetFactory.createBudget(budgetDAOServiceFactory,database);
//        model.addAttribute("wallet",walletList);
//        model.addAttribute("budget",budget);
//        return "addBudget";
//    }
//}
