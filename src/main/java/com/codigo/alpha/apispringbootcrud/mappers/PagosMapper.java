package com.codigo.alpha.apispringbootcrud.mappers;

import com.codigo.alpha.apispringbootcrud.dto.PagosDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PagosMapper {

    public List<PagosDTO> mapping(List<Object[]> listado){

        List<PagosDTO> mapeado = listado.stream().map(m -> PagosDTO
                .builder()
                .contrato(m[0].toString())
                        .nitContratante(m[1].toString())
                        .nombreContratante(m[2].toString())
                        .docUsuario(m[3].toString())
                        .nombreUsuario(m[4].toString())
                        .plan(m[5].toString())
                        .programa(m[6].toString())
                        .subPrograma(m[7].toString())
                        .novedad(m[8].toString())
                        .valorCuota(m[9].toString())
                        .comisionPagada(Integer.parseInt(m[10].toString()))
                .build())
                .collect(Collectors.toList());

        return mapeado;

    }
}
