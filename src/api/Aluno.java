package api;

import java.time.LocalDate;

public class Aluno extends Pessoa{

	private static final long serialVersionUID = 205265464267996316L;
	
	private double peso;
	private double altura;
	private String objetivoTreinamento;
	private Plano planoAluno;
	
	public Aluno(String nome, String dataNascimento, String cpf, String email, String nrTelefone, double peso,
			double altura, String objetivoTreinamento, Plano planoAluno) {
		super(nome, dataNascimento, cpf, email, nrTelefone);
		this.peso = peso;
		this.altura = altura;
		this.objetivoTreinamento = objetivoTreinamento;
		this.planoAluno = planoAluno;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public String getObjetivoTreinamento() {
		return objetivoTreinamento;
	}

	public void setObjetivoTreinamento(String objetivoTreinamento) {
		this.objetivoTreinamento = objetivoTreinamento;
	}
	
	
	
	// @Override
	// public String toString() {}
}
