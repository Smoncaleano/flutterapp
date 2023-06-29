package com.codigo.alpha.apispringbootcrud.mappers;

import com.codigo.alpha.apispringbootcrud.dto.ComisionDTO;
import com.codigo.alpha.apispringbootcrud.dto.PagosDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ComisionMapper {

    public List<ComisionDTO> mapping(List<Object[]> listado){

        List<ComisionDTO> mapeado = listado.stream().map(m -> ComisionDTO
                        .builder().id(Integer.valueOf(m[0].toString()))
                        .contrato(m[1].toString())
                        .nitContratante(m[2].toString())
                        .nombreContratante(m[3].toString())
                        .docUsuario(m[4].toString())
                        .nombreUsuario(m[5].toString())
                        .plan(m[6].toString())
                        .programa(m[7].toString())
                        .subPrograma(m[8].toString())
                        .novedad(m[9].toString())
                        .activo(Boolean.parseBoolean(m[10].toString()))
                        .build())
                .collect(Collectors.toList());

        return mapeado;

    }

}
