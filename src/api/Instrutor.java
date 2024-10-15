package api;

import java.time.LocalDate;

public class Instrutor extends Pessoa{

	private static final long serialVersionUID = -6911015873360191299L;

	private String areaDeAtuacao;

	public Instrutor(String nome, String dataNascimento, String cpf, String email, String nrTelefone,
			String areaDeAtuacao) {
		super(nome, dataNascimento, cpf, email, nrTelefone);
		this.areaDeAtuacao = areaDeAtuacao;
	}

	public String getAreaDeAtuacao() {
		return areaDeAtuacao;
	}

	public void setAreaDeAtuacao(String areaDeAtuacao) {
		this.areaDeAtuacao = areaDeAtuacao;
	}
	
	
}
