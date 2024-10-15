package api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Plano implements Serializable{

	private static final long serialVersionUID = 879164486254408060L;

	private String nomePlano;
	private double precoPlano;
	private String descricao;
	private List<Exercicio> exercicios = new ArrayList<Exercicio>();
	
	public Plano(String nomePlano, double precoPlano, String descricao, List<Exercicio> exercicios) {
		super();
		this.nomePlano = nomePlano;
		this.precoPlano = precoPlano;
		this.descricao = descricao;
		this.exercicios = exercicios;
	}

	public String getNomePlano() {
		return nomePlano;
	}

	public void setNomePlano(String nomePlano) {
		this.nomePlano = nomePlano;
	}

	public double getPrecoPlano() {
		return precoPlano;
	}

	public void setPrecoPlano(double precoPlano) {
		this.precoPlano = precoPlano;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Exercicio> getExercicios() {
		return exercicios;
	}

	public void setExercicios(List<Exercicio> exercicios) {
		this.exercicios = exercicios;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
