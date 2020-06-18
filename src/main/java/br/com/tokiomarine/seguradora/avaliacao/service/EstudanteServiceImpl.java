package br.com.tokiomarine.seguradora.avaliacao.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import br.com.tokiomarine.seguradora.avaliacao.error.EstudanteInvalidIdException;
import br.com.tokiomarine.seguradora.avaliacao.error.EstudanteNotFoundException;
import br.com.tokiomarine.seguradora.avaliacao.repository.EstudanteRepository;

// TODO Efetue a implementação dos métodos da classe service
@Service
public class EstudanteServiceImpl implements EstudanteService {

	@Autowired
	private EstudanteRepository estudanteDAO;

	@Override
	/*
	public void cadastrarEstudante(@Valid Estudante estudante) {

	}
	*/
	public Estudante cadastrarEstudante(@Valid Estudante estudante) {
		return estudanteDAO.save(estudante);
	}
	
	@Override
	public void atualizarEstudante(@Valid Estudante estudante) {
		verificaSeEstudanteExiste(estudante.getId());
		estudanteDAO.save(estudante);
	}

	@Override
	public List<Estudante> buscarEstudantes() {
		Iterable<Estudante> it = estudanteDAO.findAll();

		List<Estudante> estudantes = new ArrayList<Estudante>();

		for (Estudante e : it) {
			estudantes.add(e);
		}

		return estudantes;
	}

	@Override
	public Estudante buscarEstudante(long id) {
		//throw new IllegalArgumentException("Identificador inválido:" + id);
		verificaSeEstudanteExiste(id);
		return estudanteDAO.findById(id).get();
	}

	@Override
	public List<Estudante> buscarEstudantesByNomeExato(String nome) {
		return estudanteDAO.findByNome(nome);
	}
	
	@Override
	public List<Estudante> buscarEstudantesByContainsNome(String nome) {
		return estudanteDAO.findByNomeIgnoreCaseContaining(nome);
	}

	@Override
	public void apagarEstudante(Long id) {
		verificaSeEstudanteExiste(id);
		estudanteDAO.deleteById(id);
		
	}

	private boolean estudanteExiste(Long id) {
		return(estudanteDAO.findById(id).isPresent());
	}

	private void verificaSeEstudanteExiste(Long id) {
		if (id <= 0) {
			throw new EstudanteInvalidIdException("Identificador inválido:" + id);
		}
		if (!estudanteExiste(id))
			throw new EstudanteNotFoundException("Estudante não encontrado para o ID: " + id);
	}
	
}
