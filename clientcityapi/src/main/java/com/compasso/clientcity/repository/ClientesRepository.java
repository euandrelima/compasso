package com.compasso.clientcity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.compasso.clientcity.domain.Cliente;

@Repository
public interface ClientesRepository extends JpaRepository<Cliente, Long> {

	@Modifying
	@Query("update Cliente C set C.nome = :nome where C.id = :id")
	int updateName(@Param("nome") String nome, @Param("id") Long id); 
		
	@Query(value = "select C from Cliente as C where upper(C.nome) = upper(?1)")
	Cliente findByNome(String nome);
	
}