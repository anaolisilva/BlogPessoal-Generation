package br.generation.anaoliveira.blogpessoal.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.generation.anaoliveira.blogpessoal.model.Usuario;

public class UserDetailsImpl implements UserDetails {
	//O que essa classe faz? R: Pega uma classe que já existe e implementa ela (a UserDetails).
	//A User Details define as características do usuário que vai fazer o login.
	
	private static final long serialVersionUID = 1L; //O que é isso?
	
	private String userName;
	private String password; //Nesse caso tem que ser em inglês por causa dos métodos herdados da UserDetails
	
	
	public UserDetailsImpl(Usuario user) {
		super();
		this.userName = user.getUsuario();
		this.password = user.getSenha();
	}
	
	
	public UserDetailsImpl() {}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	} //Esse método, por exemplo, serve para definir os direitos de cada usuário (se é admin, se não, etc).

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	//Esses outros métodos abaixo podem fazer referências a outras características de usuário,
	//como expiração da conta, se a conta está trancada, etc.
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	

}
