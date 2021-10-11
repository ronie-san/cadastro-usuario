package br.com.developerronie.cadastrousuario.service;

//import java.io.IOException;
//import java.util.Arrays;
//import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

//import org.apache.tomcat.util.http.fileupload.impl.InvalidContentTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;

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

//	private Byte[] toBytes(byte[] byteArray) {
//		int i = 0;
//
//		Byte[] result = new Byte[byteArray.length];
//
//		for (byte b : byteArray) {
//			result[i++] = b;
//		}
//
//		return result;
//	}
	
//	public static byte[] toByteArray(Byte[] bytes) {
//		byte[] byteArray = new byte[bytes.length];
//		int i = 0;
//
//		for (Byte b : bytes) {
//			byteArray[i++] = b.byteValue();
//		}
//
//		return byteArray;
//	}

	public Optional<Usuario> findByCodigo(String codigo) {
		return repository.findById(codigo);
	}
	
//	public byte[] getFotoByCodigo(String codigo) {
//		Optional<Usuario> opt = findByCodigo(codigo);
//		
//		if (!opt.isPresent()) {
//			throw new EntityNotFoundException("Usuário não encontrado...");
//		}
//		
//		return toByteArray(opt.get().getFoto());
//	}

	public Usuario insert(UsuarioDTO dto/*, MultipartFile[] uploadfile*/) {
		if (repository.existsById(dto.getCodigo()) || repository.existsUsuarioByNome(dto.getNome())) {
			throw new EntityExistsException("O usuário informado já existe...");
		}

		//List<String> contentTypesUpload = Arrays.asList("image/jpeg", "image/png", "image/bmp");
		Usuario usuario = modelMapper.map(dto, Usuario.class);

		/*
		if (uploadfile.length == 1) {
			String fileContentType = uploadfile[0].getContentType();
			if (contentTypesUpload.contains(fileContentType.toLowerCase())) {
				try {
					usuario.setFoto(toBytes(uploadfile[0].getBytes()));
				} catch (IOException e) {
					throw new Exception("Erro ao salvar arquivo do upload '" + uploadfile[0].getOriginalFilename()
							+ "': " + e.getMessage());
				}
			} else {
				throw new InvalidContentTypeException(
						"Arquivo de upload '" + uploadfile[0].getOriginalFilename() + "' inválido:");
			}
		}
		*/

		return repository.save(usuario);
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
