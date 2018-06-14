package control;

import java.awt.Color;
import java.util.List;

import javax.swing.JOptionPane;

import model.bean.master.Jogador;
import model.bean.master.Objetivo;
import model.bean.militares.Militar;
import model.bean.mundo.Territorio;
import view.AcoesDoJogador;
import view.EscolherJogador;
import view.PlayerInfo;
import view.ResultadoAtaque;
import view.SalvandoJogo;
import view.SelecionarMilitar;
import view.SelecionarTerritorio;

public class InterfaceGrafica {
	
	private InterfaceGrafica() {
		/** Esta classe nao deve ser instanciada */
	}
	
	public static void startPlayerStatus() {
		/** Inicia a interface grafica que mostra os territorios de cada jogador em tempo real
			Uma thread é gerada para ficar atualizando a interface
			Para funcionar é preciso que o pre jogo tenha sido concluido, pois
			é preciso que a lista de jogadores ja esteja preenchida */
		
		if (ControladorDeJogo.playerInfo != null) {
			ControladorDeJogo.playerInfo.frame.dispose();
		}
		ControladorDeJogo.playerInfo = new PlayerInfo(ControladorDeJogo.listaJogadores);
	}
	
	public static void automaticSave() {
		SalvandoJogo interfaceGrafica = new SalvandoJogo();
		while(interfaceGrafica.flag == false) {
			/** Aguarda a interface executar */
			System.out.println("Aguarda o salvamento automatico do jogo");
		}
		interfaceGrafica.dispose();
	}
	
	public static void resultadoAtaque(ControladorDeAtaque controle) {
		/** Este metodo é usado para mostrar o resultado de um ataque por meio de uma interface grafica
		
			Abre a interface Grafica */
		ResultadoAtaque interfaceGrafica = new ResultadoAtaque(controle);
		while(interfaceGrafica.flag == false) {
			/** Aguarda a interface acabar de mostrar os dados */
			System.out.println("Aguarda resultado ataque");
		}
		control.InterfaceGrafica.startPlayerStatus();
		interfaceGrafica.frame.dispose();
		/** fecha a interface grafica */
	}
	
	public static Territorio selecionaTerritorio(List<Territorio> listaTerritorios , String stringLabel , Jogador jogador) {
		/** Este metodo é usado para abrir uma interface grafica onde o usuario
			podera escolher um territorio por comboBox
		
			Abre a interface grafica */
		SelecionarTerritorio interfaceGrafica = new SelecionarTerritorio(listaTerritorios , stringLabel , jogador);
		while(interfaceGrafica.flag == false) {
			/** Aguarda o usuario escolher o territorio */
			System.out.println("Aguarda territorio");
		}
		control.InterfaceGrafica.startPlayerStatus();
		Territorio territorio = interfaceGrafica.getTerritorioEscolhido();
		interfaceGrafica.frame.dispose();
		/** fecha a interface grafica */
		return territorio;
	}
	
	public static Militar selecionaMilitar(List<Militar> listaMilitares , String stringLabel , Jogador jogador) {
		/** Este metodo é usado para abrir uma interface grafica onde o usuario
			Podera escolher um militar por um comboBox
				
			Abre a interface grafica */
		SelecionarMilitar interfaceGrafica = new SelecionarMilitar(listaMilitares , stringLabel , jogador);
		while(interfaceGrafica.flag == false) {
			/** Aguarda o evento do botao da interface */
			System.out.println("Aguarda Militar");
		}
		control.InterfaceGrafica.startPlayerStatus();
		Militar militarEscolhido = interfaceGrafica.getMilitarEscolhido();
		interfaceGrafica.frame.dispose();
		/** fecha a interface grafica */ 
		return militarEscolhido;
	}
	
