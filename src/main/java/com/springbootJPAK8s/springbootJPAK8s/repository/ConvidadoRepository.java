package com.springbootJPAK8s.springbootJPAK8s.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootJPAK8s.springbootJPAK8s.model.Convidado;

public interface ConvidadoRepository extends JpaRepository<Convidado,Long>{
	
	Convidado findByNome(String nome);
	
}
