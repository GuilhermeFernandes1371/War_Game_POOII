package model.bean.master;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import model.bean.militares.Militar;
import model.bean.mundo.Territorio;

public class Jogador {
	private int id;                                    // Usado para localizar no BDA
	private String nome;                               // Nome do Jogador
	private Color cor;                                 // Cor do exercito
	private Color corComplementar;                     // Cor que complementa a cor do exercito
	private Objetivo objetivo;                         // Objetivo do Jogador
	private List<Militar> listaMilitaresSemTerritorio; // Lista de militares que o jogador ganhou,
	                                                   // mas ainda estao aguardando para serem posicionadas
	                                                   // a um territorio no inicio da rodada
	private List<Territorio> listaTerritorios;         // Lista de territorios que o jogador possui
	
	public Jogador (String nome , Color cor , Objetivo objetivo) {
		this.setId(0);
		this.listaMilitaresSemTerritorio = new ArrayList<>();
		this.listaTerritorios = new ArrayList<>();
		this.setNome(nome);
		this.setCor(cor);
		this.setObjetivo(objetivo);
		this.corComplementar = this.descobreCorComplementar();
	}
	
	public boolean verificaPosseTerritorio(Territorio territorio) {
	/* Passa por parametro um objeto do tipo Territorio
	 * Retorna um boolean
	 * True  - Jogador possui o territorio em sua lista de territorios
	 * False - Jogador nao possui o territorio em sua lista de territorios
	 */
		for (Territorio t : this.listaTerritorios) {
			if (t.equals(territorio)) {
				return true;
			}
		}
		return false;
	}
	
	public void addMilitaresSemTerritorio(List<Militar> listaMilitares) {
		for (Militar militar : listaMilitares) {
			this.listaMilitaresSemTerritorio.add(militar);
		}
	}

	public List<Militar> getListaMilitaresSemTerritorio() {
		return listaMilitaresSemTerritorio;
	}

	public String getNome() {
		return nome;
	}

	public Color getCor() {
		return cor;
	}

	public Objetivo getObjetivo() {
		return objetivo;
	}
	
	private void setCor(Color cor) {
		this.cor = cor;
	}
	
	private void setNome(String nome) {
		this.nome = nome;
	}

	private void setObjetivo(Objetivo objetivo) {
		this.objetivo = objetivo;
	}

	public List<Territorio> getListaTerritorios() {
		return listaTerritorios;
	}

	public void setListaTerritorios(List<Territorio> listaTerritorios) {
		this.listaTerritorios = listaTerritorios;
	}
	
	public void addTerritorios(Territorio territorio) {
		this.listaTerritorios.add(territorio);
	}
	
	public void removeTerritorios(Territorio territorio) {
		this.listaTerritorios.remove(territorio);
	}
	
	

	@Override
	public String toString() {
		return "Jogador [id=" + id + ", nome=" + nome + "]";
	}

	public Color getCorComplementar() {
		return corComplementar;
	}
	
	private Color descobreCorComplementar() {
		// retorna a cor complementar a cor do exercito do jogador
		// Cor complementar seria a cor que "combina" com a cor do exercito
		Color cor = this.getCor();
		
		if (cor.equals(Color.BLACK)) {
			return Color.WHITE;
		} else if (cor.equals(Color.WHITE)) {
			return Color.BLACK;
		} else if (cor.equals(Color.BLUE)) {
			return Color.ORANGE;
		} else if (cor.equals(Color.YELLOW)) {
			return Color.GREEN;
		}
		return Color.PINK; // ERRO ZUEIRO
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
