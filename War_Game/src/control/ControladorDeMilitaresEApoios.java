package control;

import java.util.ArrayList;
import java.util.List;

import model.bean.master.Jogador;
import model.bean.militares.Militar;
import model.bean.militares.Soldado;
import model.bean.mundo.Continente;
import model.bean.mundo.Territorio;

public class ControladorDeMilitaresEApoios {

	public ControladorDeMilitaresEApoios() {
		this.verificaConquistaContinente();
		this.verificaQuantidadeTerritoriosPosRodada();
	}
	
	private void verificaQuantidadeTerritoriosPosRodada() {
		for (int i=0 ; i < ControladorDeJogo.listaJogadores.size() ; i++) {
			int quantidadeTerritorios = ControladorDeJogo.listaJogadores.get(i).getListaTerritorios().size();
			int quantidadeMilitaresGanhados = (quantidadeTerritorios / 2) + (quantidadeTerritorios % 2);
			
			List<Militar> listaMilitares = new ArrayList<>();
			
			for (int j=0 ; j<quantidadeMilitaresGanhados ; j++) {
				listaMilitares.add(new Soldado());
			}
			
			Jogador jogador = ControladorDeJogo.listaJogadores.get(i);
			jogador.addMilitaresSemTerritorio(listaMilitares);
			this.posicionaMilitaresSemTerritorio(jogador);
		}
	}
	
	private void verificaConquistaContinente() {
		for (Jogador jogador : ControladorDeJogo.listaJogadores) {
			for (Continente continente : ControladorDeJogo.tabuleiro.getListaContinentes()) {
				if (ControladorDeMilitaresEApoios.jogadorConquistouTerritorio(continente , jogador)) {
					this.posicionaMilitarDeApoio(jogador, continente);
				}
			}
		}
	}
	
	private void posicionaMilitaresSemTerritorio(Jogador jogador) {
		List<Militar> listaMilitares = jogador.getListaMilitaresSemTerritorio();
		while(!listaMilitares.isEmpty()) {
			Territorio territorioEscolhido = control.InterfaceGrafica.selecionaTerritorio(jogador.getListaTerritorios(), "Selecione um territorio para posicionar seus Militares ", jogador);
			Militar militarEscolhido = control.InterfaceGrafica.selecionaMilitar(listaMilitares, "Selecione um militar para posicionar em " + territorioEscolhido.getNome(), jogador);
			territorioEscolhido.addMilitar(militarEscolhido);
			listaMilitares.remove(militarEscolhido);
		}
	}
	
	private void posicionaMilitarDeApoio(Jogador jogador , Continente continente) {
		List<Militar> apoio = continente.getApoio();
		while(!apoio.isEmpty()) {
			Territorio territorioEscolhido = control.InterfaceGrafica.selecionaTerritorio(continente.getTerritorio(), "Selecione um territorio do continente " + continente.getNome(), jogador);
			Militar militarEscolhido = control.InterfaceGrafica.selecionaMilitar(apoio , "Selecione um apoio para posicionar em " + territorioEscolhido.getNome(), jogador);
			territorioEscolhido.addMilitar(militarEscolhido);
			apoio.remove(militarEscolhido);
		}
	}
	

	
	public static boolean jogadorConquistouTerritorio(Continente continente , Jogador jogador) {
		int contador = 0;
		for (Territorio territorio : jogador.getListaTerritorios()) {
			if (territorio.getContinente().equals(continente)) {
				contador++;
			}
		}
		if (contador == continente.getQuantidadeTerritorios()) {
			return true;
		}
		return false;
	}
}
