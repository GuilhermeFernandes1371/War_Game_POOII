package control;

import java.util.List;

import model.master.Jogador;
import model.militares.Militar;
import model.mundo.Territorio;

public class InterfaceMostraEstatistica {
	
	private Jogador vencedor;
	private int rodada;
	private List<Territorio> listaTerritorios;
	
	public InterfaceMostraEstatistica(Jogador vencedor , int rodada , List<Territorio> listaTerritorios) {
		this.vencedor = vencedor;
		this.rodada = rodada;
		this.listaTerritorios = listaTerritorios;
		
		this.mostraVencedor();
		this.mostraTurno();
		this.mostraEstadoDeJogo();
	}
	
	private void mostraEstadoDeJogo() {
		for (Territorio territorio : this.listaTerritorios) {
			System.out.println("Territorio " + territorio.getNome() + " (" + territorio.getContinente().getNome() + ")");
			System.out.println("Numero de militares: " + territorio.getListaMilitares().size() + " (");
			for (Militar militar : territorio.getListaMilitares()) {
				System.out.print(" " + militar.toString() + ",");
			}
			System.out.print(")");
			System.out.println("-----------");
		}
	}
	
	private void mostraTurno() {
		System.out.println("O jogo finalizou no turno " + this.rodada);
	}
	
	private void mostraVencedor() {
		System.out.println("O jogador " + this.vencedor.getNome() + " o jogo");
		System.out.println("Objetivo: " + this.vencedor.getObjetivo().getObjetivo());
	}
	
}
