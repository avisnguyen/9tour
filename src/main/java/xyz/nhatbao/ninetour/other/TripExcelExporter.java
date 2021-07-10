package xyz.nhatbao.ninetour.other;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import xyz.nhatbao.ninetour.model.response.TripResponseModel;

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

public class TripExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<TripResponseModel> listTrips;

    public TripExcelExporter(List<TripResponseModel> listTrips) {
        this.listTrips = listTrips;
        workbook = new XSSFWorkbook();
    }


    private void writeHeaderLine() {
        sheet = workbook.createSheet("Trips");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "STT", style);
        createCell(row, 1, "ID", style);
        createCell(row, 2, "Thuộc tour", style);
        createCell(row, 3, "Trip code", style);
        createCell(row, 4, "Nơi đi", style);
        createCell(row, 5, "Ngày đi", style);
        createCell(row, 6, "Ngày về", style);
        createCell(row, 7, "Giá vé người lớn", style);
        createCell(row, 8, "Số người lớn tối đa", style);
        createCell(row, 9, "Giá vé trẻ em", style);
        createCell(row, 10, "Số trẻ em tối đa", style);
        createCell(row, 11, "Giá vé trẻ sơ sinh", style);
        createCell(row, 12, "Số trẻ sơ sinh tối đa", style);
        createCell(row, 13, "Ghi chú", style);

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
        List<String> places = new ArrayList<>();
        int STT = 0;
        for (TripResponseModel trip : listTrips) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, ++STT, style);
            createCell(row, columnCount++, trip.getId(), style);
            createCell(row, columnCount++, trip.getTourResponseModel().getName(), style);
            createCell(row, columnCount++, trip.getCode(), style);
            createCell(row, columnCount++, trip.getDeparture(), style);
            createCell(row, columnCount++, trip.getDepartureTime().toString(), style);
            createCell(row, columnCount++, trip.getReturnTime().toString(), style);
            createCell(row, columnCount++, trip.getAdultPrice(), style);
            createCell(row, columnCount++, trip.getAdultMaximum(), style);
            createCell(row, columnCount++, trip.getChildPrice(), style);
            createCell(row, columnCount++, trip.getChildMaximum(), style);
            createCell(row, columnCount++, trip.getInfantPrice(), style);
            createCell(row, columnCount++, trip.getInfantMaximum(), style);
            createCell(row, columnCount++, trip.getNote(), style);
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
