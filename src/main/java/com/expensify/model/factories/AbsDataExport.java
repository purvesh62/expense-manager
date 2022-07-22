package com.expensify.model.factories;

import com.expensify.model.IExpense;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public abstract class AbsDataExport {

    protected final String baseFilePath = "/csv/";

    public abstract String addDataToFile(List<IExpense> expenseList);

    public boolean createFile(String fileName) {
        File file = new File(fileName);
        try {
            file.createNewFile();
            new FileOutputStream(file, false).close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
