package com.springbootJPAK8s.springbootJPAK8s.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.springbootJPAK8s.springbootJPAK8s.model.Convidado;
import com.springbootJPAK8s.springbootJPAK8s.service.ConvidadoService;

@Controller
public class ConvidadoController {
	
	@Autowired
	private ConvidadoService convidadoService;
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("listaconvidados")
	public String listaConvidados(Model model) {
		
		Iterable<Convidado> convidados = convidadoService.getConvidados();
		
		model.addAttribute("convidados", convidados);
		
		return "listaconvidados";
	}
	
	@RequestMapping(value="salvarconvidado",method = RequestMethod.POST)
	public String salvarConvidado(@RequestParam("nome") String nome,
			@RequestParam("email") String email,@RequestParam("telefone") String telefone,Model model) {
		
		convidadoService.saveConvidado(new Convidado(0,nome,email,telefone));
		
		Iterable<Convidado> convidados = convidadoService.getConvidados();

		model.addAttribute("convidados", convidados);
		
		return "listaconvidados";
	}
	
}
