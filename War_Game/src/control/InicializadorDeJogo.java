package control;

import java.util.ArrayList;
import java.util.List;

import model.bean.master.Jogador;
import model.bean.master.Objetivo;
import model.bean.master.Sorteio;
import model.bean.militares.Cabo;
import model.bean.militares.Militar;
import model.bean.militares.Sargento;
import model.bean.militares.Soldado;
import model.bean.militares.Tenente;
import model.bean.mundo.Continente;
import model.bean.mundo.Mundo;
import model.bean.mundo.Territorio;

public class InicializadorDeJogo {
	
	public static List<Jogador> ordenaJogadoresAleatoriamente(List<Jogador> listaJogadores) {
		/** O jogo escolhe a ordem dos jogadores aleatoriamente */
		
		List<Jogador> listaJogadoresOrdenadosAleatoriamente = new ArrayList<>();
		List<Jogador> listaJogadoresAux = new ArrayList<>();
		for (Jogador jogador : listaJogadores) {
			listaJogadoresAux.add(jogador);
		}
		
		for (int i = 0 ; i< listaJogadores.size() ; i++) {
			int numeroAleatorio = Sorteio.sorteiaNumero(0, listaJogadores.size() - listaJogadoresOrdenadosAleatoriamente.size());
			listaJogadoresOrdenadosAleatoriamente.add(listaJogadoresAux.get(numeroAleatorio));
			listaJogadoresAux.remove(numeroAleatorio);
		}
		
		return listaJogadoresOrdenadosAleatoriamente;
	}
	
	public static List<Jogador> inicializaDivisaoTerritorio(List<Jogador> listaJogadores , List<Territorio> listaTerritorios) {

		List<Territorio> listaTerritorioCopia = Territorio.geraCopiaListaTerritorio(listaTerritorios); // Tem que criar uma copia da lista de Territorios, para quando remover um elemento, nao remover no objeto original, ja que o que é passado por parametro é o endereco da memoria e nao o conteudo do array
		int quantidadeJogadores = listaJogadores.size(); // Quantidade de Jogadores
		int quantidadeTerritorios = listaTerritorioCopia.size(); // Quantidade de Territorios
		int quantidadeTerritoriosParaCadaJogador = quantidadeTerritorios / quantidadeJogadores; // Quantidade de territorios que serao dividios igualmente para cada jogador, o resto sera dividido posteriormente
		int quantidadeTerritoriosResto = quantidadeTerritorios % quantidadeJogadores; // Quantidade de Territorios que restaram ao tentar dividir igualmente os territorios entre os jogadores
		int quantidadeTerritoriosDistribuidos = 0; // Quantos territorios ja foram distribuidos
		int numeroSorteado = -1; // Numero Aleatorio gerado pela funca Sorteia da Classe Sorteio

		for (int i = 0; i < quantidadeTerritoriosParaCadaJogador; i++) {
			for (int j = 0; j < quantidadeJogadores; j++) {
				numeroSorteado = Sorteio.sorteiaNumero(0, quantidadeTerritorios - quantidadeTerritoriosDistribuidos);
				listaJogadores.get(j).addTerritorios(listaTerritorioCopia.remove(numeroSorteado));
				quantidadeTerritoriosDistribuidos++;
				
				System.out.println("Territorio: " + listaJogadores.get(j).getListaTerritorios().get(listaJogadores.get(j).getListaTerritorios().size() -1).getNome() + " foi sorteado ao jogador: " + listaJogadores.get(j).getNome());
			}
		}
		
		for (int i = 0 ; i < quantidadeTerritoriosResto ; i++) {
			
				numeroSorteado = Sorteio.sorteiaNumero(0, quantidadeTerritorios - quantidadeTerritoriosDistribuidos);
				Territorio territorio = listaTerritorioCopia.remove(numeroSorteado);
				listaJogadores.get(i).addTerritorios(territorio);
				quantidadeTerritoriosDistribuidos++;
				
				System.out.println("Territorio: " + territorio.getNome() + " foi sorteado ao jogador: " + listaJogadores.get(i).getNome());
		}

		return listaJogadores;
	}

	public static Mundo inicializaMundo() {

		List<Continente> listaContinentes = InicializadorDeJogo.inicializaContinentes();
		List<Territorio> listaTerritorios = InicializadorDeJogo.inicializaTerritorios(listaContinentes);

		return new Mundo(listaContinentes, listaTerritorios);
	}

