package com.codigo.alpha.apispringbootcrud.service;

import com.codigo.alpha.apispringbootcrud.dto.ComisionDTO;
import com.codigo.alpha.apispringbootcrud.dto.GenericResponseDTO;
import com.codigo.alpha.apispringbootcrud.dto.PagosDTO;
import com.codigo.alpha.apispringbootcrud.dto.UpdateDTO;
import com.codigo.alpha.apispringbootcrud.entity.ComisionEntity;
import com.codigo.alpha.apispringbootcrud.entity.Pagos;
import com.codigo.alpha.apispringbootcrud.helper.ExcelHelper;
import com.codigo.alpha.apispringbootcrud.mappers.ComisionMapper;
import com.codigo.alpha.apispringbootcrud.repositoty.ComisionRepository;
import com.codigo.alpha.apispringbootcrud.repositoty.PagosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ExcelService {

    @Autowired
    ComisionRepository repository;

 @Autowired
    PagosRepository pagosRepository;

    @Autowired
    ComisionMapper comisionMapper;

    public void save(MultipartFile file) {
        try {
            List<ComisionEntity> tutorials = ExcelHelper.excelRead(file.getInputStream());
            repository.saveAll(tutorials);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }


    public void update(MultipartFile file) {
        try {
            List<UpdateDTO> tutorials = ExcelHelper.excelReadUpdate(file.getInputStream());


            tutorials.forEach(updateDTO -> {

                List<ComisionEntity> comisiones = repository.findComision(updateDTO.getNitContratante(), updateDTO.getDocUsuario());

                if(comisiones!=null && !comisiones.isEmpty()) {
                    System.out.println(comisiones.size());

                    int valorCuotaUpdate = Integer.parseInt(updateDTO.getValorCuota());

                    Optional<ComisionEntity> comisionMasCercana = comisiones.stream()
                            .min(Comparator.comparingInt(comision -> Math.abs(Integer.parseInt(comision.getValorCuota()) - valorCuotaUpdate)));



                    comisiones.forEach(comisionEntity -> {

                        if(comisionMasCercana.orElse(null).getSubprograma().equalsIgnoreCase(comisionEntity.getSubprograma())){

                            System.out.println("IF COMISION CERCANA" + comisionMasCercana);
                            comisionMasCercana.orElse(null).setComision(Integer.valueOf(updateDTO.getComision()));
                            comisionMasCercana.orElse(null).setValorCuota(updateDTO.getValorCuota());

                            repository.save(comisionMasCercana.orElse(null));
                        }else{

                            System.out.println("ELSE COMISION CERCANA" + comisionEntity);
                            comisionEntity.setComision(Integer.valueOf(updateDTO.getComision()));
                            comisionEntity.setValorCuota(updateDTO.getValorCuota());

                            repository.save(comisionEntity);
                        }


                    });


                }else{

                    System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< NUEVO");
                    ComisionEntity comisionEntity = ComisionEntity.builder().
                            ciclo("66")
                            .activo(true).docUsuario(updateDTO.getDocUsuario()).nombreUsuario(updateDTO.getNombreUsario())
                            .nitContratante(updateDTO.getNitContratante())
                            .nombreContratante(updateDTO.getNombreContratante())
                            .comision(Integer.valueOf(updateDTO.getComision()))
                            .valorCuota(updateDTO.getValorCuota())
                            .categoria("2")
                            .codAsesor("77")
                            .concepto("CRE")
                            .contrato("new")
                            .factorDeLiq("6")
                            .familia("00")
                            .linea("SAO")
                            .nitAsesor("900008870")
                            .rc("")
                            .plan("")
                            .suc("")
                            .programa("")
                            .novedad("")
                            .periodo("")
                            .regional("")
                            .tipoAsesor("")
                            .tipoDocumentoContratante("")
                            .tipDocUsuario("")
                            .nombreCorredor("")
                            .usuario("")
                            .tipoNovedad("")
                            .subprograma("")
                            .tipoContrato("")
                            .usuario("")
                            .build();
                    repository.save(comisionEntity);
                }



            });

        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public GenericResponseDTO calculateComision(MultipartFile file){
        List<ComisionDTO> bd = comisionMapper.mapping(repository.findClientes());
        AtomicReference<Integer> sum = new AtomicReference<>(0);
        List<Pagos> pagosList = new ArrayList<Pagos>();



        Date fechaActual = new Date();

        Integer count = pagosRepository.countByFecha(fechaActual);
        if(count>=0){
            pagosRepository.deleteByFecha(fechaActual);
        }
        try {
            List<ComisionEntity> comisions = ExcelHelper.excelRead(file.getInputStream());
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Comisions size: " + comisions.size());

            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> PagosBD size: " + bd .size());
            for (int i = 0; i < bd.size(); i++) {
                Pagos pagos = new Pagos();

                for (int j = 0; j < comisions.size(); j++) {
                    if(comisions.get(j).getDocUsuario().equalsIgnoreCase(bd.get(i).getDocUsuario()) && comisions.get(j).getContrato().equalsIgnoreCase(bd.get(i).getContrato())){
                        if(comisions.get(j).getComision()>0){
                            sum.set(sum.get() + comisions.get(j).getComision());
                            pagos.setIdComision(bd.get(i).getId());
                            pagos.setFechaNoPago(fechaActual);
                            pagos.setComisionPagada(comisions.get(j).getComision());
                            pagosList.add(pagos);
                        }else if(comisions.get(j).getComision()<=0){
                            pagos.setIdComision(bd.get(i).getId());
                            pagos.setFechaNoPago(fechaActual);
                            pagos.setComisionPagada(0);
                            pagosList.add(pagos);

                        }

                    }

                }

            }




            if(pagosList.size()>0)
                pagosRepository.saveAll(pagosList);

        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }

        return new GenericResponseDTO(sum, true,
                "Se calculó éxitosamente", HttpStatus.OK, "Cálculo éxitoso");
    }

    public List<ComisionDTO> findAll() {
        return comisionMapper.mapping(repository.findAllClientes());
    }

    public void inactivar(String id){

        repository.cambiarEstado(id);
    }



}
