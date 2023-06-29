package com.codigo.alpha.apispringbootcrud.controllers;

import com.codigo.alpha.apispringbootcrud.dto.ComisionDTO;
import com.codigo.alpha.apispringbootcrud.dto.GenericResponseDTO;
import com.codigo.alpha.apispringbootcrud.dto.PagosDTO;
import com.codigo.alpha.apispringbootcrud.entity.ComisionEntity;
import com.codigo.alpha.apispringbootcrud.helper.ComisionDataExcelExport;
import com.codigo.alpha.apispringbootcrud.helper.ExcelHelper;
import com.codigo.alpha.apispringbootcrud.message.ResponseMessage;
import com.codigo.alpha.apispringbootcrud.service.ExcelService;
import com.codigo.alpha.apispringbootcrud.service.PagosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.server.ResponseStatusException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@Log4j2
public class ExcelController {

    @Autowired
    ExcelService fileService;

    @Autowired
    PagosService pagosService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                fileService.save(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message + e.toString()));
            }
        }

        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }


    @PutMapping("/update")
    public ResponseEntity<ResponseMessage> updateBD(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                fileService.update(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message + e.toString()));
            }
        }

        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @PostMapping("/Sum")
    public ResponseEntity<GenericResponseDTO> sum(@RequestParam("file") MultipartFile file) {

        String message = "";

        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                 GenericResponseDTO genericResponseDTO = fileService.calculateComision(file);
                log.info(genericResponseDTO);
              return new ResponseEntity<>(genericResponseDTO, HttpStatus.OK);
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                e.printStackTrace();
                throw  new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, message, null);
            }
        }

        message = "Please upload an excel file!";
        return new ResponseEntity<>(new GenericResponseDTO(message, true,
                "ERROR", HttpStatus.OK, "HUBO UN ERROR"), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/Comisions")
    public ResponseEntity<List<ComisionDTO>> findAll() {
        log.info(">>>>>>>>>>>>>>>>>>>>>>Consulta comisiones");
        try {
            List<ComisionDTO> comisiones = fileService.findAll();

            if (comisiones.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(comisiones, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/export/{fecha}/{opcion}")
    public void exportIntoExcelFile(HttpServletResponse response, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha, @PathVariable String opcion) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String palabra = (opcion.equalsIgnoreCase("Mayor")?"Pagos":"NoPagos");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + palabra + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List < PagosDTO > comisionList = pagosService.findByFecha(fecha, opcion);
        ComisionDataExcelExport generator = new ComisionDataExcelExport(comisionList);
        generator.generateExcelFile(response);
    }



    @GetMapping("/Pagaron/{fecha}/{busqueda}")
    public ResponseEntity<List<PagosDTO>> pagaron(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha, @PathVariable String busqueda) {
        String message = "";

        log.info("busqueda: " +  busqueda);
        if (fecha != null) {
            try {
                List<PagosDTO> list = pagosService.findByFecha(fecha, busqueda);
                return ResponseEntity.status(HttpStatus.OK).body(list);
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
            }
        }
        return null;
    }

    @PutMapping("cambiar-estado/{id}")
    public ResponseEntity<String> cambiarESTADO(@PathVariable("id") String id) {
        String message = "";

        if (id != null) {
            try {

                fileService.inactivar(id);
                return ResponseEntity.status(HttpStatus.OK).body("Actualización exitosa");
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
            }
        }
        return null;
    }


}
