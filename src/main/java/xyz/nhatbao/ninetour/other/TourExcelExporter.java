package xyz.nhatbao.ninetour.other;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import xyz.nhatbao.ninetour.model.response.TourResponseModel;

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

public class TourExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<TourResponseModel> listTours;

    public TourExcelExporter(List<TourResponseModel> listTours) {
        this.listTours = listTours;
        workbook = new XSSFWorkbook();
    }


    private void writeHeaderLine() {
        sheet = workbook.createSheet("Tours");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "STT", style);
        createCell(row, 1, "ID", style);
        createCell(row, 2, "Code", style);
        createCell(row, 3, "Tên tour", style);
        createCell(row, 4, "Subtitle", style);
        createCell(row, 5, "Nơi đi", style);
        createCell(row, 6, "Nơi đến", style);
        createCell(row, 7, "Thời gian tour", style);
        createCell(row, 8, "Phương tiện", style);

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
        for (TourResponseModel tour : listTours) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, ++STT, style);
            createCell(row, columnCount++, tour.getId(), style);
            createCell(row, columnCount++, tour.getCode(), style);
            createCell(row, columnCount++, tour.getName(), style);
            createCell(row, columnCount++, tour.getShortDescription(), style);
            createCell(row, columnCount++, tour.getDepartureModel().getName(), style);
            createCell(row, columnCount++, tour.getDestinationModel().getName(), style);
            createCell(row, columnCount++, tour.getDuringTime(), style);
            createCell(row, columnCount++, tour.getTransport(), style);
            places.clear();
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
