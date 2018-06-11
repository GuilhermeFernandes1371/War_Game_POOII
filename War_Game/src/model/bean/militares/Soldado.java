package model.bean.militares;

import model.bean.master.Sorteio;

public class Soldado extends Militar {
	
	public static final int pontos = 0; // Valor de força adicional da unidade
	
	public Soldado() {
		this.id = 0;
	}
	
	public int valorAtaque () {
		/* Retorna um inteiro
		 * Sorteia um numero aleatorio e soma ao valor de força adicional da unidade
		 */
		return Sorteio.sorteiaNumero(Sorteio.MinimoDado, Sorteio.MaximoDado) + Soldado.pontos;
	}
	
	public String toString() {
		return "Soldado";
	}
}
