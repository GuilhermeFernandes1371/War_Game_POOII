package control;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.master.Jogador;
import model.master.Objetivo;
import model.mundo.Mundo;
import view.PlayerInfo;

public class ControladorDeJogo {
	
	public static int rodada;
	public static int quantidadeJogadores;
	public static List<Jogador> listaJogadores;
	public static Mundo tabuleiro;
	public static List<Objetivo> listaObjetivo = new ArrayList<>();
	public static PlayerInfo playerInfo = null;
	
	public ControladorDeJogo() {
		this.inicializaJogo();
		this.preJogo();
		
		control.InterfaceGrafica.startPlayerStatus();
		
		// INICIA loop de jogo
		while(true) {
			if (this.preRodada()) {
				break; // fim de jogo
			}
			this.realizaJogada();
		}
		// Fim do jogo
	}
	
	private boolean preRodada() {
		rodada++;
		// Verifica se algum jogador conquistou algum continente e caso tenha conquistado, ele posiciona o apoio militar
		// Verifica quantos militares cada jogador ganha referente ao numero de territorios
		ControladorDeMilitaresEApoios controladorPosicionarMilitares = new ControladorDeMilitaresEApoios();
		
		return this.verificaVencedor();
	}
	
	private void realizaJogada() {
		for (Jogador jogador : ControladorDeJogo.listaJogadores) {
			JOptionPane.showMessageDialog(null, "Iniciando turno de " + jogador.getNome());
			ControladorDeAcoesDoJogador rodadaDoJogador = new ControladorDeAcoesDoJogador(jogador);
			JOptionPane.showMessageDialog(null, "Finalizando turno de " + jogador.getNome());		
		}	
	}

	private boolean verificaVencedor() {
		// Verifica um vencedor
		// Return true para finalizar o jogo
		// Return false para continuar o jogo
		for (Jogador jogador : ControladorDeJogo.listaJogadores) {
			// Verifica conclusão de objetivo de cada jogador
			if (ControladorDeObjetivo.verificaObjetivo(jogador)) {
				// Jogador venceu o jogo
				JOptionPane.showMessageDialog(null, "O jogador " + jogador.getNome() + " venceu o jogo");
				InterfaceMostraEstatistica fimDeJogo = new InterfaceMostraEstatistica(jogador, this.rodada , this.tabuleiro.getListaTerritorios());
				
				return true;
				// fim de jogo
			}
		}
		return false; // jogo continua
	}
	
	private void inicializaJogo() {
		ControladorDeJogo.listaObjetivo = InicializadorDeJogo.inicializaObjetivo();
		ControladorDeJogo.rodada = 0;
		ControladorDeJogo.tabuleiro = InicializadorDeJogo.inicializaMundo();
		ControladorDeJogo.listaJogadores = new ArrayList<>();
	}
	
	private void preJogo() {
		ControladorEscolherJogadores interfaceEscolherJogadores = new ControladorEscolherJogadores(ControladorDeJogo.listaObjetivo);
		ControladorDeJogo.quantidadeJogadores = interfaceEscolherJogadores.getQuantidadeDeJogadores();
		ControladorDeJogo.listaJogadores = interfaceEscolherJogadores.getListaJogadores();
		
		ControladorDeJogo.listaJogadores = InicializadorDeJogo.ordenaJogadoresAleatoriamente(ControladorDeJogo.listaJogadores);
		ControladorDeJogo.listaJogadores = InicializadorDeJogo.inicializaDivisaoTerritorio(ControladorDeJogo.listaJogadores, ControladorDeJogo.tabuleiro.getListaTerritorios());
	}

}
