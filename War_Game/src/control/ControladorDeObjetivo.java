package control;

import java.util.ArrayList;
import java.util.List;

import model.master.Jogador;
import model.mundo.Continente;
import java.awt.Color;

public class ControladorDeObjetivo {
	
	private ControladorDeObjetivo(List<Jogador> listaJogadores) {
		// Esta classe nao deve ser instanciada
	}
	
	public static boolean verificaObjetivo(Jogador jogador) {
		// Passa por parametro um jogador e ela verificar� se seu objetivo foi concluido
		// Retorna true caso o objetivo dele esteja concluido
		// Returna false caso o objetivo dele nao esteja concluido
		
		switch (jogador.getObjetivo().getId()) {
			case 1:
				return ControladorDeObjetivo.verificaObjetivoId1 (jogador);
			case 2:
				return ControladorDeObjetivo.verificaObjetivoId2 (jogador);
			case 3:
				return ControladorDeObjetivo.verificaObjetivoId3 (jogador);
			case 4:
				return ControladorDeObjetivo.verificaObjetivoId4 (jogador);
			case 5:
				return ControladorDeObjetivo.verificaObjetivoId5 (jogador);
			case 6:
				return ControladorDeObjetivo.verificaObjetivoId6 (jogador);
			case 7:
				return ControladorDeObjetivo.verificaObjetivoId7 (jogador);
			case 8:
				return ControladorDeObjetivo.verificaObjetivoId8 (jogador);
			case 9:
				return ControladorDeObjetivo.verificaObjetivoId9 (jogador);
			case 10:
				return ControladorDeObjetivo.verificaObjetivoId10(jogador);
			default:
				System.out.println("ERROR - Objetivo ainda nao instanciado");
				return false;
		}
	}
	
	private static boolean verificaObjetivoId1(Jogador jogador) {
		// Conquistar na totalidade a Am�rica do Sul e a Europa
		// Passa um jogador para verificar se ele completou o objetivo de ID 1
		// Return TRUE para objetivo completado pelo jogador
		// Return FALSE para objetivo nao completado pelo jogador
		Continente americaDoSul = Continente.getContinentePeloNome("America do Sul");
		Continente europa       = Continente.getContinentePeloNome("Europa");
		if (ControladorDeMilitaresEApoios.jogadorConquistouTerritorio(europa, jogador)   && 
				ControladorDeMilitaresEApoios.jogadorConquistouTerritorio(americaDoSul, jogador)) {
			return true;
		}
		return false;
	}
	
	private static boolean verificaObjetivoId2(Jogador jogador) {
		// Conquistar a Oceania e mais um continente a sua escolha
		// Passa um jogador para verificar se ele completou o objetivo de ID 2
		// Return TRUE para objetivo completado pelo jogador
		// Return FALSE para objetivo nao completado pelo jogador
		
		List<Continente> listaContinentes = new ArrayList<>();
		for (Continente continente : ControladorDeJogo.tabuleiro.getListaContinentes()) {
			listaContinentes.add(continente);
		}
		
		Continente oceania = Continente.getContinentePeloNome("Oceania");
		
		if (ControladorDeMilitaresEApoios.jogadorConquistouTerritorio(oceania, jogador)) {
			
			listaContinentes.remove(oceania);
			
			for (Continente continente : listaContinentes) {
				if (ControladorDeMilitaresEApoios.jogadorConquistouTerritorio(continente, jogador)) {
					return true;
				}
			}
		}
		return false;
		
	}
	
	private static boolean verificaObjetivoId3(Jogador jogador) {
		// Conquistar 24 territ�rios
		// Passa um jogador para verificar se ele completou o objetivo de ID 3
		// Return TRUE para objetivo completado pelo jogador
		// Return FALSE para objetivo nao completado pelo jogador
		
		if (jogador.getListaTerritorios().size() >= 24) {
			return true;
		}
		return false;
	}
	
