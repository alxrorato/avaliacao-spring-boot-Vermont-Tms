package br.com.tokiomarine.seguradora.avaliacao.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//Esta anotação faz com que automaticamente o metodo chamador retorne o status do parâmetro, 
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EstudanteNotFoundException extends RuntimeException {

	public EstudanteNotFoundException(String message) {
		super(message);
	}

}
