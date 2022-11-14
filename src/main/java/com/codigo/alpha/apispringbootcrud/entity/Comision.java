package com.codigo.alpha.apispringbootcrud.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Comisiones")
public class Comision {

    @Id
    @GeneratedValue
    private Integer id;
    private String ciclo;
    private String periodo;
    private String concepto;
    private String tipoAsesor;
    private String categoria;
    private String codAsesor;
    private String nitAsesor;
    private String nombreCorredor;
    private String tipoDocumentoContratante;
    private String nitContratante;
    private String nombreContratante;
    private String linea;
    private String plan;
    private String programa;
    private String subprograma;
    private String tipDocUsuario;
    private String docUsuario;
    private String nombreUsuario;
    private String suc;
    private String contrato;
    private String familia;
    private String usuario;
    private String tipoNovedad;
    private String novedad;
    private String tipoContrato;
    private String rc;
    private String valorCuota;
    private String valorRecaudoCuota;
    private String factorDeLiq;
    private Integer comision;
    private String regional;
}
