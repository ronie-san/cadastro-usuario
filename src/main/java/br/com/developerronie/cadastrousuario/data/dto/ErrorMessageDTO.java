package br.com.developerronie.cadastrousuario.data.dto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class ErrorMessageDTO  {

	@JsonIgnore
	private HttpStatus status;
	private int codigo;
	private String mensagem;
	private LocalDateTime dt = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
	private List<String> erros = new ArrayList<>();

	public ErrorMessageDTO(HttpStatus status, String mensagem) {
		this.status = status;
		this.codigo = status.value();
		this.mensagem = mensagem;
	}
}
