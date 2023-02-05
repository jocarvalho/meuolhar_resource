package br.com.meuolhar.api.resource.domain.momento.model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "momento")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Momento {

	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	  @GenericGenerator(name = "native", strategy = "native")
	  @Column(name = "ID")
	private Long id;
	  @Column(name = "titulo")
	private String titulo;
	  @Column(name = "data")
	private String data;
	  @Column(name = "descricao")
	private String descricao;
	  @Column(name = "owner")
	private String owner;

	public Momento(MomentoAdd momento) {
		setTitulo(momento.titulo());
		setData(momento.data());
		setDescricao(momento.descricao());
		setOwner(momento.owner());
	}
	
	public record MomentoDetails() {}
	public record MomentoList(Long id, String titulo, String data, String descricao, String owner) {
		public MomentoList(Momento m) {
			this(m.id, m.titulo, m.data, m.descricao, m.owner);
		}
	}
	public record MomentoUpdate() {}
	public record MomentoAdd(
			String titulo,
			String data,
			String descricao,
			String owner ) {}
}