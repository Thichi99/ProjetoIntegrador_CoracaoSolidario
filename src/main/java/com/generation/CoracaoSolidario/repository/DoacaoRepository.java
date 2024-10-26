package com.generation.CoracaoSolidario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.CoracaoSolidario.model.Doacao;

public interface DoacaoRepository extends JpaRepository<Doacao, Long>{

	//public List<Doacao> findAllByDestinoContainingIgnoreCase(@Param("destino") String destino);
}
