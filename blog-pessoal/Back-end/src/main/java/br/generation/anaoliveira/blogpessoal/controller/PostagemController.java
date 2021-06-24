package br.generation.anaoliveira.blogpessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.generation.anaoliveira.blogpessoal.model.Postagem;
import br.generation.anaoliveira.blogpessoal.repository.PostagemRepository;

//Classe Controller se comunica com o cliente, é como se fosse um porteiro direcionador, 
//controla os requests que vem e as respostas que são dadas.

@RestController
@RequestMapping("/postagens")
@CrossOrigin("*")
public class PostagemController {
	
	@Autowired //Serviço de ingestão de dependências do Spring
	private PostagemRepository PostagemRepository; //
	
	@GetMapping
	public ResponseEntity<List<Postagem>> GetAll (){ 
		return ResponseEntity.ok(PostagemRepository.findAll()); // OK = 200 } 
	} //Aqui é onde de fato define o método que foi assinado lá no repositório. 
		//Isso é polimorfismo, override de métodos.
	
}
