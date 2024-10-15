package api;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ControlePresenca implements Serializable{

	private static final long serialVersionUID = 5161903888667361983L;

	private String dataRegistro;
	private List<Aluno> alunosPresentes;

	public ControlePresenca(String dataRegistro, List<Aluno> alunosPresentes) {
		super();
		this.dataRegistro = dataRegistro;
		this.alunosPresentes = alunosPresentes;
	}

	public String getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(String dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public List<Aluno> getAlunosPresentes() {
		return alunosPresentes;
	}

	public void setAlunosPresentes(List<Aluno> alunosPresentes) {
		this.alunosPresentes = alunosPresentes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public List<String> getNomesAlunosPresentes(){
		return this.alunosPresentes.stream().map(e -> e.getNome()).collect(Collectors.toList());
	}
	
}
