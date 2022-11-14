package com.codigo.alpha.apispringbootcrud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Pagos")
public class Pagos {

    @Id
    @GeneratedValue
    private int id;
    private int idComision;
    @Temporal(TemporalType.DATE)
    private Date fechaNoPago;
    private int comisionPagada;


}
