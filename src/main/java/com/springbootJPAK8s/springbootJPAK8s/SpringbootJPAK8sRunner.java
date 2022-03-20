package com.springbootJPAK8s.springbootJPAK8s;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.springbootJPAK8s.springbootJPAK8s.model.Convidado;
import com.springbootJPAK8s.springbootJPAK8s.service.ConvidadoService;

@Configuration
@Profile("dev")
public class SpringbootJPAK8sRunner implements ApplicationRunner {

	@Autowired
	private ConvidadoService condidadoService;
	
	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		condidadoService.saveConvidado(new Convidado(0,"Diego Macedo","diegomacedo_cp@hotmail.com","86 99458-9285"));
		
	}
}
