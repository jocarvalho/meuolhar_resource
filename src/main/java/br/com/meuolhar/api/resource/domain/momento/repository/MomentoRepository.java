package br.com.meuolhar.api.resource.domain.momento.repository;

import java.util.Collection;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.meuolhar.api.resource.domain.momento.model.Momento;

public interface MomentoRepository extends JpaRepository<Momento, Long>{
	
	Momento getById(Long id);
	Collection<Momento> getByOwner(String owner);
	Page<Momento> findAllByOwner(Example<Momento> of, Pageable pageable);
	
}
