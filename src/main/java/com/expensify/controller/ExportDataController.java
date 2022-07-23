package com.expensify.controller;

import com.expensify.SessionManager;
import com.expensify.model.DateRange;
import com.expensify.model.ExportDataToCSV;
import com.expensify.model.ExportDataToPDF;
import com.expensify.model.IExpense;
import com.expensify.model.factories.ExpenseFactory;
import com.expensify.model.factories.ExportDataFactory;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import java.util.List;

@Controller
public class ExportDataController {

    @GetMapping(path = "/export", produces = "text/html")
    public String export(Model model, HttpSession session) throws ParseException {
        JSONObject userCache = SessionManager.getSession(session);
        if (userCache.containsKey("userId")) {
            LocalDate currentdate = LocalDate.now();
            String startDate = currentdate.getYear() + "-" + (currentdate.getMonth().ordinal() + 1) + "-01";
            String endDate = currentdate.getYear() + "-" + (currentdate.getMonth().ordinal() + 1) + "-" + currentdate.lengthOfMonth();
            DateRange dateRange = new DateRange(startDate, endDate);
            model.addAttribute("csvDateRange", dateRange);
            model.addAttribute("pdfDateRange", dateRange);
            return "exportData";
        } else {
            return "redirect:/error";
        }
    }

    @PostMapping(value = "/export-csv")
    public String exportCSV(@RequestParam("dateFrom") String dateFrom, @RequestParam("dateTo") String dateTo, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        JSONObject userCache = SessionManager.getSession(session);
        if (userCache.containsKey("userId")) {
            response.setContentType("text/csv");

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=expenses_" + dateFrom + "-" + dateTo + ".csv";
            response.setHeader(headerKey, headerValue);

            List<IExpense> expenseList = null;
            try {
                expenseList = ExpenseFactory.instance().createExpense().getAllUserExpenses((Integer) userCache.get("userId"),
                        new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(dateFrom)),
                        new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(dateTo)));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            ExportDataFactory.instance().createExportDataToCSV().exportExpenseData(expenseList, response);
        }
        model.addAttribute("dateRange", new DateRange(dateFrom, dateTo));
        return "saved";
    }

    @PostMapping(value = "/export-pdf")
    public void exportPDF(@RequestParam("dateFrom") String dateFrom, @RequestParam("dateTo") String dateTo, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        JSONObject userCache = SessionManager.getSession(session);
        if (userCache.containsKey("userId")) {
            response.setContentType("application/pdf");

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=expenses_" + dateFrom + "-" + dateTo + ".pdf";

            response.setHeader(headerKey, headerValue);

            List<IExpense> expenseList = null;
            try {
                expenseList = ExpenseFactory.instance().createExpense().getAllUserExpenses((Integer) userCache.get("userId"),
                        new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(dateFrom)),
                        new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(dateTo)));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            ExportDataFactory.instance().createExportDataToPDF().exportExpenseData(expenseList, response);
        }
    }
}