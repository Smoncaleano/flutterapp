package com.codigo.alpha.apispringbootcrud.service;

import com.codigo.alpha.apispringbootcrud.entity.Comision;
import com.codigo.alpha.apispringbootcrud.entity.NoPagos;
import com.codigo.alpha.apispringbootcrud.helper.ExcelHelper;
import com.codigo.alpha.apispringbootcrud.repositoty.ComisionRepository;
import com.codigo.alpha.apispringbootcrud.repositoty.NoPagosRepository;
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
    NoPagosService noPagosService;

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
        List<NoPagos> noPagosList = new ArrayList<NoPagos>();
        try {
            List<Comision> comisions = ExcelHelper.excelRead(file.getInputStream());
            System.out.println(bd.size());
            System.out.println(comisions.size());
            for (int i = 0; i < comisions.size(); i++) {

                for (int j = 0; j < bd.size(); j++) {
                    if(comisions.get(i).getDocUsuario().equalsIgnoreCase(bd.get(j).getDocUsuario()) && comisions.get(i).getContrato().equalsIgnoreCase(bd.get(j).getContrato())){
                        if(bd.get(j).getComision()>0){
                            sum = sum + bd.get(j).getComision();
                        }else{
                            NoPagos noPagos = new NoPagos();
                            noPagos.setIdComision(bd.get(j).getId());
                            noPagos.setFechaNoPago(new Date());
                            noPagosList.add(noPagos);

                        }

                    }

                }

            }
            if(noPagosList.size()>0)
            noPagosService.saveAll(noPagosList);

        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }

        return sum;
    }

    public List<Comision> findAll() {
        return repository.findAll();
    }


}
