package com.codigo.alpha.apispringbootcrud.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UpdateDTO {

    String nitContratante;
    String nombreContratante;
    String docUsuario;
    String nombreUsario;
    String valorCuota;
    String comision;



}
