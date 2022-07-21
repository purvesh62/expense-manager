package com.expensify.controller;
import com.expensify.database.MySqlDatabase;
import com.expensify.model.Expense;
import com.expensify.model.IExpense;
import com.expensify.model.factories.ExpenseFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Controller
public class AnalyticsController {

    @GetMapping(value = "/analytics", produces = "text/html")
    public String index(Model model) throws SQLException {
        LocalDate currentdate = LocalDate.now();
        String startDate = currentdate.getYear() + "-" + (currentdate.getMonth().ordinal() + 1) + "-01";
        String endDate = currentdate.getYear() + "-" + (currentdate.getMonth().ordinal() + 1) + "-" + currentdate.lengthOfMonth();

        List<IExpense> expenses = new ExpenseFactory().createExpense(MySqlDatabase.instance()).getAllUserExpenses(1, startDate, endDate);
        HashMap<String, Float> expenseCategoryMap = new HashMap<>();

//        for ((IExpense expense :expenses){
//            if (expenseCategoryMap.containsKey(expense.getExpenseTitle())) {
//                expenseCategoryMap.put(expense.getExpenseTitle(), expenseCategoryMap.get(expense.getExpenseTitle()) + expense.getAmount());
//            } else {
//                expenseCategoryMap.put(expense.getExpenseTitle(), expense.getAmount());
//            }
//        }
        model.addAttribute("expenseData", expenses);
        model.addAttribute("expenseMap", expenseCategoryMap);
//        model.addAttribute("chartData", getChartData());
        return "analytics";
    }
}
