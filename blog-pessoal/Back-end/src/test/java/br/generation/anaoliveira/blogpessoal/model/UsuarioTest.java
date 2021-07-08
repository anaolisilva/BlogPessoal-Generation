package br.generation.anaoliveira.blogpessoal.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UsuarioTest {

	public Usuario usuario;
	public Usuario usuarioErro;

	@Autowired
	private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	// Classe Validator é responsável pela Validação dos atributos (segundo as
	// anotações que colocamos na Model).
	// Cuidado na hora de importar as bibliotecas certas.
	// Pesquisar o que significa esse final.

	@BeforeEach // Antes de cada teste, instanciar um objeto do tipo usuário com os seguintes
				// dados:
	public void start() {
		usuario = new Usuario(null, "João", "joaozinhoxx", "xxjoao@email.com", "xx1234"); 
		//O teste da Model não se usa o banco de dados. Por isso o nulo funciona.
		usuarioErro = new Usuario();
	}

	@Test
	public void testValidationAtributos() {

		usuario.setNome("João da Silva");
		usuario.setUsuario("joaozinho00");
		usuario.setEmail("joao@email.com");
		usuario.setSenha("12345"); // Muda os atributos dados anteriormente para verificar se as funções set estão
									// funcionando.
		// Nesse caso ele não criptografa a senha como no postman, né? No front a gente
		// garante que isso vai acontecer?

		// Armazena lista de mensagens de erro de validação do teste da model.
		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

		// imprime as mensagens de erro, caso existam.
		System.out.println(violations.toString());

		assertTrue(violations.isEmpty());

	}

	@Test
	public void testValidationAtributosNull() {

		usuarioErro.setEmail("aloaloriodejaneiro@aqueleabraco.com");

		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

		System.out.println(violations.toString());

		assertFalse(violations.isEmpty()); //Teste oposto ao anterior.

	}

}
