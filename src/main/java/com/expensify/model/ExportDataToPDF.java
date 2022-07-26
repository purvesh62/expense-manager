package com.expensify.model;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.ListIterator;

public class ExportDataToPDF implements IExportData {
    @Override
    public boolean exportExpenseData(List<IExpense> expenseList, HttpServletResponse response) {
        try {
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            font.setSize(18);
            font.setColor(Color.BLACK);

            Paragraph p = new Paragraph("Expenses", font);
            p.setAlignment(Paragraph.ALIGN_CENTER);

            document.add(p);

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100f);
            table.setWidths(new float[]{1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
            table.setSpacingBefore(10);

            writeTableHeader(table, expenseList);
            writeTableData(table, expenseList);

            document.add(table);

            document.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    private void writeTableData(PdfPTable table, List<IExpense> expenseList) {
        if (expenseList.size() > 0) {
            ListIterator itr = expenseList.listIterator();

            while (itr.hasNext()) {
                Expense expense = (Expense) itr.next();
                table.addCell(String.valueOf(expense.getExpenseTitle()));
                table.addCell(String.valueOf(expense.getDescription()));
                table.addCell(String.valueOf(expense.getAmount()));
                table.addCell(String.valueOf(expense.getExpenseDate()));
                table.addCell(String.valueOf(expense.getExpenseCategoryName()));
            }
        }
    }

    private void writeTableHeader(PdfPTable table, List<IExpense> expenseList) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLACK);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Title", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Description", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Amount", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Date", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Expense Category", font));
        table.addCell(cell);
    }
}
