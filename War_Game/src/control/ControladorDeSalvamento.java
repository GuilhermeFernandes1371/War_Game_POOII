package control;

import java.sql.SQLException;

import model.dao.JogoDAO;

public class ControladorDeSalvamento {
	private ControladorDeJogo jogo;
	private int gameId;
	
	public ControladorDeSalvamento(ControladorDeJogo jogo , int gameId) throws SQLException {
		this.jogo = jogo;
		this.gameId = gameId;
		
		this.salvaGame();
		this.salvaJogadores();
		this.salvaTerritorios();
		this.salvaMilitares();
		
	}
	
	private void salvaGame() throws SQLException {
		JogoDAO dao = new JogoDAO();
		dao.createGame(this.gameId , this.jogo);
	}
	
	private void salvaJogadores() {
		
	}
	
	private void salvaTerritorios() {
		
	}
	
	private void salvaMilitares() {
		
	}
}
