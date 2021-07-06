package br.generation.anaoliveira.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.generation.anaoliveira.blogpessoal.model.Usuario;
import br.generation.anaoliveira.blogpessoal.model.UsuarioLogin;
import br.generation.anaoliveira.blogpessoal.repository.UsuarioRepository;
import br.generation.anaoliveira.blogpessoal.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;	
	
	@Autowired
	private UsuarioService service;
	
	//A body é mais difícil de ser interceptada, portanto, mais segura.
	
	//Os dois métodos (/logar e /cadastrar) são aqueles que o filtro de segurança deixa passar sem o Header com o token.
	
	@PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> Authentication(@RequestBody Optional<UsuarioLogin> user){
		return service.logarUsuario(user)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> cadastrarUsuario (@RequestBody Usuario usuario){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarUsuario(usuario));
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Usuario>> getAll() {
		return ResponseEntity.ok(usuarioRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getById(@PathVariable long id){
		return usuarioRepository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());				
	}
	
	@PutMapping("/alterar")
	public ResponseEntity<Usuario> putUsuario(@RequestBody Usuario usuario){
		Optional<Usuario> updateUsuario = service.atualizarUsuario(usuario);
		try {
			return ResponseEntity.ok(updateUsuario.get());
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	

}
