package com.codigo.alpha.apispringbootcrud.service;

import com.codigo.alpha.apispringbootcrud.dto.PagosDTO;
import com.codigo.alpha.apispringbootcrud.entity.Pagos;
import com.codigo.alpha.apispringbootcrud.mappers.PagosMapper;
import com.codigo.alpha.apispringbootcrud.repositoty.PagosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PagosService {

    @Autowired
    PagosRepository repository;

    @Autowired
    PagosMapper mapper;

    public Pagos save(Pagos pagos){
        return repository.save(pagos);
    }

    public List<Pagos>  saveAll(List<Pagos> pagosList){
        return repository.saveAll(pagosList);
    }

    public List<PagosDTO> findByFecha(Date fecha, String busqueda){
        return mapper.mapping(repository.pagaron(fecha, busqueda));
    }


    public void deleteAll(){

    }
}
