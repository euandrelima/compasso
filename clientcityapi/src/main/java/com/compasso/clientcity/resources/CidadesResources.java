package com.compasso.clientcity.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.compasso.clientcity.domain.Cidade;
import com.compasso.clientcity.repository.CidadesRepository;

@RestController
@RequestMapping("/cidades")
public class CidadesResources {
	
	@Autowired
	private CidadesRepository cidadesRepository;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@RequestBody Cidade cidade) {
		
		cidadesRepository.save(cidade);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(cidade.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(value = "/{nome}", method = RequestMethod.GET)
	public Cidade buscarPorNome(@PathVariable("nome") String nome) {
		
		return cidadesRepository.findByNome(nome);
				
	}
	
	@RequestMapping(value = "/estado/{estado}", method = RequestMethod.GET)
	public List<Cidade> buscarPorEstado(@PathVariable("estado") String estado) {
		
		return cidadesRepository.findByEstado(estado);
				
	}
	
}