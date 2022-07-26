package com.expensify.controller;

import com.expensify.model.DateRange;

import com.expensify.model.DateUtil;
import com.expensify.model.IExpense;
import com.expensify.factories.ExpenseFactory;
import com.expensify.factories.ExportDataFactory;
import com.expensify.model.SessionManager;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ExportDataController {

    @GetMapping(path = "/export", produces = "text/html")
    public String export(Model model, HttpSession session) {
        JSONObject userCache = SessionManager.getSession(session);
        if (userCache.containsKey("userId")) {
            DateRange dateRangeForCSV = new DateRange();
            DateRange dateRangeForPDF = new DateRange();
            model.addAttribute("csvDateRange", dateRangeForCSV);
            model.addAttribute("pdfDateRange", dateRangeForPDF);
            model.addAttribute("name", userCache.get("name"));
            model.addAttribute("email", userCache.get("email"));
            return "exportData";
        } else {
            return "error";
        }
    }

    @PostMapping(value = "/export-csv")
    public String exportCSV(@RequestParam("dateFrom") String dateFrom, @RequestParam("dateTo") String dateTo, HttpSession session, HttpServletResponse response) {
        JSONObject userCache = SessionManager.getSession(session);
        if (userCache.containsKey("userId")) {
            response.setContentType("text/csv");
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=expenses_" + dateFrom + "-" + dateTo + ".csv";
            response.setHeader(headerKey, headerValue);

            List<IExpense> expenseList = ExpenseFactory.instance().createExpense().getAllUserExpenses(
                    (Integer) userCache.get("userId"),
                    DateUtil.formatDate(dateFrom),
                    DateUtil.formatDate(dateTo)
            );
            boolean status = ExportDataFactory.instance().createExportDataToCSV().exportExpenseData(expenseList, response);
        }
        return "error";
    }

    @PostMapping(value = "/export-pdf")
    public void exportPDF(@RequestParam("dateFrom") String dateFrom, @RequestParam("dateTo") String dateTo, HttpSession session, HttpServletResponse response) {
        JSONObject userCache = SessionManager.getSession(session);
        if (userCache.containsKey("userId")) {
            response.setContentType("application/pdf");

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=expenses_" + dateFrom + "-" + dateTo + ".pdf";
            response.setHeader(headerKey, headerValue);
            List<IExpense> expenseList = ExpenseFactory.instance().createExpense().getAllUserExpenses(
                    (Integer) userCache.get("userId"),
                    DateUtil.formatDate(dateFrom),
                    DateUtil.formatDate(dateTo)
            );
            boolean status = ExportDataFactory.instance().createExportDataToPDF().exportExpenseData(expenseList, response);
        }
    }
}
