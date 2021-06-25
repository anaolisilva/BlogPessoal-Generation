package br.generation.anaoliveira.blogpessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	
	@Autowired //Serviço de ingestão de dependências do Spring. Transfere para o Spring a responsabilidade de criar esses objetos.
	private PostagemRepository PostagemRepository; //
	
	@GetMapping
	public ResponseEntity<List<Postagem>> GetAll (){ //Pode trocar esse nome GetAll, não tem problema. Ele é personalizável.
		return ResponseEntity.ok(PostagemRepository.findAll()); // OK = 200 } 
	} //Aqui é onde de fato define o método que foi assinado lá no repositório. 
		//Isso é polimorfismo, override de métodos.
		//Esse aqui não pode trocar o nome porque é um método do próprio repository (polimorfismo)
	
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> GetById (@PathVariable long id) {
		return PostagemRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(PostagemRepository.findAllByTituloContainingIgnoreCase(titulo));
		
	}
	
	@PostMapping
	public ResponseEntity<Postagem> criarPostagem (@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(PostagemRepository.save(postagem));
	} //Devolve a repsosta 201 (criado com sucesso), por isso .status(HttpStatus.CREATED).
		//O .body(...) pega a informação do body (método que diz pra usar o body) e salva como postagem por meio do save.(objeto).
	
	
	@PutMapping
	public ResponseEntity<Postagem> alterarPostagem (@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.OK).body(PostagemRepository.save(postagem));
	}
	
	@DeleteMapping("/{id}")
	public void deleteById (@PathVariable long id) {
		PostagemRepository.deleteById(id);
	}
	
}
