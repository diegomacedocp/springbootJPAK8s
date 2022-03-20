package com.springbootJPAK8s.springbootJPAK8s.service;

import java.util.List;

import com.springbootJPAK8s.springbootJPAK8s.model.Convidado;

public interface ConvidadoService {
	
	Convidado saveConvidado(Convidado convidado);
	
	List<Convidado>getConvidados();
	
	Convidado getConvidado(String nome);

}
