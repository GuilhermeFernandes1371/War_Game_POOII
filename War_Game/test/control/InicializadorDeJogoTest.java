/**
 * 
 */
package control;

import static org.junit.Assert.*;
import model.bean.master.Jogador;
import model.bean.master.Objetivo;
import model.bean.mundo.Mundo;

import org.junit.Test;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author User
 *
 */
public class InicializadorDeJogoTest {

	/**
	 * Test method for {@link control.InicializadorDeJogo#ordenaJogadoresAleatoriamente(java.util.List)}.
	 */
	@Test
	public void testOrdenaJogadoresAleatoriamente() {
		/** 
		 * Esta fun��o de teste serve para testar se a fun��o retorna uma lista de jogadores
		 * com todos os jogadores ainda contido nela.
		 * Obs1: Para verificar se a fun��o nao esta engolindo jogadores (Passa 4 jogadores e ela retorna jogadores diferentes)
		 * Obs2: Esta fun��o nao testa se os jogadores foram realmente ordenados aleatoriamente, pois isto � inviavel ja que � 'Aleatorio' o ordenamento
		 */
		
		Objetivo objetivoTeste = new Objetivo(1 , "Objetivo de teste");
		
		Jogador guilherme = new Jogador("Guilherme" , Color.WHITE, objetivoTeste);
		Jogador gabriel = new Jogador("Gabriel" , Color.BLUE, objetivoTeste);
		Jogador prea = new Jogador("Prea" , Color.BLACK, objetivoTeste);
		Jogador paje = new Jogador("Paje" , Color.YELLOW, objetivoTeste);
		
		List<Jogador> listaJogadores = new ArrayList<>();
		listaJogadores.add(guilherme);
		listaJogadores.add(gabriel);
		listaJogadores.add(prea);
		listaJogadores.add(paje);
		
		List<Jogador> listaOrdenadaAleatoriamente = control.InicializadorDeJogo.ordenaJogadoresAleatoriamente(listaJogadores);
		
		assertTrue(listaOrdenadaAleatoriamente.size() == listaJogadores.size()); /** Testa se a lista tem o mesmo n�mero de jogadores ap�s passar pela fun��o*/
		assertTrue(listaOrdenadaAleatoriamente.contains(guilherme));             /** Testa se o jogador GUILHERME ainda esta na lista ordenada aleatoriamente*/
		assertTrue(listaOrdenadaAleatoriamente.contains(gabriel));               /** Testa se o jogador GABRIEL   ainda esta na lista ordenada aleatoriamente*/
		assertTrue(listaOrdenadaAleatoriamente.contains(prea));      			 /** Testa se o jogador PREA      ainda esta na lista ordenada aleatoriamente*/
		assertTrue(listaOrdenadaAleatoriamente.contains(paje));      			 /** Testa se o jogador PAJE      ainda esta na lista ordenada aleatoriamente*/
	}

	/**
	 * Test method for {@link control.InicializadorDeJogo#testInicializaMundo(java.util.List, java.util.List)}.
	 * Esse m�todo realiza o teste para verificar se a lista de territ�rios 
	 * cont�m ou n�o a quantidade determinada de continentes, que � igual a 6.
	 */
	
	@Test
	public void testInicializaContinentes() {

		Mundo mundo = InicializadorDeJogo.inicializaMundo();
		
		assertFalse(mundo.getListaContinentes().size() < 6);
		assertFalse(mundo.getListaContinentes().size() > 6);
		assertTrue(mundo.getListaContinentes().size() == 6);

	}

	/**
	 * Test method for {@link control.InicializadorDeJogo#inicializaObjetivo()}.
	 * Esse m�todo realiza o teste para verificar se a lista de objetivos  
	 * cont�m ou n�o a quantidade determinada de objetivos que foram determinados 
	 * na classe de nome "Inicializador de Jogo". O total de objetivos s�o 10.
	 */
	
	@Test
	public void testInicializaObjetivo() {
		
		List<Objetivo> listaObjetivo = InicializadorDeJogo.inicializaObjetivo();
		
		assertFalse(listaObjetivo.size() < 10);
		assertFalse(listaObjetivo.size() > 10);
		assertTrue(listaObjetivo.size() == 10);
		
	}

}
