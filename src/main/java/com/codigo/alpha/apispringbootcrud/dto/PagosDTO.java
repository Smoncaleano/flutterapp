package com.codigo.alpha.apispringbootcrud.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class PagosDTO {
    String contrato;
    String nitContratante;
    String nombreContratante;
    String docUsuario;
    String nombreUsuario;
    String plan;
    String programa;
    String subPrograma;
    String novedad;
    String valorCuota;
    Integer comisionPagada;

}
