package control;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.master.Jogador;
import model.master.Objetivo;

public class ControladorEscolherJogadores {

	private int quantidadeDeJogadores = 0;
	private List<Jogador> listaJogadores = new ArrayList<>();
	private List<Objetivo> listaObjetivo = new ArrayList<>();
	private List<Objetivo> listaObjetivoEscolhido = new ArrayList<>();

	private final int minimoJogadores = 2;
	private final int maximoJogadores = 4;

	public int getQuantidadeDeJogadores() {
		return this.quantidadeDeJogadores;
	}

	public List<Jogador> getListaJogadores() {
		return this.listaJogadores;
	}

	public ControladorEscolherJogadores(List<Objetivo> listaObjetivo) {
		this.listaObjetivo = listaObjetivo;
		this.quantidadeDeJogadores = control.InterfaceGrafica.escolherQuantidadeJogadores(minimoJogadores, maximoJogadores);
		this.escolheJogadores(this.quantidadeDeJogadores);
	}

	private void escolheJogadores(int quantidadeDeJogadores) {
		for (int i = 0; i < quantidadeDeJogadores; i++) {
			Jogador jogador = lerJogador();
			this.listaJogadores.add(jogador);
		}
	}

	private Jogador lerJogador() {
		Jogador jogador = control.InterfaceGrafica.selecionaJogador(this.listaJogadores, this.listaObjetivo, this.listaObjetivoEscolhido);
		this.listaObjetivoEscolhido.add(jogador.getObjetivo());
		JOptionPane.showMessageDialog(null, "Objetivo: " + jogador.getObjetivo().getObjetivo() , "Objetivo do Jogador " + jogador.getNome() , JOptionPane.INFORMATION_MESSAGE);
		return jogador;
	}

	public static boolean verificaCorDuplicado(Color cor , List<Jogador> listaJogadores) {
		// Retorna TRUE caso a cor passado por parametro ja foi escolhido
		// Retorna FALSE caso a cor passado por parametro nao tenha sido escolhido

		for (Jogador jogador : listaJogadores) {
			if (jogador.getCor().equals(cor)) {
				return true;
			}
		}
		return false;
	}

}
