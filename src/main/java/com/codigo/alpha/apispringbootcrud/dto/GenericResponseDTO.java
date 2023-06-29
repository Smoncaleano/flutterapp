package com.codigo.alpha.apispringbootcrud.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data

@AllArgsConstructor
@NoArgsConstructor

public class GenericResponseDTO {

	private Object data;
	private boolean success;
	private String message;
	private HttpStatus status;
	private String title = "";

	
	public GenericResponseDTO(Object data, boolean success, String message, HttpStatus status) {
		this.data = data;
		this.success = success;
		this.message = message;
		this.status = status;
		this.title = success ? "" : "¡Oops! Ocurrió un error";
	}

	
}
