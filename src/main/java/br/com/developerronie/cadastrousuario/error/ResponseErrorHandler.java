package br.com.developerronie.cadastrousuario.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.developerronie.cadastrousuario.data.dto.ErrorMessageDTO;

public class ResponseErrorHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ex.printStackTrace();
		ErrorMessageDTO errorMessage = new ErrorMessageDTO(status, ex.getMessage());

		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errorMessage.getErros().add(error.getField() + ": " + error.getDefaultMessage());
		}

		return handleExceptionInternal(ex, errorMessage, headers, errorMessage.getStatus(), request);
	}

	@ExceptionHandler({ IllegalArgumentException.class })
	public final ResponseEntity<Object> handleUnauthorizedExceptions(Exception ex, WebRequest request) {
		ex.printStackTrace();
		ErrorMessageDTO errorMessage = new ErrorMessageDTO(HttpStatus.BAD_REQUEST, ex.getMessage());
		return handleExceptionInternal(ex, errorMessage, new HttpHeaders(), errorMessage.getStatus(), request);
	}

	@ExceptionHandler({ ResponseStatusException.class })
	public final ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex, WebRequest request) {
		ex.printStackTrace();
		ErrorMessageDTO errorMessage = new ErrorMessageDTO(ex.getStatus(), ex.getMessage());
		return handleExceptionInternal(ex, errorMessage, new HttpHeaders(), errorMessage.getStatus(), request);
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleOthers(Exception ex, WebRequest request) {
		ex.printStackTrace();
		ErrorMessageDTO errorMessage = new ErrorMessageDTO(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		return handleExceptionInternal(ex, errorMessage, new HttpHeaders(), errorMessage.getStatus(), request);
	}
}
