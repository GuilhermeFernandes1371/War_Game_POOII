package model.bean.militares;

import model.bean.master.Sorteio;

public class Tenente extends Militar {
	
	public static final int pontos = 3; /** Valor de força adicional da unidade */
	
	public Tenente()  {
		this.id = 0;
	}
	
	public int valorAtaque () {
		/** Retorna um inteiro
		 * Sorteia um numero aleatorio e soma ao valor de força adicional da unidade
		 */
		return Sorteio.sorteiaNumero(Sorteio.MinimoDado, Sorteio.MaximoDado) + Tenente.pontos;
	}
	
	public String toString() {
		return "Tenente";
	}
}
