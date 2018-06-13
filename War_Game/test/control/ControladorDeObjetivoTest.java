package control;

import static org.junit.Assert.*;
import model.bean.master.Jogador;
import model.bean.master.Objetivo;
import model.bean.mundo.Mundo;

import org.junit.Test;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class ControladorDeObjetivoTest {

	/**
	 * Test method for {@link control.InicializadorDeJogo#testVerificaObjetivo(java.util.List)}.
	 */
	@Test
	public void testVerificaObjetivo() {
		
		/** Este m�todo verifica se o objetivo 3 - "Conquistar 24 territ�rios" est� funcionando
		 * ap�s ser apontado para um jogador!	
		 */
		
		Objetivo objetivoTeste = new Objetivo(3 , "Conquistar 24 territ�rios");
		
		Jogador fischer = new Jogador("Fischer" , Color.WHITE, objetivoTeste);
	
		Mundo mundoTeste = InicializadorDeJogo.inicializaMundo();
		
		fischer.setListaTerritorios(mundoTeste.getListaTerritorios());
		
		assertTrue(ControladorDeObjetivo.verificaObjetivo(fischer));
	}
	
	
}
