package br.com.developerronie.cadastrousuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.developerronie.cadastrousuario.data.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

	boolean existsUsuarioByNome(String nome);

}