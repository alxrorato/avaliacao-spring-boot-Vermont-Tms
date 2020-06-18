package br.com.tokiomarine.seguradora.avaliacao.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
public class Estudante {
	// TODO Implementar a entidade Estudante conforme solicitado
	/*
	 * OBS.: por conta própria utilizei as anotações @NotBlank ao invés de @NotNull (solicitado no documento),
	 *       porque o @NotNull deixa cadastrar valores em branco e o @NotBlank valida contra null, vazios e 
	 *       obriga o campo a ter pelo menos 1 caracter não branco.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Nome é obrigatório")
	private String nome;

	@NotBlank(message = "Email é obrigatório")
	@Email(message = "Email inválido")
	private String email;

	private String telefone;

	@NotBlank(message = "Matrícula é obrigatória")
	private String matricula;

	private String curso;

	public Estudante() {
	}

	public Estudante(Long id, @NotBlank(message = "Nome é obrigatório") String nome,
			@NotBlank(message = "Email é obrigatório") String email, String telefone,
			@NotBlank(message = "Matrícula é obrigatória") String matricula, String curso) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.matricula = matricula;
		this.curso = curso;
	}

	public Estudante(@NotBlank(message = "Nome é obrigatório") String nome,
			@NotBlank(message = "Email é obrigatório") @Email(message = "Email inválido") String email, String telefone,
			@NotBlank(message = "Matrícula é obrigatória") String matricula, String curso) {
		super();
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.matricula = matricula;
		this.curso = curso;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}
	
}
