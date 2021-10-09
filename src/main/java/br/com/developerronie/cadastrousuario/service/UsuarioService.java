package br.com.developerronie.cadastrousuario.service;

import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.developerronie.cadastrousuario.config.ModelMapperConfig.CustomModelMapper;
import br.com.developerronie.cadastrousuario.data.dto.UsuarioDTO;
import br.com.developerronie.cadastrousuario.data.model.Usuario;
import br.com.developerronie.cadastrousuario.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private CustomModelMapper modelMapper;

	public Optional<Usuario> findByCodigo(String codigo) {
		return repository.findById(codigo);
	}

	public Usuario insert(UsuarioDTO dto) {
		if (repository.existsById(dto.getCodigo()) || repository.existsUsuarioByNome(dto.getNome())) {
			throw new EntityExistsException("O usuário informado já existe...");
		}

		return repository.save(modelMapper.map(dto, Usuario.class));
	}

	public Usuario update(String codigo, UsuarioDTO dto) {
		Optional<Usuario> opt = repository.findById(codigo);

		if (!opt.isPresent()) {
			throw new EntityNotFoundException("Usuário não encontrado...");
		}

		Usuario usuario = opt.get();
		usuario.setDtNascimento(dto.getDtNascimento());
		usuario.setNome(dto.getNome());
		usuario.setFoto(dto.getFoto());
		return repository.save(usuario);
	}

	public void delete(Usuario usuario) {
		if (usuario == null || usuario.getCodigo() == null) {
			throw new IllegalArgumentException("O código do usuário não pode ser vazio.");
		}

		repository.delete(usuario);
	}
}