	private static List<Continente> inicializaContinentes() {

		List<Militar> apoioOceania = new ArrayList<>();
		apoioOceania.add(new Soldado());
		apoioOceania.add(new Cabo());
		apoioOceania.add(new Sargento());
		Continente oceania = new Continente("Oceania", apoioOceania);
		
		List<Militar> apoioAmericaDoSul = new ArrayList<>();
		apoioAmericaDoSul.add(new Soldado());
		apoioAmericaDoSul.add(new Soldado());
		apoioAmericaDoSul.add(new Cabo());
		apoioAmericaDoSul.add(new Sargento());
		Continente americaDoSul = new Continente("America do Sul", apoioAmericaDoSul);

		List<Militar> apoioAfrica = new ArrayList<>();
		apoioAfrica.add(new Soldado());
		apoioAfrica.add(new Soldado());
		apoioAfrica.add(new Cabo());
		apoioAfrica.add(new Sargento());
		apoioAfrica.add(new Tenente());
		Continente africa = new Continente("Africa", apoioAfrica);

		List<Militar> apoioAmericaDoNorte = new ArrayList<>();
		apoioAmericaDoNorte.add(new Soldado());
		apoioAmericaDoNorte.add(new Soldado());
		apoioAmericaDoNorte.add(new Cabo());
		apoioAmericaDoNorte.add(new Sargento());
		apoioAmericaDoNorte.add(new Tenente());
		Continente americaDoNorte = new Continente("America do Norte", apoioAmericaDoNorte);

		List<Militar> apoioAsia = new ArrayList<>();
		apoioAsia.add(new Soldado());
		apoioAsia.add(new Soldado());
		apoioAsia.add(new Cabo());
		apoioAsia.add(new Cabo());
		apoioAsia.add(new Sargento());
		apoioAsia.add(new Tenente());
		Continente asia = new Continente("Asia", apoioAsia);

		List<Militar> apoioEuropa = new ArrayList<>();
		apoioEuropa.add(new Soldado());
		apoioEuropa.add(new Soldado());
		apoioEuropa.add(new Cabo());
		apoioEuropa.add(new Cabo());
		apoioEuropa.add(new Sargento());
		apoioEuropa.add(new Tenente());
		Continente europa = new Continente("Europa", apoioEuropa);

		List<Continente> listaContinentes = new ArrayList<>();
		listaContinentes.add(oceania);
		listaContinentes.add(americaDoSul);
		listaContinentes.add(africa);
		listaContinentes.add(americaDoNorte);
		listaContinentes.add(asia);
		listaContinentes.add(europa);

		return listaContinentes;
	}

