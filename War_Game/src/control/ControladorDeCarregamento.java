package control;

public class ControladorDeCarregamento {
	
	private int numeroTotalDeOperacoes;
	private int numeroAtualDeOperacoes;
	
	private ControladorDeJogo jogo;
	private int gameId;
	
	public ControladorDeCarregamento(int gameId) {
		
		this.gameId = gameId;
		this.jogo = new ControladorDeJogo();
		this.jogo.id = this.gameId;
		
		this.carregaGame();
		
	}
	
	private void carregaGame() {
		
		
		
	}

	public int getNumeroTotalDeOperacoes() {
		return numeroTotalDeOperacoes;
	}

	public int getNumeroAtualDeOperacoes() {
		return numeroAtualDeOperacoes;
	}
	
	
}
