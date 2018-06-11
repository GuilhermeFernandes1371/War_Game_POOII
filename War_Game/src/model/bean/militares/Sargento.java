package model.bean.militares;

import model.bean.master.Sorteio;

public class Sargento extends Militar {
	
	public static final int pontos = 2; // Valor de for�a adicional da unidade
	
	public Sargento() {
		
	}
	
	public int valorAtaque () {
		/* Retorna um inteiro
		 * Sorteia um numero aleatorio e soma ao valor de for�a adicional da unidade
		 */
		return Sorteio.sorteiaNumero(Sorteio.MinimoDado, Sorteio.MaximoDado) + Sargento.pontos;
	}
	
	public String toString() {
		return "Sargento";
	}
	
}
