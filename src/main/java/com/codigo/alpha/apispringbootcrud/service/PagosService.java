package com.codigo.alpha.apispringbootcrud.service;

import com.codigo.alpha.apispringbootcrud.entity.Pagos;
import com.codigo.alpha.apispringbootcrud.repositoty.PagosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagosService {

    @Autowired
    PagosRepository repository;

    public Pagos save(Pagos pagos){
        return repository.save(pagos);
    }

    public List<Pagos>  saveAll(List<Pagos> pagosList){
        return repository.saveAll(pagosList);
    }
}
