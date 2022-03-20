package com.springbootJPAK8s.springbootJPAK8s.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.springbootJPAK8s.springbootJPAK8s.model.Convidado;
import com.springbootJPAK8s.springbootJPAK8s.repository.ConvidadoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service 
@RequiredArgsConstructor 
@Transactional
@Slf4j
public class ConvidadoServiceImpl implements ConvidadoService{

	private final ConvidadoRepository convidadoRepo;

	@Override
	public Convidado saveConvidado(Convidado convidado) {
		log.info("Salvando novo convidado {} no banco de dados", convidado.getNome());
		return convidadoRepo.save(convidado);
	}

	@Override
	public List<Convidado> getConvidados() {
		log.info("Pesquisando convidados");
		return convidadoRepo.findAll();
	}
	
	@Override
	public Convidado getConvidado(String nome) {
		log.info("Pesquisando convidado {}",nome);
		return convidadoRepo.findByNome(nome);
	}
}
