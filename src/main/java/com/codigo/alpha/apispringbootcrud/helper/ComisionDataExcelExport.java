package com.codigo.alpha.apispringbootcrud.helper;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.codigo.alpha.apispringbootcrud.dto.PagosDTO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ComisionDataExcelExport{

    private List <PagosDTO> comisionList;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ComisionDataExcelExport(List <PagosDTO> studentList) {
        this.comisionList = studentList;
        workbook = new XSSFWorkbook();
    }
    private void writeHeader() {
        sheet = workbook.createSheet("Student");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        createCell(row, 0, "CONTRATO", style);
        createCell(row, 1, "NIT Contratante", style);
        createCell(row, 2, "Nombre Contratante", style);
        createCell(row, 3, "Doc. Usuario", style);
        createCell(row, 4, "Nombre Usuario", style);
        createCell(row, 5, "Plan", style);
        createCell(row, 6, "Programa", style);
        createCell(row, 7, "Sub-programa", style);
        createCell(row, 8, "Novedad", style);
        createCell(row, 9, "Valor cuota", style);
        createCell(row, 10, "Comision", style);
    }
    private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        } else {
            cell.setCellValue((Boolean) valueOfCell);
        }
        cell.setCellStyle(style);
    }
    private void write() {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for (PagosDTO pagosDTO: comisionList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
                createCell(row, columnCount++, pagosDTO.getContrato(), style);
            createCell(row, columnCount++, pagosDTO.getNitContratante(), style);
            createCell(row, columnCount++, pagosDTO.getNombreContratante(), style);
            createCell(row, columnCount++, pagosDTO.getDocUsuario(), style);
            createCell(row, columnCount++, pagosDTO.getNombreUsuario(), style);
            createCell(row, columnCount++, pagosDTO.getPlan(), style);
            createCell(row, columnCount++, pagosDTO.getPrograma(), style);
            createCell(row, columnCount++, pagosDTO.getSubPrograma(), style);
            createCell(row, columnCount++, pagosDTO.getNovedad(), style);
            createCell(row, columnCount++, pagosDTO.getValorCuota(), style);
            createCell(row, columnCount++, pagosDTO.getComisionPagada(), style);

        }
    }
    public void generateExcelFile(HttpServletResponse response) throws IOException {
        writeHeader();
        write();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

}
