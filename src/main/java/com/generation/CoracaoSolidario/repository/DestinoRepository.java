package com.generation.CoracaoSolidario.repository;

import com.generation.CoracaoSolidario.model.Destino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DestinoRepository extends JpaRepository<Destino, Long> {
    public List<Destino> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);
}
