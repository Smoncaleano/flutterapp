package com.codigo.alpha.apispringbootcrud.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.codigo.alpha.apispringbootcrud.dto.UpdateDTO;
import com.codigo.alpha.apispringbootcrud.entity.ComisionEntity;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

@Log4j2

public class ExcelHelper {

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";


    static String SHEET = "Hoja1";

    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<ComisionEntity> excelRead(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<ComisionEntity> comisions = new ArrayList<ComisionEntity>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                ComisionEntity comision = new ComisionEntity();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            comision.setCiclo( currentCell.getCellType().toString().equalsIgnoreCase("NUMERIC")?String.valueOf((int) currentCell.getNumericCellValue()):currentCell.getStringCellValue());


                            break;

                        case 1:
                            comision.setPeriodo( currentCell.getCellType().toString().equalsIgnoreCase("NUMERIC")?String.valueOf((int) currentCell.getNumericCellValue()):currentCell.getStringCellValue());

                            break;

                        case 2:
                            comision.setConcepto( currentCell.getCellType().toString().equalsIgnoreCase("NUMERIC")?String.valueOf((int) currentCell.getNumericCellValue()):currentCell.getStringCellValue());
                            break;

                        case 3:
                            comision.setTipoAsesor( currentCell.getCellType().toString().equalsIgnoreCase("NUMERIC")?String.valueOf((int) currentCell.getNumericCellValue()):currentCell.getStringCellValue());
                            break;
                        case 4:
                            comision.setCategoria( currentCell.getCellType().toString().equalsIgnoreCase("NUMERIC")?String.valueOf((int) currentCell.getNumericCellValue()):currentCell.getStringCellValue());
                            break;

                        case 5:
                            comision.setCodAsesor( currentCell.getCellType().toString().equalsIgnoreCase("NUMERIC")?String.valueOf((int) currentCell.getNumericCellValue()):currentCell.getStringCellValue());
                            break;

                        case 6:
                            comision.setNitAsesor( currentCell.getCellType().toString().equalsIgnoreCase("NUMERIC")?String.valueOf((int) currentCell.getNumericCellValue()):currentCell.getStringCellValue());
                            break;

                        case 7:
                            comision.setNombreCorredor( currentCell.getCellType().toString().equalsIgnoreCase("NUMERIC")?String.valueOf((int) currentCell.getNumericCellValue()):currentCell.getStringCellValue());
                            break;
                        case 8:
                            comision.setTipoDocumentoContratante( currentCell.getCellType().toString().equalsIgnoreCase("NUMERIC")?String.valueOf((int) currentCell.getNumericCellValue()):currentCell.getStringCellValue());
                            break;

                        case 9:
                            comision.setNitContratante( currentCell.getCellType().toString().equalsIgnoreCase("NUMERIC")?String.valueOf((int) currentCell.getNumericCellValue()):currentCell.getStringCellValue());
                            break;

                        case 10:
                            comision.setNombreContratante( currentCell.getCellType().toString().equalsIgnoreCase("NUMERIC")?String.valueOf((int) currentCell.getNumericCellValue()):currentCell.getStringCellValue());
                            break;

                        case 11:
                            comision.setLinea( currentCell.getCellType().toString().equalsIgnoreCase("NUMERIC")?String.valueOf((int) currentCell.getNumericCellValue()):currentCell.getStringCellValue());
                            break;
                        case 12:
                            comision.setPlan( currentCell.getCellType().toString().equalsIgnoreCase("NUMERIC")?String.valueOf((int) currentCell.getNumericCellValue()):currentCell.getStringCellValue());
                            break;

                        case 13:
                            comision.setPrograma( currentCell.getCellType().toString().equalsIgnoreCase("NUMERIC")?String.valueOf((int) currentCell.getNumericCellValue()):currentCell.getStringCellValue());
                            break;

                        case 14:
                            comision.setSubprograma( currentCell.getCellType().toString().equalsIgnoreCase("NUMERIC")?String.valueOf((int) currentCell.getNumericCellValue()):currentCell.getStringCellValue());
                            break;

                        case 15:
                            comision.setTipDocUsuario( currentCell.getCellType().toString().equalsIgnoreCase("NUMERIC")?String.valueOf((int) currentCell.getNumericCellValue()):currentCell.getStringCellValue());
                            break;
                        case 16:
                            comision.setDocUsuario( currentCell.getCellType().toString().equalsIgnoreCase("NUMERIC")?String.valueOf((int) currentCell.getNumericCellValue()):currentCell.getStringCellValue());
                            break;

                        case 17:
                            comision.setNombreUsuario( currentCell.getCellType().toString().equalsIgnoreCase("NUMERIC")?String.valueOf((int) currentCell.getNumericCellValue()):currentCell.getStringCellValue());
                            break;

                        case 18:
                            comision.setSuc( currentCell.getCellType().toString().equalsIgnoreCase("NUMERIC")?String.valueOf((int) currentCell.getNumericCellValue()):currentCell.getStringCellValue());
                            break;

                        case 19:
                            comision.setContrato( currentCell.getCellType().toString().equalsIgnoreCase("NUMERIC")?String.valueOf((int) currentCell.getNumericCellValue()):currentCell.getStringCellValue());
                            break;
                        case 20:
                            comision.setFamilia( currentCell.getCellType().toString().equalsIgnoreCase("NUMERIC")?String.valueOf((int) currentCell.getNumericCellValue()):currentCell.getStringCellValue());
                            break;

                        case 21:
                            comision.setUsuario( currentCell.getCellType().toString().equalsIgnoreCase("NUMERIC")?String.valueOf((int) currentCell.getNumericCellValue()):currentCell.getStringCellValue());
                            break;

                        case 22:
                            comision.setTipoNovedad( currentCell.getCellType().toString().equalsIgnoreCase("NUMERIC")?String.valueOf((int) currentCell.getNumericCellValue()):currentCell.getStringCellValue());
                            break;

                        case 23:
                            comision.setNovedad( currentCell.getCellType().toString().equalsIgnoreCase("NUMERIC")?String.valueOf((int) currentCell.getNumericCellValue()):currentCell.getStringCellValue());
                            break;
                        case 24:
                            comision.setTipoContrato( currentCell.getCellType().toString().equalsIgnoreCase("NUMERIC")?String.valueOf((int) currentCell.getNumericCellValue()):currentCell.getStringCellValue());
                            break;

                        case 25:
                            comision.setRc( currentCell.getCellType().toString().equalsIgnoreCase("NUMERIC")?String.valueOf((int) currentCell.getNumericCellValue()):currentCell.getStringCellValue());
                            break;

                        case 26:
                            comision.setValorCuota( currentCell.getCellType().toString().equalsIgnoreCase("NUMERIC")?String.valueOf((int) currentCell.getNumericCellValue()):currentCell.getStringCellValue());
                            break;
                        case 27:
                            comision.setValorRecaudoCuota( currentCell.getCellType().toString().equalsIgnoreCase("NUMERIC")?String.valueOf((int) currentCell.getNumericCellValue()):currentCell.getStringCellValue());
                            break;

                        case 28:
                            comision.setFactorDeLiq( currentCell.getCellType().toString().equalsIgnoreCase("NUMERIC")?String.valueOf((int) currentCell.getNumericCellValue()):currentCell.getStringCellValue());
                            break;

                        case 29:
                            comision.setComision(Integer.valueOf(currentCell.getCellType().toString().equalsIgnoreCase("NUMERIC")?String.valueOf((int) currentCell.getNumericCellValue()):currentCell.getStringCellValue()));
                            break;
                        case 30:
                            comision.setRegional( currentCell.getCellType().toString().equalsIgnoreCase("NUMERIC")?String.valueOf((int) currentCell.getNumericCellValue()):currentCell.getStringCellValue());
                            break;

                        default:
                            break;
                    }

                    cellIdx++;
                }

                comisions.add(comision);
            }

            workbook.close();

            return comisions;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }



    public static List<UpdateDTO> excelReadUpdate(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<UpdateDTO> updateDTOS = new ArrayList<UpdateDTO>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                UpdateDTO comision = new UpdateDTO();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
                        case 0:
                            comision.setNitContratante(currentCell.getStringCellValue());

                            break;

                        case 1:
                            comision.setNombreContratante(currentCell.getStringCellValue());

                            break;

                        case 2:
                            comision.setDocUsuario(currentCell.getStringCellValue());
                            break;

                        case 3:
                            comision.setNombreUsario(currentCell.getStringCellValue());
                            break;
                        case 4:
                            comision.setValorCuota(String.valueOf((int) currentCell.getNumericCellValue()));
                            break;

                        case 5:
                            comision.setComision(String.valueOf((int) currentCell.getNumericCellValue()));
                            break;

                        default:
                            break;
                    }

                    cellIdx++;
                }

                updateDTOS.add(comision);
            }

            workbook.close();

            return updateDTOS;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }



}