	private static List<Territorio> inicializaTerritorios(List<Continente> listaContinentes) {
		/** Oceania -> 3 Territorios -> Australia , Indonesia , Nova Zelandia
			America do Sul -> 3 Territorios -> Brasil , Argentina , Colombia
			America do Norte -> 4 Territorios -> EUA , Canada , Mexico , Groelandia
			Africa -> 4 Territorios -> Africa do Sul , Egito , Libia , Angola
			Europa -> 5 Territorios -> Inglaterra , Portugal , França , Alemanha , Holanda
			Asia -> 5 Territorios -> Coreia do Sul , Russia , India , Japao , China
			Total -> 24 Territorios */
		
		Territorio brasil       = new Territorio("Brasil", Continente.getContinentePeloNome("America do Sul",listaContinentes));
		Territorio argentina    = new Territorio("Argentina", Continente.getContinentePeloNome("America do Sul",listaContinentes));
		Territorio colombia     = new Territorio("Colombia", Continente.getContinentePeloNome("America do Sul",listaContinentes));
		Territorio australia    = new Territorio("Australia", Continente.getContinentePeloNome("Oceania",listaContinentes));
		Territorio indonesia    = new Territorio("Indonesia", 	Continente.getContinentePeloNome("Oceania",listaContinentes));
		Territorio novaZelandia = new Territorio("Nova Zelandia", Continente.getContinentePeloNome("Oceania",listaContinentes));
		Territorio EUA          = new Territorio("EUA", Continente.getContinentePeloNome("America do Norte",listaContinentes));
		Territorio canada       = new Territorio("Canada",Continente.getContinentePeloNome("America do Norte",listaContinentes));
		Territorio mexico       = new Territorio("Mexico",	Continente.getContinentePeloNome("America do Norte",listaContinentes));
		Territorio groelandia   = new Territorio("Groelandia",Continente.getContinentePeloNome("America do Norte",listaContinentes));
		Territorio africaDoSul  = new Territorio("Africa do Sul",Continente.getContinentePeloNome("Africa",listaContinentes));
		Territorio egito        = new Territorio("Egito", Continente.getContinentePeloNome("Africa",listaContinentes));
		Territorio libia        = new Territorio("Libia", Continente.getContinentePeloNome("Africa",listaContinentes));
		Territorio angola       = new Territorio("Angola", Continente.getContinentePeloNome("Africa",listaContinentes));
		Territorio inglaterra   = new Territorio("Inglaterra",Continente.getContinentePeloNome("Europa",listaContinentes));
		Territorio portugal     = new Territorio("Portugal", Continente.getContinentePeloNome("Europa",listaContinentes));
		Territorio franca       = new Territorio("França", Continente.getContinentePeloNome("Europa",listaContinentes));
		Territorio alemanha     = new Territorio("Alemanha", Continente.getContinentePeloNome("Europa",listaContinentes));
		Territorio holanda      = new Territorio("Holanda", Continente.getContinentePeloNome("Europa",listaContinentes));
		Territorio coreiaDoSul  = new Territorio("Coreia do Sul",Continente.getContinentePeloNome("Asia",listaContinentes));
		Territorio russia       = new Territorio("Russia", Continente.getContinentePeloNome("Asia",listaContinentes));
		Territorio india        = new Territorio("India", Continente.getContinentePeloNome("Asia",listaContinentes));
		Territorio japao        = new Territorio("Japao", Continente.getContinentePeloNome("Asia",listaContinentes));
		Territorio china        = new Territorio("China", Continente.getContinentePeloNome("Asia",listaContinentes));
		
		/** Determinando os vizinhos

			America do Sul

			Vizinhos Brasil */
		
		List<Territorio> brasilVizinho = new ArrayList<>();
		brasilVizinho.add(argentina);
		brasilVizinho.add(colombia);
		brasilVizinho.add(angola);
		brasil.setListaVizinhos(brasilVizinho);

		/** Vizinhos Argentina */
		List<Territorio> argentinaVizinho = new ArrayList<>();
		argentinaVizinho.add(brasil);
		argentinaVizinho.add(colombia);
		argentina.setListaVizinhos(argentinaVizinho);

		/** Vizinhos Colombia */ 
		List<Territorio> colombiaVizinho = new ArrayList<>();
		colombiaVizinho.add(brasil);
		colombiaVizinho.add(argentina);
		colombiaVizinho.add(mexico);
		colombia.setListaVizinhos(colombiaVizinho);

		/** America do Norte

			Vizinhos Mexico */
		
		List<Territorio> mexicoVizinho = new ArrayList<>();
		mexicoVizinho.add(colombia);
		mexicoVizinho.add(EUA);
		mexicoVizinho.add(canada);
		mexico.setListaVizinhos(mexicoVizinho);

		/** Vizinhos Canada */
		List<Territorio> canadaVizinho = new ArrayList<>();
		canadaVizinho.add(mexico);
		canadaVizinho.add(EUA);
		canadaVizinho.add(russia);
		canada.setListaVizinhos(canadaVizinho);

		/** Vizinhos EUA */
		List<Territorio> EUAVizinho = new ArrayList<>();
		EUAVizinho.add(mexico);
		EUAVizinho.add(canada);
		EUAVizinho.add(groelandia);
		EUA.setListaVizinhos(EUAVizinho);

		/** Vizinhos Groelandia */
		List<Territorio> groelandiaVizinho = new ArrayList<>();
		groelandiaVizinho.add(EUA);
		groelandia.setListaVizinhos(groelandiaVizinho);

		/** Africa

			Vizinhos Angola */
		List<Territorio> angolaVizinho = new ArrayList<>();
		angolaVizinho.add(brasil);
		angolaVizinho.add(egito);
		angolaVizinho.add(libia);
		angolaVizinho.add(africaDoSul);
		angola.setListaVizinhos(angolaVizinho);

		/** Vizinhos Africa do Sul */
		List<Territorio> africaDoSulVizinho = new ArrayList<>();
		africaDoSulVizinho.add(angola);
		africaDoSul.setListaVizinhos(africaDoSulVizinho);

		/** Vizinhos Libia */
		List<Territorio> libiaVizinho = new ArrayList<>();
		libiaVizinho.add(angola);
		libiaVizinho.add(egito);
		libia.setListaVizinhos(libiaVizinho);

		/** Vizinhos Egito */
		List<Territorio> egitoVizinho = new ArrayList<>();
		egitoVizinho.add(angola);
		egitoVizinho.add(libia);
		egitoVizinho.add(india);
		egito.setListaVizinhos(egitoVizinho);

		/** Asia

			Vizinhos India */
		List<Territorio> indiaVizinho = new ArrayList<>();
		indiaVizinho.add(egito);
		indiaVizinho.add(china);
		indiaVizinho.add(coreiaDoSul);
		indiaVizinho.add(russia);
		india.setListaVizinhos(indiaVizinho);

		/** Vizinhos China */
		List<Territorio> chinaVizinho = new ArrayList<>();
		chinaVizinho.add(india);
		chinaVizinho.add(japao);
		chinaVizinho.add(coreiaDoSul);
		chinaVizinho.add(russia);
		china.setListaVizinhos(chinaVizinho);

		/** Vizinhos Japao */
		List<Territorio> japaoVizinho = new ArrayList<>();
		japaoVizinho.add(china);
		japaoVizinho.add(coreiaDoSul);
		japaoVizinho.add(russia);
		japaoVizinho.add(australia);
		japao.setListaVizinhos(japaoVizinho);

		/** Vizinhos Coreia do Sul */
		List<Territorio> coreiaDoSulVizinho = new ArrayList<>();
		coreiaDoSulVizinho.add(china);
		coreiaDoSulVizinho.add(india);
		coreiaDoSulVizinho.add(japao);
		coreiaDoSulVizinho.add(indonesia);
		coreiaDoSul.setListaVizinhos(coreiaDoSulVizinho);

		/** Vizinhos Russia */
		List<Territorio> russiaVizinho = new ArrayList<>();
		russiaVizinho.add(china);
		russiaVizinho.add(india);
		russiaVizinho.add(japao);
		russiaVizinho.add(alemanha);
		russiaVizinho.add(canada);
		russia.setListaVizinhos(russiaVizinho);

		/** Oceania

			Vizinhos Indonesia */
		List<Territorio> indonesiaVizinho = new ArrayList<>();
		indonesiaVizinho.add(coreiaDoSul);
		indonesiaVizinho.add(novaZelandia);
		indonesia.setListaVizinhos(indonesiaVizinho);

		/** Vizinhos Nova Zelandia */
		List<Territorio> novaZelandiaVizinho = new ArrayList<>();
		novaZelandiaVizinho.add(coreiaDoSul);
		novaZelandiaVizinho.add(australia);
		novaZelandia.setListaVizinhos(novaZelandiaVizinho);

		/** Vizinhos Australia */
		List<Territorio> australiaVizinho = new ArrayList<>();
		australiaVizinho.add(novaZelandia);
		australiaVizinho.add(japao);
		australia.setListaVizinhos(australiaVizinho);

		/** Europa

			Vizinhos Alemanha */
		List<Territorio> alemanhaVizinho = new ArrayList<>();
		alemanhaVizinho.add(russia);
		alemanhaVizinho.add(franca);
		alemanhaVizinho.add(holanda);
		alemanha.setListaVizinhos(alemanhaVizinho);

		/** Vizinhos Holanda */
		List<Territorio> holandaVizinho = new ArrayList<>();
		holandaVizinho.add(alemanha);
		holandaVizinho.add(franca);
		holandaVizinho.add(portugal);
		holanda.setListaVizinhos(holandaVizinho);

		/** Vizinhos Franca */
		List<Territorio> francaVizinho = new ArrayList<>();
		francaVizinho.add(alemanha);
		francaVizinho.add(inglaterra);
		francaVizinho.add(holanda);
		franca.setListaVizinhos(francaVizinho);

		/** Vizinhos Inglaterra */
		List<Territorio> inglaterraVizinho = new ArrayList<>();
		inglaterraVizinho.add(franca);
		inglaterraVizinho.add(portugal);
		inglaterra.setListaVizinhos(inglaterraVizinho);

		/** Vizinhos Portugal */
		List<Territorio> portugalVizinho = new ArrayList<>();
		portugalVizinho.add(inglaterra);
		portugalVizinho.add(holanda);
		portugal.setListaVizinhos(portugalVizinho);

		List<Territorio> listaTerritorios = new ArrayList<>();
		listaTerritorios.add(brasil);
		listaTerritorios.add(argentina);
		listaTerritorios.add(colombia);
		listaTerritorios.add(australia);
		listaTerritorios.add(indonesia);
		listaTerritorios.add(novaZelandia);
		listaTerritorios.add(EUA);
		listaTerritorios.add(canada);
		listaTerritorios.add(mexico);
		listaTerritorios.add(groelandia);
		listaTerritorios.add(africaDoSul);
		listaTerritorios.add(egito);
		listaTerritorios.add(libia);
		listaTerritorios.add(angola);
		listaTerritorios.add(inglaterra);
		listaTerritorios.add(portugal);
		listaTerritorios.add(franca);
		listaTerritorios.add(alemanha);
		listaTerritorios.add(holanda);
		listaTerritorios.add(coreiaDoSul);
		listaTerritorios.add(russia);
		listaTerritorios.add(india);
		listaTerritorios.add(japao);
		listaTerritorios.add(china);
		
		/** Seta um atributo nos continentes identificando a quantidade de territorios que possui cada continente */
		for (int i=0 ; i<listaContinentes.size() ; i++) {
			int quantidade = InicializadorDeJogo.inicializaAtributoQuantidadeTerritoriosNoContinente(listaContinentes.get(i), listaTerritorios);
			listaContinentes.get(i).setQuantidadeTerritorios(quantidade);
		}
		
		/** Povoa com 1 Militar cada territorio */
		
				brasil.addMilitar(new Soldado());
				argentina.addMilitar(new Soldado());
				colombia.addMilitar(new Soldado());
				australia.addMilitar(new Soldado());
				indonesia.addMilitar(new Soldado());
				novaZelandia.addMilitar(new Soldado());
				EUA.addMilitar(new Soldado());
				canada.addMilitar(new Soldado());
				mexico.addMilitar(new Soldado());
				groelandia.addMilitar(new Soldado());
				africaDoSul.addMilitar(new Soldado());
				egito.addMilitar(new Soldado());
				libia.addMilitar(new Soldado());
				angola.addMilitar(new Soldado());
				inglaterra.addMilitar(new Soldado());
				portugal.addMilitar(new Soldado());
				franca.addMilitar(new Soldado());
				alemanha.addMilitar(new Soldado());
				holanda.addMilitar(new Soldado());
				coreiaDoSul.addMilitar(new Soldado());
				russia.addMilitar(new Soldado());
				india.addMilitar(new Soldado());
				japao.addMilitar(new Soldado());
				china.addMilitar(new Soldado());
				
		/** Fim povoamento */

		return listaTerritorios;
	}