	private static boolean verificaObjetivoId4(Jogador jogador) {
		// Conquistar a Am�rica do norte e a �frica (continente)
		// Passa um jogador para verificar se ele completou o objetivo de ID 4
		// Return TRUE para objetivo completado pelo jogador
		// Return FALSE para objetivo nao completado pelo jogador
		
		Continente americaDoNorte = Continente.getContinentePeloNome("America do Norte");
		Continente africa = Continente.getContinentePeloNome("Africa");
		
		if (ControladorDeMilitaresEApoios.jogadorConquistouTerritorio(americaDoNorte, jogador) 
		 && ControladorDeMilitaresEApoios.jogadorConquistouTerritorio(africa, jogador)) {
			return true;
		}
		return false;
	}
	
	private static boolean verificaObjetivoId5(Jogador jogador) {
		// Conquistar a �sia e outro continente da sua escolha
		// Passa um jogador para verificar se ele completou o objetivo de ID 5
		// Return TRUE para objetivo completado pelo jogador
		// Return FALSE para objetivo nao completado pelo jogador
		
		List<Continente> listaContinentes = new ArrayList<>();
		for (Continente continente : ControladorDeJogo.tabuleiro.getListaContinentes()) {
			listaContinentes.add(continente);
		}
		
		Continente asia = Continente.getContinentePeloNome("Asia");
		
		if (ControladorDeMilitaresEApoios.jogadorConquistouTerritorio(asia, jogador)) {
			
			listaContinentes.remove(asia);
			
			for (Continente continente : listaContinentes) {
				if (ControladorDeMilitaresEApoios.jogadorConquistouTerritorio(continente, jogador)) {
					return true;
				}
			}
		}
		return false;
	}
	
	private static boolean verificaObjetivoId6(Jogador jogador) {
		// Conquistar a Oceania, Am�rica do Sul e outro continente da sua escolha
		// Passa um jogador para verificar se ele completou o objetivo de ID 6
		// Return TRUE para objetivo completado pelo jogador
		// Return FALSE para objetivo nao completado pelo jogador
		
		List<Continente> listaContinentes = new ArrayList<>();
		for (Continente continente : ControladorDeJogo.tabuleiro.getListaContinentes()) {
			listaContinentes.add(continente);
		}
		
		Continente oceania = Continente.getContinentePeloNome("Oceania");
		Continente americaDoSul = Continente.getContinentePeloNome("America do Sul");
		
		if (ControladorDeMilitaresEApoios.jogadorConquistouTerritorio(oceania, jogador) 
		 && ControladorDeMilitaresEApoios.jogadorConquistouTerritorio(americaDoSul, jogador)) {
			
			listaContinentes.remove(oceania);
			listaContinentes.remove(americaDoSul);
			
			for (Continente continente : listaContinentes) {
				if (ControladorDeMilitaresEApoios.jogadorConquistouTerritorio(continente, jogador)) {
					return true;
				}
			}
		}
		return false;
		
	}
	
	private static boolean verificaObjetivoId7(Jogador jogador) {
		// Destruir o ex�rcito preto, se for voc� o ex�rcito preto ou se ele n�o estiver no jogo,
		// seu objetivo passa a ser conquistar 24 territ�rios
		// Passa um jogador para verificar se ele completou o objetivo de ID 7
		// Return TRUE para objetivo completado pelo jogador
		// Return FALSE para objetivo nao completado pelo jogador
		
		List<Jogador> listaJogador = new ArrayList<>();
		for (Jogador j : ControladorDeJogo.listaJogadores) {
			listaJogador.add(j);
		}
		listaJogador.remove(jogador);
		
		if (jogador.getCor().equals(Color.BLACK)) {
			// O jogador de cor preto � o propio jogador
			// O objetivo passa a ser conquistar 24 territorios
			return ControladorDeObjetivo.verificaObjetivoId3(jogador);
		}
		
		for (Jogador j : listaJogador) {
			if (j.getCor().equals(Color.BLACK)) {
				// O exercito preto esta no jogo e nao � voce
				if (j.getListaTerritorios().isEmpty()) {
					// O jogador de cor preto foi destruido
					return true;
				}
			}
		}
		
		return false;
	}
	
