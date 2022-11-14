package com.codigo.alpha.apispringbootcrud.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.codigo.alpha.apispringbootcrud.entity.Comision;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.transform.sax.SAXSource;


public class ExcelHelper {

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = {
    "CICLO", "PERIODO", "CONCEPTO", "TIPO ASESOR", "CATEGORÍA", "COD ASESOR", "NIT ASESOR", "NOMBRE CORREDOR" , "TIP DOC CONTRATANTE", "NIT CONTRATANTE", "NOMBRE CONTRATANTE",
    "LINEA", "PLAN", "PROGRAMA", "SUBPROGRAMA",	"TIP DOC USUARIO", "DOC USUARIO", "NOMBRE USUARIO",	"SUC", "CONTRATO", "FAMILIA", "USUARIO", "TIP NOVEDAD", "NOVEDAD", "TIPO CONTRATO",
    "R/C", "VALOR CUOTA", "VALOR RECAUDO CUOTA", "FACTOR DE LIQ", "COMISION", "REGIONAL"};

    static String SHEET = "Hoja1";

    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<Comision> excelRead(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<Comision> comisions = new ArrayList<Comision>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Comision comision = new Comision();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();



                    switch (cellIdx) {
                        case 0:
                            comision.setCiclo(String.valueOf((int) currentCell.getNumericCellValue()));

                            break;

                        case 1:
                            comision.setPeriodo(String.valueOf((int) currentCell.getNumericCellValue()));

                            break;

                        case 2:
                            comision.setConcepto(currentCell.getStringCellValue());
                            break;

                        case 3:
                            comision.setTipoAsesor(currentCell.getStringCellValue());
                            break;
                        case 4:
                            comision.setCategoria(currentCell.getStringCellValue());
                            break;

                        case 5:
                            comision.setCodAsesor(String.valueOf((int) currentCell.getNumericCellValue()));
                            break;

                        case 6:
                            comision.setNitAsesor(String.valueOf((int) currentCell.getNumericCellValue()));
                            break;

                        case 7:
                            comision.setNombreCorredor(currentCell.getStringCellValue());
                            break;
                        case 8:
                            comision.setTipoDocumentoContratante(currentCell.getStringCellValue());
                            break;

                        case 9:
                            comision.setNitContratante(currentCell.getStringCellValue());
                            break;

                        case 10:
                            comision.setNombreContratante(currentCell.getStringCellValue());
                            break;

                        case 11:
                            comision.setLinea(currentCell.getStringCellValue());
                            break;
                        case 12:
                            comision.setPlan(currentCell.getStringCellValue());
                            break;

                        case 13:
                            comision.setPrograma(currentCell.getStringCellValue());
                            break;

                        case 14:
                            comision.setSubprograma(currentCell.getStringCellValue());
                            break;

                        case 15:
                            comision.setTipDocUsuario(currentCell.getStringCellValue());
                            break;
                        case 16:
                            comision.setDocUsuario(currentCell.getStringCellValue());
                            break;

                        case 17:
                            comision.setNombreUsuario(currentCell.getStringCellValue());
                            break;

                        case 18:
                            comision.setSuc(currentCell.getStringCellValue());
                            break;

                        case 19:
                            comision.setContrato(currentCell.getStringCellValue());
                            break;
                        case 20:
                            comision.setFamilia(currentCell.getStringCellValue());
                            break;

                        case 21:
                            comision.setUsuario(currentCell.getStringCellValue());
                            break;

                        case 22:
                            comision.setTipoNovedad(currentCell.getStringCellValue());
                            break;

                        case 23:
                            comision.setNovedad(currentCell.getStringCellValue());
                            break;
                        case 24:
                            comision.setTipoContrato(currentCell.getStringCellValue());
                            break;

                        case 25:
                            comision.setRc(currentCell.getStringCellValue());
                            break;

                        case 26:
                            comision.setValorCuota(String.valueOf((int) currentCell.getNumericCellValue()));
                            break;
                        case 27:
                            comision.setValorRecaudoCuota(String.valueOf((int) currentCell.getNumericCellValue()));
                            break;

                        case 28:
                            comision.setFactorDeLiq(String.valueOf((int) currentCell.getNumericCellValue()));
                            break;

                        case 29:
                            comision.setComision((int) currentCell.getNumericCellValue());
                            break;
                        case 30:
                            comision.setRegional(currentCell.getStringCellValue());
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

}
