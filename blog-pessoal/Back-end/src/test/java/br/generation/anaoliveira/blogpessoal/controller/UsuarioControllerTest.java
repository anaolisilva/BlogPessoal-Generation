package br.generation.anaoliveira.blogpessoal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.generation.anaoliveira.blogpessoal.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioControllerTest {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	private Usuario usuario;
	private Usuario usuarioupd;
	
	@BeforeAll
	public void start () {
		//usuario = new Usuario(0L, "Márcia", "marcinha", "marcia@email.com", "123456");
		usuario = new Usuario(0L, "Amdmin", "admin", "admin@email.com", "admin123");
		usuarioupd = new Usuario(5L, "Márcia dos Santos", "marcinha", "marcia@email.com", "123456");
			
	}
	
	@Disabled
	@Test
	public void deveCadastrarNovoUsuario () {
		HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(usuario); //HttpEntity envia requisições com o corpo de objeto.
		
		ResponseEntity<Usuario> resposta = testRestTemplate.exchange("/usuarios/cadastrar", HttpMethod.POST, requisicao, Usuario.class);
		
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	
	@Test
	public void deveMostrarTodosUsuarios () {
		
		ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("marcinha", "12345").exchange("/usuarios/todos", HttpMethod.GET, null, String.class);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	
	
	@Test
	public void deveAlterarUsuario () {
		HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(usuarioupd);
		ResponseEntity<Usuario> resposta = testRestTemplate.withBasicAuth("admin", "admin123").exchange("/usuarios/alterar", HttpMethod.PUT, requisicao, Usuario.class);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	
	

}
