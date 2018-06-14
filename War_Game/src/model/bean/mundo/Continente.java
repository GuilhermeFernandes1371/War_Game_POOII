package model.bean.mundo;

import java.util.ArrayList;
import java.util.List;

import control.ControladorDeJogo;
import model.bean.militares.Militar;

public class Continente {
	
	private String nome;
	private int quantidadeTerritorios;
	private List<Militar> apoio = new ArrayList<>();
	
	public Continente(String nome , List<Militar> apoio) {
		this.setApoio(apoio);
		this.setNome(nome);
	}
	
	public static Continente getContinentePeloNome (String nomeContinente) {
		for (Continente continente : ControladorDeJogo.tabuleiro.getListaContinentes()) {
			if (continente.nome.equals(nomeContinente)) {
				return continente;
			}
		}
		System.out.println("Erro em Continente->getContinentePeloNome | Continente: " + nomeContinente + " nao existe na lista de Continentes: " + ControladorDeJogo.tabuleiro.getListaContinentes());
		return null;
	}
	
	public static Continente getContinentePeloNome (String nomeContinente , List<Continente> listaContinentes) {
		for (Continente continente : listaContinentes) {
			if (continente.nome.equals(nomeContinente)) {
				return continente;
			}
		}
		System.out.println("Erro em Continente->getContinentePeloNome | Continente: " + nomeContinente + " nao existe na lista de Continentes: " + ControladorDeJogo.tabuleiro.getListaContinentes());
		return null;
	}
	
	public List<Territorio> getTerritorio() {
		List<Territorio> listaTerritoriosDoContinente = new ArrayList<>();
		for (Territorio territorio : ControladorDeJogo.tabuleiro.getListaTerritorios()) {
			if (territorio.getContinente().getNome().equals(this.nome)) {
				listaTerritoriosDoContinente.add(territorio);
			}
		}
		System.out.println();
		return listaTerritoriosDoContinente;
	}

	public int getQuantidadeTerritorios() {
		return quantidadeTerritorios;
	}

	public void setQuantidadeTerritorios(int quantidadeTerritorios) {
		this.quantidadeTerritorios = quantidadeTerritorios;
	}

	public String getNome() {
		return nome;
	}

	private void setNome(String nome) {
		this.nome = nome;
	}

	public List<Militar> getApoio() {
		/** Retorna uma copia para nao passar o endereco da memoria */
		
		List<Militar> apoioCopia = new ArrayList<>();
		for (Militar militar : this.apoio) {
			apoioCopia.add(militar);
		}
		return apoioCopia;
	}

	private void setApoio(List<Militar> apoio) {
		this.apoio = apoio;
	}

	@Override
	public String toString() {
		return "Continente [nome=" + nome + ", quantidadeTerritorios=" + quantidadeTerritorios + ", apoio=" + apoio
				+ "]";
	}
	
}
