package br.com.tokiomarine.seguradora.avaliacao.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//Esta anotação faz com que automaticamente o metodo chamador retorne o status do parâmetro, 
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EstudanteNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3773423597477499181L;

	public EstudanteNotFoundException(String message) {
		super(message);
	}

}
