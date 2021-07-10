package xyz.nhatbao.ninetour.other;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import xyz.nhatbao.ninetour.model.response.ExtraServiceResponseModel;
import xyz.nhatbao.ninetour.model.response.TourResponseModel;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

public class ExtraServiceExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<ExtraServiceResponseModel> listExtraServices;

    public ExtraServiceExcelExporter(List<ExtraServiceResponseModel> listExtraServices) {
        this.listExtraServices = listExtraServices;
        workbook = new XSSFWorkbook();
    }


    private void writeHeaderLine() {
        sheet = workbook.createSheet("ExtraServices");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "STT", style);
        createCell(row, 1, "ID", style);
        createCell(row, 2, "Tên dịch vụ", style);
        createCell(row, 3, "Tên tour", style);
        createCell(row, 4, "Giá", style);
        createCell(row, 5, "Mô tả ngắn", style);
        createCell(row, 6, "Nội dung", style);

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
        List<String> tours = new ArrayList<>();
        int STT = 0;
        for (ExtraServiceResponseModel extraService : listExtraServices) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, ++STT, style);
            createCell(row, columnCount++, extraService.getId(), style);
            createCell(row, columnCount++, extraService.getName(), style);
            for (TourResponseModel tour : extraService.getTourResponseModels()) {
                tours.add(tour.getName());
            }
            createCell(row, columnCount++, tours.stream().map(String::valueOf).collect(Collectors.joining(", ", "[", "]")), style);
            tours.clear();
            createCell(row, columnCount++, extraService.getPrice(), style);
            createCell(row, columnCount++, extraService.getShortDescription(), style);
            createCell(row, columnCount++, extraService.getDescription(), style);
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
