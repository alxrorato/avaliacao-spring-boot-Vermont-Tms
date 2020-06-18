package br.com.tokiomarine.seguradora.avaliacao;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import br.com.tokiomarine.seguradora.avaliacao.repository.EstudanteRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class EstudanteRestControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;
	@LocalServerPort
	private int port;
	
	@MockBean
	private EstudanteRepository estudanteRepository;
	
	/*
	 * Setup p/ que essas linhas sejam sempre executadas antes de cada método de teste
	 */
	@Before
	public void Setup() {
		Optional<Estudante> estudanteOpt = 
				Optional.of(new Estudante(1L, "Joao da Silva", "joao@gmail.com", "9111111", "22222", "Enga. Civil"));
		BDDMockito.when(estudanteRepository.findById(estudanteOpt.get().getId())).thenReturn(estudanteOpt);
	}	
	
	@Test
	public void getEstudantesByIdWhenExisteReturn200() {
		ResponseEntity<Estudante> response = 
				restTemplate.getForEntity("/estudantes/buscarPorId/{id}", Estudante.class, 1L);
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	public void getEstudantesByIdWhenNaoExisteReturn404() {
		ResponseEntity<Estudante> response = 
				restTemplate.getForEntity("/estudantes/buscarPorId/{id}", Estudante.class, 99L);
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}
	
	@Test
	public void deleteEstudanteExistenteReturn200() {
		BDDMockito.doNothing().when(estudanteRepository).deleteById(1L);
		ResponseEntity<String> exchange = 
				restTemplate.exchange("/estudantes/apagar/{id}", HttpMethod.DELETE, null, String.class, 1L);
		Assertions.assertThat(exchange.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	public void deleteWhenEstudanteNaoExisteReturn404() {
		BDDMockito.doNothing().when(estudanteRepository).deleteById(1L);
		ResponseEntity<String> exchange	= 
				restTemplate.exchange("/estudantes/apagar/{id}", HttpMethod.DELETE, null, String.class, 99999L);
		Assertions.assertThat(exchange.getStatusCodeValue()).isEqualTo(404);
	}
	
	@Test
	public void createWhenNomeEstudanteIsNullReturn400BadRequest() throws Exception {
		Estudante estudante = new Estudante(9L, null, "vitoria@gmail.com", "9111111", "22222", "Informatica");
		BDDMockito.when(estudanteRepository.save(estudante)).thenReturn(estudante);
		ResponseEntity<String> response = restTemplate.postForEntity("/estudantes/add/", estudante, String.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(400);
		Assertions.assertThat(response.getBody()).contains("message", "Nome é obrigatório");
		
	}
}
