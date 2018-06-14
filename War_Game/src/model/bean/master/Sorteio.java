package model.bean.master;

import java.util.List;
import java.util.Random;

public class Sorteio {
	
	public final static int MinimoDado = 1; /** Menor valor a ser sorteado por um dado */
	public final static int MaximoDado = 6; /** Maior valor a ser sorteado por um dado */
	
	public static int sorteiaNumero(int minimo , int maximo) {
		/** Sorteia numeros entre o minimo e maximo incluindo eles mesmos
		*  O numero minimo passado por parametro pode ser sorteado
		*  O numero maximo passado por parametro tambem pode ser sorteado
		*  Exemplo de chamada da Funcao Sorteia.SorteiaNumero(1,6) -> Referente a um sorteio de dado
		*  Neste caso a possibilidade de retorno seriam numeros entre 1 e 6 (1,2,3,4,5,6)
		*  Retorna um inteiro com o valor sorteado
		*/ 
		
		Random random = new Random();
		
		int numeroAleatorio = 0;
		
		do {
			numeroAleatorio = random.nextInt(maximo);
		}while(numeroAleatorio < minimo);
		
		/** System.out.println("Gerado numero aleatorio: " + numeroAleatorio + " range[" + minimo + "-" + maximo + "]"); */
		
		return numeroAleatorio;
	}
	

	public static Objetivo sorteiaObjetivo(List<Objetivo> listaObjetivo, List<Objetivo> listaObjetivoEscolhido) {
		/** Sorteia um objetivo aleatoriamente que esteja na listaObjetivo passado pelo primeiro parametro
		 * o objetivo sorteado nao pode estar na lista de objetivos ja sorteados, passado pelo segundo parametro
		 */
		
		do {
			int idObjetivoSorteado = Sorteio.sorteiaNumero(1, listaObjetivo.size());
			
			for (Objetivo objetivoEscolhido : listaObjetivoEscolhido) {
				if (objetivoEscolhido.getId() == idObjetivoSorteado) {
					continue;
				}
			}
			
			return Objetivo.procuraObjetivoPeloId(idObjetivoSorteado, listaObjetivo);

		} while (true);
	}
	
}
