package br.com.meuolhar.api.resource.domain.momento.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.meuolhar.api.resource.domain.momento.model.Momento;
import br.com.meuolhar.api.resource.domain.momento.model.Momento.MomentoAdd;
import br.com.meuolhar.api.resource.domain.momento.model.Momento.MomentoList;
import br.com.meuolhar.api.resource.domain.momento.service.MomentoService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/momentos")
@Slf4j
public class MomentoController {
	@Autowired
	MomentoService service;
	
	/*
	 * @GetMapping("/{id}") public ResponseEntity<Momento> getMomento(@PathVariable
	 * Long id){ return ResponseEntity.of(service.getById(id)); }
	 */
	
	@GetMapping("/{owner}")
	public Page<MomentoList> getByOwner(
			@PathVariable String owner, 
			@PageableDefault(sort = {"data"}, size = 5, page = 0) Pageable pageable ){
			log.info("Value for owner: "+owner);
		return service.getByOwner(owner, pageable).map(MomentoList::new);
	}
	
	@GetMapping
	public Page<MomentoList> getAll( 
			@PageableDefault(sort = {"data"}, size = 5, page = 0) Pageable pageable ){
		return service.getAll(pageable).map(MomentoList::new);
	}
	
	@PostMapping
	public ResponseEntity<Momento> addMomento(
			@RequestBody MomentoAdd momento, 
			UriComponentsBuilder builder){
		var add = service.addMomento(new Momento(momento));
		URI uri = builder.path("/momentos/{id}").buildAndExpand(add.getId()).toUri();
		return ResponseEntity.created(uri).body(add);
	}
}
