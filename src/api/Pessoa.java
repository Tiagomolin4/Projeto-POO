package api;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;

public abstract class Pessoa implements Serializable{

	private static final long serialVersionUID = -2911293833123735615L;
	
	private String nome;
	private LocalDate dataNascimento;
	private String cpf;
	private String email;
	private String nrTelefone;

	public Pessoa(String nome, String dataNascimento, String cpf, String email, String nrTelefone) {
		super();
		this.setNome(nome);
		this.setDataNascimento(dataNascimento);;
		this.setCpf(cpf);
		this.email = email;
		this.setNrTelefone(nrTelefone);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (nome.length() < 5) {
			throw new IllegalArgumentException("Nome inválido");
		}
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		try {			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate dataFormatada = LocalDate.parse(dataNascimento, formatter);
			this.dataNascimento = dataFormatada;
		} catch (Exception e) {
			throw new IllegalArgumentException("Data inválida");
		}
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		if (cpf.length() < 10) {
			throw new IllegalArgumentException("Cpf inválido");
		}
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNrTelefone() {
		return nrTelefone;
	}

	public void setNrTelefone(String nrTelefone) {
		if (nrTelefone.length() < 5) {
			throw new IllegalArgumentException("Numero de telefone inválido");
		}
		this.nrTelefone = nrTelefone;
	}
	
	
	// public abstract String descreverInformacoes();
}
