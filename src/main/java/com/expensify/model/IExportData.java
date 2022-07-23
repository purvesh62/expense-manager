package com.expensify.model;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface IExportData {
    void exportExpenseData(List<IExpense> expenseList, HttpServletResponse response) throws IOException;
}
