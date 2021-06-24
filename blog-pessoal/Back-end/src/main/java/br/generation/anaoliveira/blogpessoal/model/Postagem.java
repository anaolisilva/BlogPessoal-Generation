package br.generation.anaoliveira.blogpessoal.model;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_postagem")
public class Postagem {
	
	@Id //Define que o atributo abaixo é uma ID, chave primária.
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Define o auto_increment com a estratégia "GenerationType.IDENTITY" pré definida.
	private long id;
	
	@NotNull //Define que não pode ser nulo
	@Size(min = 2, max = 100) //Definindo tamanho do título com mínimo e máximo
	private String titulo;
	
	@NotNull
	@Size(min = 10, max = 600)
	private String corpo;
	
	@Temporal(TemporalType.TIMESTAMP) //método pelo qual vai pegar a data da postagem (é como se fosse um 'preencher automático' também?)
	private Date data = new java.sql.Date(System.currentTimeMillis());


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getCorpo() {
		return corpo;
	}


	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}


	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}
	
	
	

}
