package br.com.tokiomarine.seguradora.avaliacao.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import br.com.tokiomarine.seguradora.avaliacao.service.EstudanteService;

//TODO não esquecer de usar as anotações para criação do restcontroller

@RestController
@RequestMapping("/estudantes")
public class EstudanteRestController {

	// TODO caso você não conheça THEMELEAF faça a implementação dos métodos em forma de RESTCONTROLLER (seguindo o padrão RESTFUL)

	@Autowired
	private EstudanteService estudanteService;

	// TODO IMPLEMENTAR CADASTRO DE ESTUDANTES (POST)
	@PostMapping("add")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<?> adicionarEstudante(@Valid @RequestBody Estudante estudante) {
		return new ResponseEntity<>(estudanteService.cadastrarEstudante(estudante), HttpStatus.CREATED);
	}

	// TODO IMPLEMENTAR ATUALIZACAO DE ESTUDANTES (PUT)
	@PutMapping("atualizar")
	public ResponseEntity<?> atualizarEstudante(@Valid @RequestBody Estudante estudante) {
		estudanteService.atualizarEstudante(estudante);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// TODO IMPLEMENTAR A LISTAGEM DE ESTUDANTES (GET)

	/* Lista todos os estudantes */
	@GetMapping("listar")
	public ResponseEntity<?> listarEstudantes() {
		List<Estudante> estudantes = estudanteService.buscarEstudantes();
		return new ResponseEntity<>(estudantes, HttpStatus.OK);	
	}

	/* Retorna 1 estudante pelo ID informado */ 
	@GetMapping(path = "buscarPorId/{id}")
	public ResponseEntity<?> buscarEstudanteById(@PathVariable("id") Long id) {
		Estudante estudante = estudanteService.buscarEstudante(id);
		return new ResponseEntity<>(estudante, HttpStatus.OK);
	}
	
	/* Retorna os estudantes que possuem determinado nome.
	 * Considera o nome exato, inclusive letras maiúsculas e minúsculas.
	 */
	@GetMapping(path = "buscarPorNomeExato/{nome}")
	public ResponseEntity<?> buscarEstudantesByNomeExato(@PathVariable String nome) {
		return new ResponseEntity<>(estudanteService.buscarEstudantesByNomeExato(nome), HttpStatus.OK);
	}
	
	/* Retorna os estudantes que possuem parte do nome informado desconsiderando
	 * letras maiúsculas e minúsculas.
	 */
	@GetMapping(path = "buscarPorNome/{nome}")
	public ResponseEntity<?> buscarEstudantesByContainsNome(@PathVariable String nome) {
		return new ResponseEntity<>(estudanteService.buscarEstudantesByContainsNome(nome), HttpStatus.OK);
	}

	// TODO IMPLEMENTAR A EXCLUSÃO DE ESTUDANTES (DELETE)
	@DeleteMapping("apagar/{id}")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<?> apagarEstudante(@PathVariable("id") Long id) {
		estudanteService.apagarEstudante(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
