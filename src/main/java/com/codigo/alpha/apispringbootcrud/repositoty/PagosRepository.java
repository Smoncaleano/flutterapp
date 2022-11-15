package com.codigo.alpha.apispringbootcrud.repositoty;

import com.codigo.alpha.apispringbootcrud.entity.Comision;
import com.codigo.alpha.apispringbootcrud.entity.Pagos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PagosRepository extends JpaRepository<Pagos, Integer> {

    @Query(value = "select c.contrato, c.nit_contratante, c.nombre_contratante , c.doc_usuario, c.nombre_usuario, c.plan, c.programa, c.subprograma, c.novedad, c.valor_cuota, p.comision_pagada from pagos p inner join comisiones c on c.id = p.id_comision where p.fecha_no_pago = :fecha and ((:busqueda = 'Mayor' and comision_pagada > 0) or (:busqueda = 'Menor' and comision_pagada <= 0)) ", nativeQuery = true)
    List<Object[]> pagaron(@Param("fecha") Date fecha, @Param("busqueda") String busqueda);
}
