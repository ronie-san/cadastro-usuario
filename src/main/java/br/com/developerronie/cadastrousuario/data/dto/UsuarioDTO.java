package br.com.developerronie.cadastrousuario.data.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	

	private String codigo;
	
	private String nome;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate dtNascimento;
	
	private String foto;

}