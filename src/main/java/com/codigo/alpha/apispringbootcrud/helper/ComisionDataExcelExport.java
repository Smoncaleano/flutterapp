package com.codigo.alpha.apispringbootcrud.helper;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codigo.alpha.apispringbootcrud.entity.Comision;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

public class ComisionDataExcelExport extends AbstractXlsxView{
    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // define excel file name to be exported
        response.addHeader("Content-Disposition", "attachment;fileName=Pagos"+new Date()+".xlsx");

        // read data provided by controller
        @SuppressWarnings("unchecked")
        List<Comision> list = (List<Comision>) model.get("list");

        // create one sheet
        Sheet sheet = workbook.createSheet("Pagos");

        // create row0 as a header
        Row row0 = sheet.createRow(0);
        row0.createCell(0).setCellValue("ID");
        row0.createCell(1).setCellValue("NAME");
        row0.createCell(2).setCellValue("LOCATION");
        row0.createCell(3).setCellValue("AMOUNT");

        // create row1 onwards from List<T>
        int rowNum = 1;
        for(Invoice spec : list) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(spec.getId());
            row.createCell(1).setCellValue(spec.getName());
            row.createCell(2).setCellValue(spec.getLocation());
            row.createCell(3).setCellValue(spec.getAmount());
        }
    }
    }
}