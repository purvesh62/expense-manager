package com.expensify.model;

import com.expensify.model.factories.AbsDataExport;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


public class ExportDataToCSV extends AbsDataExport {


    @Override
    public String addDataToFile(List<IExpense> expenseList) {
        String fileName = "sample.csv";
        boolean fileStatus = createFile(fileName);
        if (fileStatus) {
            List<String[]> rows = new LinkedList<>();
            ListIterator itr = expenseList.listIterator();

            while (itr.hasNext()) {
                Expense expense = (Expense) itr.next();
                rows.add(new String[]{String.valueOf(expense.getExpenseID()), String.valueOf(expense.getUserID()), String.valueOf(expense.getExpenseTitle()), String.valueOf(expense.getDescription()), String.valueOf(expense.getAmount()), String.valueOf(expense.getExpenseCategory()), String.valueOf(expense.getExpenseDate())});
            }
            try {
                CSVWriter csvWriter = new CSVWriter(new FileWriter(super.baseFilePath + fileName));
                csvWriter.writeAll(rows);
                csvWriter.close();
                return fileName;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
