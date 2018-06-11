package model.bean.master;

import java.util.List;

public class Objetivo {
	private int id;          // ID do objetivo, seguindo a tabela 2 passada no TP
	private String objetivo; // Explicação do objetivo, seguindo a tabela 2 passada no TP

	public Objetivo(int id, String objetivo) {
		this.setId(id);
		this.setObjetivo(objetivo);
	}
	
	public static Objetivo procuraObjetivoPeloId(int id , List<Objetivo> listaObjetivo) {
	/* Esta função retorna o objetivo correspondente ao Id passado por parametro,
	 * dentro da lista de objetivos passados pelo segundo parametro
	 * Retorna um objeto da tipo Objetivo caso o id seja encontrado na lista
	 * Retorna NULL caso o objetivo correspondente ao id nao seja encontrado	
	 */
		
		for (Objetivo objetivo : listaObjetivo) {
			if (objetivo.getId() == id) {
				return objetivo;
			}
		}
		return null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}
}
