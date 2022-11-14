package com.codigo.alpha.apispringbootcrud.service;

import com.codigo.alpha.apispringbootcrud.entity.Comision;
import com.codigo.alpha.apispringbootcrud.entity.Pagos;
import com.codigo.alpha.apispringbootcrud.helper.ExcelHelper;
import com.codigo.alpha.apispringbootcrud.repositoty.ComisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ExcelService {

    @Autowired
    ComisionRepository repository;

    @Autowired
    PagosService pagosService;

    public void save(MultipartFile file) {
        try {
            List<Comision> tutorials = ExcelHelper.excelRead(file.getInputStream());
            repository.saveAll(tutorials);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public Integer calculateComision(MultipartFile file){
        List<Comision> bd = repository.findAll();
        Integer sum = 0;
        List<Pagos> pagosList = new ArrayList<Pagos>();
        try {
            List<Comision> comisions = ExcelHelper.excelRead(file.getInputStream());
            for (int i = 0; i < bd.size(); i++) {
                Pagos pagos = new Pagos();

                for (int j = 0; j < comisions.size(); j++) {
                    if(comisions.get(j).getDocUsuario().equalsIgnoreCase(bd.get(i).getDocUsuario()) && comisions.get(j).getContrato().equalsIgnoreCase(bd.get(i).getContrato())){
                        if(comisions.get(j).getComision()>0){
                            sum = sum + comisions.get(j).getComision();
                            pagos.setIdComision(bd.get(i).getId());
                            pagos.setFechaNoPago(new Date());
                            pagos.setComisionPagada(comisions.get(j).getComision());
                            pagosList.add(pagos);
                        }else if(comisions.get(j).getComision()<=0){
                            pagos.setIdComision(bd.get(i).getId());
                            pagos.setFechaNoPago(new Date());
                            pagos.setComisionPagada(0);
                            pagosList.add(pagos);

                        }

                    }

                }

            }
            if(pagosList.size()>0)
            pagosService.saveAll(pagosList);

        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }

        return sum;
    }

    public List<Comision> findAll() {
        return repository.findAll();
    }


}
