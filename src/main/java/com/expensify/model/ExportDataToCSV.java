package com.expensify.model;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.ListIterator;


public class ExportDataToCSV implements IExportData {

    @Override
    public boolean exportExpenseData(List<IExpense> expenseList, HttpServletResponse response) {
        try {
            ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
            String[] csvHeader = {"Title", "Description", "Amount", "Date", "Expense Category"};
            String[] nameMapping = {"expenseTitle", "description", "amount", "expenseDate", "expenseCategoryName"};

            csvWriter.writeHeader(csvHeader);

            if(expenseList.size() > 0){
                ListIterator itr = expenseList.listIterator();

                while (itr.hasNext()) {
                    Expense expense = (Expense) itr.next();
                    csvWriter.write(expense, nameMapping);
                }
            }

            csvWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }
}
