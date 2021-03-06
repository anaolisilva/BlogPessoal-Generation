package br.generation.anaoliveira.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.generation.anaoliveira.blogpessoal.model.Postagem;
import br.generation.anaoliveira.blogpessoal.repository.PostagemRepository;
import br.generation.anaoliveira.blogpessoal.service.PostagemService;

//Classe Controller se comunica com o cliente; é como se fosse um porteiro direcionador, 
//controla os requests que vem e as respostas que são dadas.

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*") // o que está entre parênteses é para as novas versões do Angular.
public class PostagemController {

	@Autowired // Serviço de ingestão de dependências do Spring. Transfere para o Spring a
				// responsabilidade de criar esses objetos.
	private PostagemRepository postagemRepository;

	@Autowired
	private PostagemService postagemService;

	@GetMapping
	public ResponseEntity<List<Postagem>> GetAll() { // Pode trocar esse nome GetAll, não tem problema. Ele é
														// personalizável.
		return ResponseEntity.ok(postagemRepository.findAll()); // OK = 200
	} // O verdinho claro (findAll) aqui não pode trocar o nome porque é um método do
		// próprio repository, herdado automaticamente.

	@GetMapping("/{id}")
	public ResponseEntity<Postagem> getById(@PathVariable long id) {
		return postagemRepository.findById(id).map(resp -> ResponseEntity.ok(resp)) // esse map substitui o Optional.
																					// Ele mapeia a resposta, se ela
																					// existe ou não.
				.orElse(ResponseEntity.notFound().build()); // .build é pra construir a resposta de notFound. Esse
															// .orElse funciona como um else de um if (caso o map não
															// encontre a resposta ele devolve)
	}

	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));

	}

	@GetMapping("/usuario/{usuario}")
	public ResponseEntity<List<Postagem>> getByUsuario(@PathVariable String usuario) {
		return ResponseEntity.ok(postagemRepository.findAllByUsuario_usuarioIgnoreCase(usuario));
	} // Lista todas as postagens por usuário.
	
	/*
	@GetMapping("/maiorcurtida")
	public ResponseEntity<List<Postagem>> postagemMaisCurtida () {
		return ResponseEntity.ok(postagemRepository.findAllOrderByCurtidas());
	}*/

	@PostMapping
	public ResponseEntity<Postagem> criarPostagem(@RequestBody Postagem postagem) {
		return ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagem));
	} // Devolve a repsosta 201 (criado com sucesso), por isso
		// .status(HttpStatus.CREATED).
		// O .body(...) pega a informação do body (método que diz pra usar o body) e
		// salva como postagem por meio do save.(objeto).

	@PutMapping
	public ResponseEntity<Postagem> alterarPostagem(@RequestBody Postagem postagem) {
		return ResponseEntity.status(HttpStatus.OK).body(postagemRepository.save(postagem));
	}

	@PutMapping("/curtir/{id}")
	public ResponseEntity<Postagem> curtirPostagemId(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(postagemService.curtir(id));
	}
	
	@PutMapping("/descurtir/{id}")
	public ResponseEntity<Postagem> descurtirPostagemId (@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(postagemService.descurtir(id));
	}
	

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable long id) {
		// PostagemRepository.deleteById(id); --> modo mais simples

		// Para retornar um erro mais simples pro usuário (um 404):

		Optional<Postagem> postagem = postagemRepository.findById(id);

		if (postagem.isPresent()) {
			postagemRepository.deleteById(id);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O id que você digitou não existe!");
		}

	}

}
