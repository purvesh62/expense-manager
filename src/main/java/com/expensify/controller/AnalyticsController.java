package com.expensify.controller;

import com.expensify.model.DateUtil;
import com.expensify.model.IAnalytics;
import com.expensify.factories.ExpenseFactory;
import com.expensify.model.SessionManager;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.HashMap;

@Controller
public class AnalyticsController {

    @GetMapping(value = "/analytics", produces = "text/html")
    public String index(@RequestParam(value = "date", required = false) String inputDate, Model model, HttpSession session) {
        JSONObject userCache = SessionManager.getSession(session);
        LocalDate localDate;
        if (userCache.containsKey("userId")) {
            int userId = (Integer) userCache.get("userId");
            if (inputDate == null) {
                localDate = LocalDate.now();
            } else {
                localDate = LocalDate.parse(inputDate);
            }

            IAnalytics expenseAnalytics = ExpenseFactory.instance().createExpenseAnalytics();

            HashMap<Integer, Float> userMonthlyExpenseMap = expenseAnalytics.getMonthlyAnalytics(userId, localDate);

            HashMap<String, Float> userMonthlyExpenseWithCategoryMap = expenseAnalytics.getMonthlyAnalyticsByCategories(userId, DateUtil.getFirstDayOfMonth(localDate), DateUtil.getLastDayOfMonth(localDate));

            model.addAttribute("selectedDate", localDate);
            model.addAttribute("monthlyExpenseMap", userMonthlyExpenseMap);
            model.addAttribute("monthlyExpenseWithCategoryMap", userMonthlyExpenseWithCategoryMap);
            model.addAttribute("name", userCache.get("name"));
            model.addAttribute("email", userCache.get("email"));
            return "analytics";
        }
        return "error";
    }
}
