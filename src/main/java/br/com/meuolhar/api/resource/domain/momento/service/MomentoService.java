package br.com.meuolhar.api.resource.domain.momento.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.meuolhar.api.resource.domain.momento.model.Momento;
import br.com.meuolhar.api.resource.domain.momento.repository.MomentoRepository;

@Service
public class MomentoService {

	@Autowired
	MomentoRepository repository;
	
	public Optional<Momento> getById(Long id) {
		return repository.findById(id);
	}
	
	public Page<Momento> getByOwner(String owner, Pageable pageable){
		var momento = new Momento();
		momento.setOwner(owner);
		return repository.findAll(Example.of(momento, ExampleMatcher.matchingAll().withIgnoreCase()), pageable);
	}
	
	public Momento addMomento(Momento add) {
		return repository.saveAndFlush(add);
	}
	public Page<Momento> getAll(Pageable pageable){
		return repository.findAll(pageable);
	}
}
