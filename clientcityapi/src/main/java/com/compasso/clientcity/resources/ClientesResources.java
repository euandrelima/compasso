package com.compasso.clientcity.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.compasso.clientcity.domain.Cliente;
import com.compasso.clientcity.repository.ClientesRepository;

@RestController
@RequestMapping("/clientes")
public class ClientesResources {

	@Autowired
	private ClientesRepository clientesRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Cliente> listar() {
		return clientesRepository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@RequestBody Cliente cliente) {
		
		clientesRepository.save(cliente);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(cliente.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		
		try {
			clientesRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(value = "/{id}/{nome}", method = RequestMethod.PUT)
	public void atualizarNome(@PathVariable("id") Long id, 
			                  @PathVariable("nome") String nome) {
		
		//cliente.setId(id);
		clientesRepository.updateName(nome, id);
		//clientesRepository.save(cliente);
		
	}
	
	@RequestMapping(value = "/nome/{nome}", method = RequestMethod.GET)
	public Cliente buscarPorNome(@PathVariable("nome") String nome) {
		
		return clientesRepository.findByNome(nome);
				
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> consultarPorId(@PathVariable("id") Long id) {
		
		Optional<Cliente> cliente = clientesRepository.findById(id);
		
		if(!cliente.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(cliente);
		
	}
}