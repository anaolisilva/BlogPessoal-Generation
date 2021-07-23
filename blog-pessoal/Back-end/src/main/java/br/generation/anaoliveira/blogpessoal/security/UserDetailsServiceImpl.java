package br.generation.anaoliveira.blogpessoal.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import br.generation.anaoliveira.blogpessoal.model.Usuario;
import br.generation.anaoliveira.blogpessoal.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	//Nesse caso, ela vai aplicar os detalhes dos serviços (aqui, é só o login.
	//Ela é chamada lá na Basic Security Config, por exemplo.
	//Por causa do verbo "implements", na hora de o programa buscar a classe UserDetailsService, ele vai ver que
	//ela está implementada aqui. Por isso não precisa chamar a classe específica lá no BasicSecurityConfig.
	
	@Autowired 
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername (String userName) throws UsernameNotFoundException {
		
		Optional<Usuario> usuario = usuarioRepository.findByUsuario(userName);
		
		usuario.orElseThrow(() -> new UsernameNotFoundException(userName + " not found."));

		return usuario.map(UserDetailsImpl::new).get();
		
		//Novo objeto da classe UserDetailsImpl com as informações que ele encontrou no banco (user e senha) Como se fizesse uma cópia
	}
	
	
}
