package model.bean.mundo;

import java.util.ArrayList;
import java.util.List;

import model.bean.master.Jogador;
import model.bean.militares.Militar;

public class Territorio {
	private String nome;
	private Continente continente;
	private List<Territorio> listaVizinhos = new ArrayList<>();
	private List<Militar> listaMilitares = new ArrayList<>();
	
	public Territorio(String nome , Continente continente) {
		this.setContinente(continente);
		this.setNome(nome);
	}
	
	public Jogador getJogador() {
		for (Jogador jogador : control.ControladorDeJogo.listaJogadores) {
			for (Territorio territorio : jogador.getListaTerritorios()) {
				if (territorio.getNome() == this.getNome()) {
					return jogador;
				}
			}
		}
		return null;
	}
	
	public static List<Territorio> removeDuplicados(List<Territorio> listaTerritorio1 , List<Territorio> listaTerritorio2 , boolean inverso) {
		// Caso inverso == false
		// Remove da primeira lista, os territorios que são repetidos na segunda lista
		// Caso inverso == true
		// Remove da primeira lista, os territorios que não são repetidos na segunda lista
		
		List<Territorio> listaTerritorio3 = new ArrayList<>();
		if (inverso == false) {
			for (Territorio territorio : listaTerritorio1) {
				listaTerritorio3.add(territorio);
			}
			for (Territorio territorio1 : listaTerritorio1) {
				for (Territorio territorio2 : listaTerritorio2) {
					if (territorio1.equals(territorio2)) {
						listaTerritorio3.remove(territorio1);
					}
				}
			}
		} else {
			for (Territorio territorio1 : listaTerritorio1) {
				for (Territorio territorio2 : listaTerritorio2) {
					if (territorio1.equals(territorio2)) {
						listaTerritorio3.add(territorio1);
					}
				}
			}
		}
		return listaTerritorio3;
	}
	
	public void addMilitar(Militar militar) {
		this.listaMilitares.add(militar);
	}
	
	public boolean removeMilitar(Militar militar) {
		return this.listaMilitares.remove(militar);
	}
	
	public static List<Territorio> geraCopiaListaTerritorio (List<Territorio> listaTerritorioOriginal) {
		List<Territorio> listaTerritorioCopia = new ArrayList<>();
		
		for (Territorio territorio : listaTerritorioOriginal) {
			listaTerritorioCopia.add(territorio);
		}
		
		return listaTerritorioCopia;
	}
	
	public static Territorio getTerritorioPeloNome(List<Territorio> listaTerritorio , String nomeTerritorio) {
		for (Territorio territorio : listaTerritorio) {
			if (territorio.getNome().equals(nomeTerritorio)) {
				return territorio;
			}
		}
		return null;
	}
	
	public List<Militar> getListaMilitares() {
		return this.listaMilitares;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Continente getContinente() {
		return continente;
	}

	public void setContinente(Continente continente) {
		this.continente = continente;
	}

	public List<Territorio> getListaVizinhos() {
		return listaVizinhos;
	}

	public void setListaVizinhos(List<Territorio> listaVizinhos) {
		this.listaVizinhos = listaVizinhos;
	}
	
	public String toString() {
		return this.nome;
	}
	
	
	
}
