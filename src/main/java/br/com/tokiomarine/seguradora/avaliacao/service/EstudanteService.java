package br.com.tokiomarine.seguradora.avaliacao.service;

import java.util.List;

import javax.validation.Valid;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;

public interface EstudanteService {

	List<Estudante> buscarEstudantes();

	Estudante cadastrarEstudante(@Valid Estudante estudante);

	Estudante buscarEstudante(long id);
	
	List<Estudante> buscarEstudantesByNomeExato(String nome);
	
	List<Estudante> buscarEstudantesByContainsNome(String nome);

	void atualizarEstudante(@Valid Estudante estudante);
	
	void apagarEstudante(Long id);
}