	public static Militar selecionaMilitar(List<Militar> listaMilitares , String stringLabel , Jogador jogador , int quantidadeMilitaresSelecionados) {
		/** Este metodo é usado para abrir uma interface grafica onde o usuario
			podera escolher um militar por um comboBox
			Nesta sobrecarga, a interface grafica conta com um botao extra, no qual o usuario
			pode clicar para nao selecionar mais unidades, caso ele queria selecionar
			apenas 1 ou 2 unidades, em vez de 3 por exemplo.
		
			Abre a interface grafica */
		SelecionarMilitar interfaceGrafica = new SelecionarMilitar(listaMilitares , stringLabel , jogador , quantidadeMilitaresSelecionados);
		while(interfaceGrafica.flag == false) {
			/** Aguarda o evento do botao da interface */
			System.out.println("Aguardando Militar");
		}
		control.InterfaceGrafica.startPlayerStatus();
		if (interfaceGrafica.cancelado) {
			/** Caso o usuario queira escolher apenas 1 ou 2 unidades, em vez de 3 */
			interfaceGrafica.frame.dispose();
			/** fecha a interface grafica e retorna null (militar nao selecionado) */
			return null;
		}
		Militar militarEscolhido = interfaceGrafica.getMilitarEscolhido();
		interfaceGrafica.frame.dispose();
		/** fecha a interface grafica */
		return militarEscolhido;
	}
	
	public static int escolherQuantidadeJogadores(int minimoJogadores , int maximoJogadores) {
		/** Escolhe a quantidade de jogadores por uma interface grafica
			retornando um inteiro com o valor escolhido */
		
		int quantidadeDeJogadores = 0;
		while (quantidadeDeJogadores < minimoJogadores || quantidadeDeJogadores > maximoJogadores) {
			/** Ativa a interface grafica */
			view.EscolherQuantidadeJogadores interfaceEscolha = new view.EscolherQuantidadeJogadores();
			while(interfaceEscolha.flag == false) {
				/** Aguarda o usuario escolher a quantidade de jogadores */
				System.out.println("Aguardando Interface");
			}
			quantidadeDeJogadores = interfaceEscolha.getQuantidadeDeJogadores();
			interfaceEscolha.frame.dispose(); // fecha a interface grafica
		}
		return quantidadeDeJogadores;
	}
	
	public static Jogador selecionaJogador(List<Jogador> listaJogadores , List<Objetivo> listaObjetivo , List<Objetivo> listaObjetivoEscolhido) {
		/** Seleciona um jogador atraves de uma interface grafica
			Na interface o jogador preenchera um campo de nome e
			selecionara um comboBox de cor */
		
		String nome = "Default";
		Color cor = null;
		/** Ativa a interface grafica */ 
		EscolherJogador interfaceGrafica = new EscolherJogador(listaJogadores.size());
		while(true) {
			while(interfaceGrafica.flag == false) {
				/** Aguardando ler o jogadores */
				System.out.println("Aguardando a interface");
			}
			nome = interfaceGrafica.getNome();
			cor = interfaceGrafica.getCor();
	
			if (ControladorEscolherJogadores.verificaCorDuplicado(cor , listaJogadores)) {
				/** Esta cor ja foi usada */
				JOptionPane.showMessageDialog(null,"Cor duplicado, essa cor ja foi usado");
				interfaceGrafica.flag = false; /** Prende no loop para aguardar o evento do botao novamente */
				continue;
			}else {
				/** Jogador escolhido com sucesso */
				interfaceGrafica.frame.dispose(); /** fecha a interface grafica */
				break;
			}
		}
		Objetivo objetivo = model.bean.master.Sorteio.sorteiaObjetivo(listaObjetivo, listaObjetivoEscolhido);
		Jogador jogador = new Jogador(nome, cor, objetivo);
		return jogador;
	}
	
	public static int selecionaAcaoJogador(Jogador jogador) {
		/** Este metodo é usado para abrir uma interface grafica onde o usuario
			podera escolher as acoes que ele podera fazer, sendo elas:
			1 - Atacar
			2 - Remanejar
			3 - Passar a vez */
		
		int opcao = 0;
		/** Ativa a interface grafica */
		AcoesDoJogador interfaceGrafica = new AcoesDoJogador(jogador);
			
		while(interfaceGrafica.flag == false) {
			/** Aguarda o evento de botao */
			System.out.println("Aguardando Militar");
		}
		control.InterfaceGrafica.startPlayerStatus();
		opcao = interfaceGrafica.getOp();
		interfaceGrafica.frame.dispose(); 
		/** fecha a interface grafica */
		return opcao;
		
	}
}
