package br.com.tokiomarine.seguradora.avaliacao.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EstudanteInvalidIdException extends RuntimeException {

	public EstudanteInvalidIdException(String message) {
		super(message);
	}


}
