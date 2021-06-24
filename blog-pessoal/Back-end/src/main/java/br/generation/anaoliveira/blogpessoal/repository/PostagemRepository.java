package br.generation.anaoliveira.blogpessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.generation.anaoliveira.blogpessoal.model.Postagem;

//Repository é responsável pela comunicação com o banco de dados.
//Faz métodos de busca, de salvar, etc (CRUD).

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> { //Extende a interface pré-definida JpaRepository<NomeDaClasseModel, TipoChavePrimária>
	
	public List<Postagem> findAll(); //Assinatura de método de busca - no caso, para listar todos, sem parâmetros de busca de fato.
									//Porque é uma *interface*. 

}
