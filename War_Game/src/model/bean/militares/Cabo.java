package model.bean.militares;

import model.bean.master.Sorteio;

public class Cabo extends Militar{
	
	public static final int pontos = 1; /** Valor de for�a adicional da unidade */
	
	public Cabo () {
		this.id = 0;
	}
	
	public int valorAtaque () {
		/** Retorna um inteiro
		 * Sorteia um numero aleatorio e soma ao valor de for�a adicional da unidade
		 */
		return Sorteio.sorteiaNumero(Sorteio.MinimoDado, Sorteio.MaximoDado) + Cabo.pontos;
	}
	
	public String toString() {
		return "Cabo";
	}

}
