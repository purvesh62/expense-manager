package com.expensify.model;

import com.opencsv.CSVWriter;

import java.util.List;

public interface IDataExport {
    boolean createFile(String filename);

    String addDataToFile(List<IExpense> expenseList);
}
