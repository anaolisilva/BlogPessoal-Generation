package br.generation.anaoliveira.blogpessoal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.generation.anaoliveira.blogpessoal.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	public Optional<Usuario> findByEmail(String email);
	public Optional<Usuario> findByUsuario(String usuario);
	public Optional<Usuario> findFirstByNomeContainingIgnoreCaseOrderByNome(String nome);
	public List<Usuario> findAllByNomeContainingIgnoreCase(String nome);

}
