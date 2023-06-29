package com.codigo.alpha.apispringbootcrud.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ComisionDTO {

    Integer id;
    String contrato;
    String nitContratante;
    String nombreContratante;
    String docUsuario;
    String nombreUsuario;
    String plan;
    String programa;
    String subPrograma;
    String novedad;
    boolean activo;
}
