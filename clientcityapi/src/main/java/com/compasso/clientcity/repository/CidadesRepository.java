package com.compasso.clientcity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.compasso.clientcity.domain.Cidade;

public interface CidadesRepository extends JpaRepository<Cidade, Long> {

	@Query(value = "select C from Cidade as C where upper(C.nome) = upper(?1)")
	Cidade findByNome(String nome);
	
	@Query(value = "select C from Cidade as C where upper(C.estado) = upper(?1)")
	List<Cidade> findByEstado(String estado);
	
}