package br.com.developerronie.cadastrousuario.controller;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.developerronie.cadastrousuario.config.ModelMapperConfig.CustomModelMapper;
import br.com.developerronie.cadastrousuario.data.dto.UsuarioDTO;
import br.com.developerronie.cadastrousuario.data.model.Usuario;
import br.com.developerronie.cadastrousuario.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@Autowired
	private CustomModelMapper modelMapper;

	@GetMapping("{codigo}")
	public ResponseEntity<UsuarioDTO> getById(@PathVariable String codigo) {
		return service.findByCodigo(codigo)
				.map(usuario -> ResponseEntity.ok().body(modelMapper.map(usuario, UsuarioDTO.class)))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	@Transactional
	public ResponseEntity<UsuarioDTO> insert(@RequestBody @Valid UsuarioDTO dto, UriComponentsBuilder uriBuilder) {
		Usuario usuario = service.insert(dto);
		URI uri = uriBuilder.path("/usuario/{codigo}").buildAndExpand(usuario.getCodigo()).toUri();
		return ResponseEntity.created(uri).body(modelMapper.map(usuario, UsuarioDTO.class));
	}

	@PutMapping("/{codigo}")
	@Transactional
	public ResponseEntity<UsuarioDTO> update(@PathVariable String codigo, @RequestBody @Valid UsuarioDTO dto) {
		Usuario usuario = service.update(codigo, dto);

		if (usuario != null) {
			return ResponseEntity.ok(modelMapper.map(usuario, UsuarioDTO.class));
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("{codigo}")
	@Transactional
	public void delete(@PathVariable String codigo) {
		Usuario usuario = service.findByCodigo(codigo)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		service.delete(usuario);
	}
}