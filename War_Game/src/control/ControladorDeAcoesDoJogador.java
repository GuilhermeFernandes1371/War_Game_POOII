package control;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.master.Jogador;
import model.militares.Militar;
import model.mundo.Territorio;

public class ControladorDeAcoesDoJogador {
	private Jogador jogador;
	private int menorOpcaoMenu;
	private int maiorOpcaoMenu;
	
	public ControladorDeAcoesDoJogador(Jogador jogador) {
		this.menorOpcaoMenu = 1;
		this.maiorOpcaoMenu = 3;
		this.jogador = jogador;
		this.menuDeAcoes();
	}
	
	private void menuDeAcoes() {
		int opcao = 0;
		do {
			do {   
				opcao = control.InterfaceGrafica.selecionaAcaoJogador(this.jogador);
			}while(opcao < this.menorOpcaoMenu || opcao > this.maiorOpcaoMenu);
			
			if (opcao == 1) {
				// Atacar
				this.organizaAtaque();
			} else if (opcao == 2) {
				// Remaneja Tropa
				this.organizaRemanejo();
			} else if (opcao == 3) {
				// Passa a vez
			}
		}while (opcao == 1); // Caso nao ataque, passa a vez
		// fim do turno
	}
	
	private void organizaRemanejo() {
		Territorio territorioOrigem = null;
		Territorio territorioDestino = null;
		
		do {
			territorioOrigem = control.InterfaceGrafica.selecionaTerritorio(this.jogador.getListaTerritorios(), "Selecione um territorio que tenha posse:" , jogador);
			if (territorioOrigem.getListaMilitares().size() == 1) {
				// O territorio so pode remanejar se tiver 2 ou mais militares
				JOptionPane.showMessageDialog(null, "Este territorio tem apenas 1 militar, retornando ao menu");
				return; // retorna ao menu
			} else {
				break;
			}
		}while(true);
		
		// Remove os territorios que sao do propio atacante
		List<Territorio> territorioVizinhoDoJogador = model.mundo.Territorio.removeDuplicados(territorioOrigem.getListaVizinhos(), this.jogador.getListaTerritorios() , true);
		if (territorioVizinhoDoJogador.isEmpty()) {
			// Caso nao exista vizinhos aliados, ou seja, os vizinhos sao todos dominados pelos inimigos
			JOptionPane.showMessageDialog(null, "Este territorio nao tem vizinhos remanejaveis, retornado ao menu");
			return; // retorna ao menu
		}
		
		// Seleciona um vizinho aliado para remanejar
		territorioDestino = control.InterfaceGrafica.selecionaTerritorio(territorioVizinhoDoJogador , "Selecione um vizinho para remanejar:" , jogador);
		
		Militar militarRemanejar = control.InterfaceGrafica.selecionaMilitar(territorioOrigem.getListaMilitares(), "Selecione um militar para remanejar para " + territorioDestino.getNome(), jogador);
		
		territorioDestino.addMilitar(militarRemanejar);
		territorioOrigem.removeMilitar(militarRemanejar);
		JOptionPane.showMessageDialog(null, "Militar remanejado de " + territorioOrigem.getNome() + " para " + territorioDestino.getNome());
		
		int retorno = JOptionPane.showConfirmDialog(null, "Deseja remanejar mais militares ?" , "Remanejar ou passar a vez" , JOptionPane.YES_NO_OPTION);
		if (retorno == JOptionPane.YES_OPTION) {
			this.organizaRemanejo();
		} else if (retorno == JOptionPane.NO_OPTION) {
			JOptionPane.showMessageDialog(null, "Passou a Vez");
		}
	}
	
	private void organizaAtaque() {
		Territorio territorioAtacante = null;
		Territorio territorioVizinhoAtacado = null;
		List<Militar> listaMilitaresAtacante = new ArrayList<>();
		List<Militar> listaMilitaresAtacado  = new ArrayList<>();
		
		// Seleciona um territorio para ser o atacante
		do {
			territorioAtacante = control.InterfaceGrafica.selecionaTerritorio(this.jogador.getListaTerritorios(), "Selecione um territorio que tenha posse:" , jogador);
			if (territorioAtacante.getListaMilitares().size() == 1) {
				// O territorio so pode atacar se tiver 2 ou mais militares
				JOptionPane.showMessageDialog(null, "Este territorio tem apenas 1 militar, escolha outro");
			} else {
				break;
			}
		}while(true);
		
		// Remove os territorios que sao do propio atacante
		List<Territorio> territorioVizinhoSemVizinhosDoPropioJogador = model.mundo.Territorio.removeDuplicados(territorioAtacante.getListaVizinhos(), this.jogador.getListaTerritorios() , false);
		if (territorioVizinhoSemVizinhosDoPropioJogador.isEmpty()) {
			// Caso nao exista vizinhos atacaveis, ou seja, os vizinhos sao todos dominados pelo propio atacante
			JOptionPane.showMessageDialog(null, "Este territorio nao tem vizinhos atacaveis, retornado ao menu");
			return; // retorna ao menu
		}
		
		// Seleciona um vizinho para atacar
		territorioVizinhoAtacado = control.InterfaceGrafica.selecionaTerritorio(territorioVizinhoSemVizinhosDoPropioJogador , "Selecione um vizinho para atacar:" , jogador);
		
		for (Militar militar : territorioAtacante.getListaMilitares()) {
			listaMilitaresAtacante.add(militar);
		}
		for (Militar militar : territorioVizinhoAtacado.getListaMilitares()) {
			listaMilitaresAtacado.add(militar);
		}
		
		List<Militar> listaMilitaresAtacanteEscolhido = new ArrayList<>();
		List<Militar> listaMilitaresAtacadoEscolhido  = new ArrayList<>();
		
		while ( listaMilitaresAtacanteEscolhido.size() < 3) {
			if (listaMilitaresAtacante.size() == 1) {
				JOptionPane.showMessageDialog(null, "1 militar deve ficar vigiando o territorio durante o ataque");
				break;
			}
			Militar militar = control.InterfaceGrafica.selecionaMilitar(listaMilitaresAtacante, "Selecione um Militar para Atacar", territorioAtacante.getJogador() , listaMilitaresAtacanteEscolhido.size());
			if (militar == null) {
				if (listaMilitaresAtacanteEscolhido.size() == 0) {
					JOptionPane.showMessageDialog(null, "Escolha pelo menos 1 militar para atacar");
					continue;
				}
				break;
			}
			listaMilitaresAtacante.remove(militar);
			listaMilitaresAtacanteEscolhido.add(militar);
		}
		
		while ( listaMilitaresAtacadoEscolhido.size() < 3) {
			Militar militar = control.InterfaceGrafica.selecionaMilitar(listaMilitaresAtacado, "Selecione um Militar para Defender", territorioVizinhoAtacado.getJogador() , listaMilitaresAtacadoEscolhido.size());
			if (militar == null) {
				if (listaMilitaresAtacadoEscolhido.size() == 0) {
					JOptionPane.showMessageDialog(null, "Escolha pelo menos 1 militar para defender");
					continue;
				}
				break;
			}
			listaMilitaresAtacado.remove(militar);
			listaMilitaresAtacadoEscolhido.add(militar);
		}
		
		ControladorDeAtaque ataqueDoJogador = new ControladorDeAtaque(territorioAtacante , territorioVizinhoAtacado , listaMilitaresAtacanteEscolhido , listaMilitaresAtacadoEscolhido);
		control.InterfaceGrafica.resultadoAtaque(ataqueDoJogador);
	}

}
