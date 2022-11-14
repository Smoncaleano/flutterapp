package com.codigo.alpha.apispringbootcrud.service;

import com.codigo.alpha.apispringbootcrud.entity.NoPagos;
import com.codigo.alpha.apispringbootcrud.repositoty.NoPagosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoPagosService {

    @Autowired
    NoPagosRepository repository;

    public NoPagos save(NoPagos noPagos){
        return repository.save(noPagos);
    }

    public List<NoPagos>  saveAll(List<NoPagos> noPagosList){
        return repository.saveAll(noPagosList);
    }
}