	private static boolean verificaObjetivoId8(Jogador jogador) {
		// Destruir o ex�rcito branco, se for voc� o ex�rcito branco ou se ele n�o estiver no jogo,
		// seu objetivo passa a ser conquistar 24 territ�rios
		// Passa um jogador para verificar se ele completou o objetivo de ID 8
		// Return TRUE para objetivo completado pelo jogador
		// Return FALSE para objetivo nao completado pelo jogador

		List<Jogador> listaJogador = new ArrayList<>();
		for (Jogador j : ControladorDeJogo.listaJogadores) {
			listaJogador.add(j);
		}
		listaJogador.remove(jogador);
		
		if (jogador.getCor().equals(Color.WHITE)) {
			// O jogador de cor branco � o propio jogador
			// O objetivo passa a ser conquistar 24 territorios
			return ControladorDeObjetivo.verificaObjetivoId3(jogador);
		}
		
		for (Jogador j : listaJogador) {
			if (j.getCor().equals(Color.WHITE)) {
				// O exercito branco esta no jogo e nao � voce
				if (j.getListaTerritorios().isEmpty()) {
					// O jogador de cor branco foi destruido
					return true;
				}
			}
		}
		
		return false;
	}
	
	private static boolean verificaObjetivoId9(Jogador jogador) {
		// Destruir o ex�rcito azul, se for voc� o ex�rcito azul ou se ele n�o estiver no jogo,
		// seu objetivo passa a ser conquistar 24 territ�rios
		// Passa um jogador para verificar se ele completou o objetivo de ID 9
		// Return TRUE para objetivo completado pelo jogador
		// Return FALSE para objetivo nao completado pelo jogador
		
		List<Jogador> listaJogador = new ArrayList<>();
		for (Jogador j : ControladorDeJogo.listaJogadores) {
			listaJogador.add(j);
		}
		listaJogador.remove(jogador);
		
		if (jogador.getCor().equals(Color.BLUE)) {
			// O jogador de cor azul � o propio jogador
			// O objetivo passa a ser conquistar 24 territorios
			return ControladorDeObjetivo.verificaObjetivoId3(jogador);
		}
		
		for (Jogador j : listaJogador) {
			if (j.getCor().equals(Color.BLUE)) {
				// O exercito azul esta no jogo e nao � voce
				if (j.getListaTerritorios().isEmpty()) {
					// O jogador de cor azul foi destruido
					return true;
				}
			}
		}
		
		return false;
	}
	
	private static boolean verificaObjetivoId10(Jogador jogador) {
		// Destruir o ex�rcito amarelo, se for voc� o ex�rcito amarelo ou se ele n�o estiver no jogo,
		// seu objetivo passa a ser conquistar 24 territ�rios
		// Passa um jogador para verificar se ele completou o objetivo de ID 10
		// Return TRUE para objetivo completado pelo jogador
		// Return FALSE para objetivo nao completado pelo jogador

		List<Jogador> listaJogador = new ArrayList<>();
		for (Jogador j : ControladorDeJogo.listaJogadores) {
			listaJogador.add(j);
		}
		listaJogador.remove(jogador);
		
		if (jogador.getCor().equals(Color.YELLOW)) {
			// O jogador de cor amarelo � o propio jogador
			// O objetivo passa a ser conquistar 24 territorios
			return ControladorDeObjetivo.verificaObjetivoId3(jogador);
		}
		
		for (Jogador j : listaJogador) {
			if (j.getCor().equals(Color.YELLOW)) {
				// O exercito amarelo esta no jogo e nao � voce
				if (j.getListaTerritorios().isEmpty()) {
					// O jogador de cor amarelo foi destruido
					return true;
				}
			}
		}
		
		return false;
	}
	
}