	private static int inicializaAtributoQuantidadeTerritoriosNoContinente (Continente continente , List<Territorio> listaTerritorio) {
		int quantidade = 0;
		
		for (Territorio territorio : listaTerritorio) {
			if ( territorio.getContinente().equals(continente) ) {
				quantidade++;
			}
		}
		
		return quantidade;
	}
	
	public static List<Objetivo> inicializaObjetivo() {
		List<Objetivo> listaObjetivo = new ArrayList<>();

		listaObjetivo.add(new Objetivo(1, "Conquistar na totalidade a América do Sul e a Europa"));
		listaObjetivo.add(new Objetivo(2, "Conquistar a Oceania e mais um continente a sua escolha"));
		listaObjetivo.add(new Objetivo(3, "Conquistar 24 territórios"));
		listaObjetivo.add(new Objetivo(4, "Conquistar a América do norte e a África (continente)"));
		listaObjetivo.add(new Objetivo(5, "Conquistar a Ásia e outro continente da sua escolha"));
		listaObjetivo.add(new Objetivo(6, "Conquistar a Oceania, América do Sul e outro continente da sua escolha"));
		listaObjetivo.add(new Objetivo(7,
				"Destruir o exército preto, se for você o exército preto ou se ele não estiver no jogo, seu objetivo passa a ser conquistar 24 territórios"));
		listaObjetivo.add(new Objetivo(8,
				"Destruir o exército branco, se for você o exército branco ou se ele não estiver no jogo, seu objetivo passa a ser conquistar 24 territórios"));
		listaObjetivo.add(new Objetivo(9,
				"Destruir o exército azul, se for você o exército azul ou se ele não estiver no jogo, seu objetivo passa a ser conquistar 24 territórios"));
		listaObjetivo.add(new Objetivo(10,
				"Destruir o exército amarelo, se for você o exército amarelo ou se ele não estiver no jogo, seu objetivo passa a ser conquistar 24 territórios"));

		return listaObjetivo;
	}

}
