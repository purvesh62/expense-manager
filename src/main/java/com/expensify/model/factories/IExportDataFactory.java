package com.expensify.model.factories;

import com.expensify.model.IExportData;

public interface IExportDataFactory {
    IExportData createExportDataToPDF();
    IExportData createExportDataToCSV();
}
