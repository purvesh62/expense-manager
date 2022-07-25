package com.expensify.factories;

import com.expensify.model.ExportDataToCSV;
import com.expensify.model.ExportDataToPDF;
import com.expensify.model.IExportData;

public class ExportDataFactory implements IExportDataFactory {
    private static IExportDataFactory exportDataFactory = null;

    private ExportDataFactory() {

    }

    public static IExportDataFactory instance() {
        if (exportDataFactory == null) {
            return new ExportDataFactory();
        }
        return exportDataFactory;
    }

    @Override
    public IExportData createExportDataToPDF() {
        return new ExportDataToPDF();
    }

    @Override
    public IExportData createExportDataToCSV() {
        return new ExportDataToCSV();
    }
}
