package br.com.tokiomarine.seguradora.avaliacao;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import br.com.tokiomarine.seguradora.avaliacao.repository.EstudanteRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EstudanteRepositoryTest {

	@Autowired
	private EstudanteRepository estudanteRepository;
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void insertEstudante() {
		System.out.println("Dentro do metodo de teste de insert");
		Estudante estudante = new Estudante("Cesar", "cesar@gmail.com", "9111111", "22222", "Enga. Civil");
		this.estudanteRepository.save(estudante);
		System.out.println("Id do estudante: " + estudante.getId());
		Assertions.assertThat(estudante.getId()).isNotNull();
		Assertions.assertThat(estudante.getNome()).isEqualTo("Cesar");
		Assertions.assertThat(estudante.getEmail()).isEqualTo("cesar@gmail.com");
		Assertions.assertThat(estudante.getTelefone()).isEqualTo("9111111");
		Assertions.assertThat(estudante.getMatricula()).isEqualTo("22222");
		Assertions.assertThat(estudante.getCurso()).isEqualTo("Enga. Civil");
	}

	@Test
	public void deleteEstudante() {
		System.out.println("Dentro do metodo de teste de delete");
		Estudante estudante = new Estudante("Bruna", "bruna@gmail.com", "111111", "222222", "Direito");
		this.estudanteRepository.save(estudante);
		estudanteRepository.delete(estudante);
		Assertions.assertThat(estudanteRepository.findById(estudante.getId())).isEmpty();
	}	
	
	@Test
	public void updateData() {
		System.out.println("Dentro do metodo de teste de update");
		//Cria o estudante
		Estudante estudante = new Estudante("Sergio", "sergio@gmail.com", "111111", "222222", "Direito");
		this.estudanteRepository.save(estudante);
		//Altera o estudante
		estudante.setNome("Sergio da Silva");
		estudante.setEmail("sergio01@ig.com.br");
		estudante.setTelefone("888888");
		estudante.setMatricula("999999");
		estudante.setCurso("Nutricao");
		this.estudanteRepository.save(estudante);
		Optional<Estudante> estudanteUpd = this.estudanteRepository.findById(estudante.getId());
		Assertions.assertThat(estudanteUpd.get().getNome()).isEqualTo("Sergio da Silva");
		Assertions.assertThat(estudanteUpd.get().getEmail()).isEqualTo("sergio01@ig.com.br");
		Assertions.assertThat(estudanteUpd.get().getTelefone()).isEqualTo("888888");
		Assertions.assertThat(estudanteUpd.get().getMatricula()).isEqualTo("999999");
		Assertions.assertThat(estudanteUpd.get().getCurso()).isEqualTo("Nutricao");
	}	

	/*
	 * Testa a busca pelo nome exato com case sensitive
	 */
	@Test
	public void findByNomeExatoTeste() {
		System.out.println("Dentro do metodo de teste findByNomeIgnoreCaseContainingTeste");
		Estudante estudante = new Estudante("JOSE da Silva Jr.", "ze@gmail.com", "111111", "222222", "Direito");
		Estudante estudante2 = new Estudante("Jose da Silva", "ze02@gmail.com", "3333", "3333", "Engenharia");
		Estudante estudante3 = new Estudante("JOSE da Silva", "ze03@gmail.com", "3333", "3333", "Engenharia");
		this.estudanteRepository.save(estudante);
		this.estudanteRepository.save(estudante2);
		this.estudanteRepository.save(estudante3);
		List<Estudante> estudanteList = estudanteRepository.findByNome("Jose da Silva");
		Assertions.assertThat(estudanteList.size()).isEqualTo(1);
	}	
	
	/*
	 * Testa o no case sensitive para o nome do estudante.
	 * O método findByNomeIgnoreCaseContaining deverá retornar 2 registros na lista. 
	 */
	@Test
	public void findByNomeIgnoreCaseContainingTeste() {
		System.out.println("Dentro do metodo de teste findByNomeIgnoreCaseContainingTeste");
		Estudante estudante = new Estudante("RICARDO da Silva", "ricardo@gmail.com", "111111", "222222", "Direito");
		Estudante estudante2 = new Estudante("Ricardo Pereira", "ricardo02@gmail.com", "3333", "3333", "Engenharia");
		this.estudanteRepository.save(estudante);
		this.estudanteRepository.save(estudante2);
		List<Estudante> estudanteList = estudanteRepository.findByNomeIgnoreCaseContaining("ricardo");
		Assertions.assertThat(estudanteList.size()).isEqualTo(2);
	}	
	
	
	@Test
	public void insertSeNomeNullGeraException() {
		thrown.expect(ConstraintViolationException.class);
		//Instancia o objeto sem o nome do estudante
		Estudante estudante = new Estudante();
		estudante.setEmail("alexandre@gmail.com");
		estudante.setTelefone("123456");
		estudante.setMatricula("3333333");
		estudante.setCurso("Ciencia da Computacao");
		this.estudanteRepository.save(estudante);
	}
	
}
