package br.generation.anaoliveira.blogpessoal.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.generation.anaoliveira.blogpessoal.model.Usuario;
import br.generation.anaoliveira.blogpessoal.model.UsuarioLogin;
import br.generation.anaoliveira.blogpessoal.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	// Esse método criptografa a senha do usuário.
	public Usuario CadastrarUsuario(Usuario usuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String senhaEncoder = encoder.encode(usuario.getSenha());

		usuario.setSenha(senhaEncoder);

		return repository.save(usuario);
	}

	public Optional<UsuarioLogin> Logar(Optional<UsuarioLogin> user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		Optional<Usuario> usuario = repository.findByUsuario(user.get().getUsuario());

		if (usuario.isPresent()) {
			if (encoder.matches(user.get().getSenha(), usuario.get().getSenha())) { // compara senha encriptada no
																					// cadastro do usuário com a senha
																					// não encriptada

				String auth = user.get().getUsuario() + ":" + user.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth); // Define a autenticação de cabeçalho.

				user.get().setToken(authHeader);

				user.get().setNome(usuario.get().getNome()); // Essa coisa do ponto depois de ponto ainda me deixa muito
																// confusa

				return user;
			}
		}

		return null;
	}
	
	

}
