package br.com.developerronie.cadastrousuario.data.model;



import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "USUARIO")
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "CODIGO", nullable = false)
	private String codigo;
	
	@Column(name = "NOME", nullable = false)
	private String nome;
	
	@Column(name = "DT_NASCIMENTO", nullable = false)
	private LocalDate dtNascimento;
	
	//Para o caso de a foto ser um link
	@Column(name = "FOTO", nullable = true)
	private String foto;
	
//	@Column(name = "FOTO", nullable = true)
//	private Byte[] foto;
}