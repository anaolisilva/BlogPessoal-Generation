package br.generation.anaoliveira.blogpessoal.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.generation.anaoliveira.blogpessoal.model.Usuario;
import br.generation.anaoliveira.blogpessoal.model.UsuarioLogin;
import br.generation.anaoliveira.blogpessoal.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	// Esse método criptografa a senha do usuário no cadastro.

	public Usuario cadastrarUsuario(Usuario usuario) {
		
		if(repository.findByUsuario(usuario.getUsuario()).isPresent())
			throw new ResponseStatusException(
				HttpStatus.BAD_REQUEST, "Usuário já existe!", null);
		
		if(repository.findByEmail(usuario.getEmail()).isPresent())
			throw new ResponseStatusException(
				HttpStatus.BAD_REQUEST, "E-mail já existe!", null);
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String senhaEncoder = encoder.encode(usuario.getSenha());

		usuario.setSenha(senhaEncoder);

		return repository.save(usuario);
	}

	public Optional<UsuarioLogin> logarUsuario (Optional<UsuarioLogin> user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		Optional<Usuario> usuario = repository.findByUsuario(user.get().getUsuario());

		if (usuario.isPresent()) {
			if (encoder.matches(user.get().getSenha(), usuario.get().getSenha())) { // compara senha encriptada no cadastro do usuário com a senha não encriptada

				String auth = user.get().getUsuario() + ":" + user.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth); // Define a autenticação de cabeçalho.

				user.get().setToken(authHeader);

				user.get().setNome(usuario.get().getNome()); // Essa coisa do ponto depois de ponto ainda me deixa muito confusa. Pelo que entendi esse primeiro .get() vem do Optional. (confirmar)
				//É isso mesmo, tem a ver com o instanciamento do objeto. Como ele é Optional, primeiro ele precisa verificar se o objeto existe e quais são os valores dele (por meio do.get()).
				user.get().setId(usuario.get().getId());
				user.get().setFoto(usuario.get().getFoto());
				user.get().setTipo(usuario.get().getTipo());
				user.get().setUsuario(usuario.get().getUsuario());
				user.get().setEmail(usuario.get().getEmail());
				return user;
			}
		}

		return null;
	}
	
	public Optional<Usuario> atualizarUsuario(Usuario usuario){
		
		if(repository.findById(usuario.getId()).isPresent()) {
					
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			String senhaEncoder = encoder.encode(usuario.getSenha());
			usuario.setSenha(senhaEncoder);
			
			return Optional.of(repository.save(usuario));
		
		}else {
			
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "Usuário não encontrado!", null);
			
		}
		
	}
	
	

}
