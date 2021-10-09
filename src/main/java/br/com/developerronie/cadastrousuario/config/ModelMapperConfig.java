package br.com.developerronie.cadastrousuario.config;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.developerronie.cadastrousuario.data.dto.UsuarioDTO;
import br.com.developerronie.cadastrousuario.data.model.Usuario;

@Configuration
public class ModelMapperConfig {
	
	public class CustomModelMapper extends ModelMapper {
		public <T, R> List<R> mapList(List<T> source, Class<R> targetClass) {
			return source.stream().map(element -> map(element, targetClass)).collect(Collectors.toList());
		}
	}

	@Bean
	public CustomModelMapper modelMapper() {
		CustomModelMapper mp = new CustomModelMapper();
		mp.getConfiguration().setPropertyCondition(Conditions.isNotNull());
		
		mp.createTypeMap(UsuarioDTO.class, Usuario.class);
		return mp;
	}
}
