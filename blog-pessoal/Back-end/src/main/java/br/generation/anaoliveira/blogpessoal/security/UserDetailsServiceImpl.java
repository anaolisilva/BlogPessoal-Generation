package br.generation.anaoliveira.blogpessoal.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import br.generation.anaoliveira.blogpessoal.model.Usuario;
import br.generation.anaoliveira.blogpessoal.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
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
