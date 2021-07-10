package xyz.nhatbao.ninetour.other;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import xyz.nhatbao.ninetour.model.response.BillResponseModel;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*******************************************************************************
 <pre>

 Copyright (c) 2021 Nguyen Nhat Bao
 This project is licensed under the terms of the MIT license.

 Author: Nguyen Nhat Bao (Kian Nguyen)
 Website: https://kiandev.xyz
 Contact for work: kiannguyen.work@gmail.com
 Feedback to me: kiannguyen.dev@gmail.com
 Github: https://github.com/kian-nguyen

 Please do not remove.

 </pre>
 ******************************************************************************/

public class BillExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<BillResponseModel> listBills;


    public BillExcelExporter(List<BillResponseModel> listBills) {
        this.listBills = listBills;
        workbook = new XSSFWorkbook();
    }


    private void writeHeaderLine() {
        sheet = workbook.createSheet("Bills");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "STT", style);
        createCell(row, 1, "ID", style);
        createCell(row, 2, "Tour", style);
        createCell(row, 3, "Mã chuyến", style);
        createCell(row, 4, "Giá người lớn", style);
        createCell(row, 5, "Số vé người lớn", style);
        createCell(row, 6, "Giá vé trẻ em", style);
        createCell(row, 7, "Số vé trẻ em", style);
        createCell(row, 8, "Giá vé trẻ dưới 2t", style);
        createCell(row, 9, "Số vé trẻ dưới 2t", style);
        createCell(row, 10, "Tiền vé", style);
        createCell(row, 11, "Tiền dịch vụ", style);
        createCell(row, 12, "Tổng tiền", style);
        createCell(row, 13, "Trạng thái", style);

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        List<String> extraServices = new ArrayList<>();
        int STT = 0;
        for (BillResponseModel bill : listBills) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, ++STT, style);
            createCell(row, columnCount++, bill.getId(), style);
            createCell(row, columnCount++, bill.getTourName(), style);
            createCell(row, columnCount++, bill.getCodeOfTrip(), style);
            createCell(row, columnCount++, bill.getAdultPrice(), style);
            createCell(row, columnCount++, bill.getAdultQuantity(), style);
            createCell(row, columnCount++, bill.getChildPrice(), style);
            createCell(row, columnCount++, bill.getChildQuantity(), style);
            createCell(row, columnCount++, bill.getInfantPrice(), style);
            createCell(row, columnCount++, bill.getInfantQuantity(), style);
            createCell(row, columnCount++, bill.getBillCost(), style);
            createCell(row, columnCount++, bill.getExtraCost(), style);
            createCell(row, columnCount++, bill.getCost(), style);
            createCell(row, columnCount, bill.getPaymentStatus(), style);
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}
