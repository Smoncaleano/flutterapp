package com.codigo.alpha.apispringbootcrud.repositoty;

import com.codigo.alpha.apispringbootcrud.entity.ComisionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ComisionRepository extends JpaRepository<ComisionEntity, Integer> {


    @Query(value = "select id, contrato, nit_contratante, nombre_contratante , doc_usuario, nombre_usuario, plan, programa, subprograma, novedad, activo from comisiones where activo = true", nativeQuery = true)
    List<Object[]> findClientes();


    @Query(value = "select id, contrato, nit_contratante, nombre_contratante , doc_usuario, nombre_usuario, plan, programa, subprograma, novedad, activo from comisiones", nativeQuery = true)
    List<Object[]> findAllClientes();


    @Modifying
    @Query(value = "update comisiones set activo = not activo where nit_contratante = :nitContratante", nativeQuery = true)
    void cambiarEstado( @Param("nitContratante") String nitContratante);


    @Query(value = "select * from comisiones where nit_contratante = :nitContratante and doc_usuario = :docUsuario", nativeQuery = true)
    List<ComisionEntity> findComision(@Param("nitContratante") String nitContratante, @Param("docUsuario") String docUsuario);

}
