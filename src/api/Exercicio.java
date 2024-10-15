package api;

import java.io.Serializable;

public class Exercicio implements Serializable{

	private static final long serialVersionUID = -4077635475943364931L;

	private String nomeExercicio;
	private int qntRepeticoes;
	private int nrSeries;

	public Exercicio(String nomeExercicio, int qntRepeticoes, int nrSeries) {
		super();
		this.nomeExercicio = nomeExercicio;
		this.qntRepeticoes = qntRepeticoes;
		this.nrSeries = nrSeries;
	}

	public String getNomeExercicio() {
		return nomeExercicio;
	}

	public void setNomeExercicio(String nomeExercicio) {
		this.nomeExercicio = nomeExercicio;
	}

	public int getQntRepeticoes() {
		return qntRepeticoes;
	}

	public void setQntRepeticoes(int qntRepeticoes) {
		this.qntRepeticoes = qntRepeticoes;
	}

	public int getNrSeries() {
		return nrSeries;
	}

	public void setNrSeries(int nrSeries) {
		this.nrSeries = nrSeries;
	}
	
	
	
	
}
