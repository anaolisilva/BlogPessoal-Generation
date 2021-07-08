package br.generation.anaoliveira.blogpessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import br.generation.anaoliveira.blogpessoal.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {
	
	@Autowired
	private UsuarioRepository repository;
	
	@BeforeAll
	public void start () {
		Usuario usuario = new Usuario(0L, "João da Silva", "joaozinhoxx", "joaosilva@email.com", "xx1234");
		if(repository.findByEmail(usuario.getEmail()).isEmpty())
			repository.save(usuario);
		
		//Aqui usa o mesmo objeto pra ir substituindo os dados e o banco grava normal,
		//porque tá alterando o objeto e o que salva ele no banco é o método save.
		
		//Ele não aceita o Id nulo aqui porque ele não está respondendo a uma requisição http 
		//(que entenderia que o atributo id é auto-incremento e não precisa ser passado)
		//O postman faz essas requisições pra nós geralmente, mas aqui nos testes Spring estamos no modo raíz.
		//Tem como eu fazer requisição por aqui então? como fazemos na controller? Pra isso dar certo?
		
		usuario = new Usuario(0L, "João de Castro", "joaozinho02", "joaocastro@email.com", "12345");
		if(repository.findByEmail(usuario.getEmail()).isEmpty())
			repository.save(usuario);
		
		usuario = new Usuario(0L, "João de Souza", "joaozinho03", "joaosouza@email.com", "12345");
		if(repository.findByEmail(usuario.getEmail()).isEmpty())
			repository.save(usuario);
		
		usuario = new Usuario(0L, "Mônica Oliveira", "moninha", "monicaoli@email.com", "54321");
		if(repository.findByEmail(usuario.getEmail()).isEmpty())
			repository.save(usuario);
		
	}
	
	@Test
	public void findByEmailRetornaUsuario() {
		
		Optional<Usuario> usuario = repository.findByEmail("joaosilva@email.com");
		
		assertTrue(usuario.get().getEmail().equals("joaosilva@email.com")); //teste dá certo se ele encontrar o mesmo e-mail que for passado.		
		
	}
	
	@Test
	public void findAllByNomeContainingIgnoreCaseRetornaTresUsuarios() {
		List<Usuario> usuarios = repository.findAllByNomeContainingIgnoreCase("João");
		
		assertEquals(3, usuarios.size());
	}
	
	@Test
	public void findByNomeOrderByNomeRetornaUsuario() {
		Optional<Usuario> usuarioNome = repository.findFirstByNomeContainingIgnoreCaseOrderByNome("João");
		
		assertTrue(usuarioNome.get().getNome().equals("João da Silva")); //Esse no caso retornaria o primeiro nome em ordem alfabética
	}
	
	@Test
	public void findByUsuarioRetornaUsuario() {
		Optional<Usuario> usuario = repository.findByUsuario("joaozinho02");
		
		assertTrue(usuario.get().getUsuario().equals("joaozinho02"));
	}
	

}
