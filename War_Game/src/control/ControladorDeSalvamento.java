package control;

import java.sql.SQLException;

import model.dao.JogadorDAO;
import model.dao.JogoDAO;
import model.dao.MilitarDAO;
import model.dao.TerritorioDAO;
import model.bean.master.Jogador;
import model.bean.militares.Militar;
import model.bean.mundo.Territorio;

public class ControladorDeSalvamento {
	
	private int numeroTotalDeOperacoes;
	private int numeroAtualDeOperacoes;
	
	public ControladorDeSalvamento() throws SQLException , Exception {
		
		this.numeroTotalDeOperacoes = this.descobreNumeroTotalDeOperacoes();
		this.numeroAtualDeOperacoes = 1;
	}
	
	private int descobreNumeroTotalDeOperacoes() {
		int contador = 1;
		
		for (Jogador jogador : ControladorDeJogo.listaJogadores) {
			for (Territorio territorio : jogador.getListaTerritorios()) {
				contador = contador + territorio.getListaMilitares().size();
			}
			contador = contador + jogador.getListaTerritorios().size();
		}
		contador = contador + ControladorDeJogo.listaJogadores.size();
		
		return contador;
	}
	
	public void salvaGame() throws SQLException , Exception {
		if (ControladorDeJogo.id == 0) {
			JogoDAO dao = new JogoDAO();
			dao.createGame(ControladorDeJogo.quantidadeJogadores , ControladorDeJogo.rodada);
			
			JogoDAO dao2 = new JogoDAO();
			ControladorDeJogo.id = dao2.maxId();
		}else{
			JogoDAO dao = new JogoDAO();
			dao.updateGame(ControladorDeJogo.id , ControladorDeJogo.quantidadeJogadores , ControladorDeJogo.rodada);
		}
		numeroAtualDeOperacoes++;
		
		this.salvaJogadores();
	}
	
	private void salvaJogadores() throws SQLException , Exception {
		for (Jogador jogador : ControladorDeJogo.listaJogadores) {
			JogadorDAO dao = new JogadorDAO();
			if (jogador.getId() == 0) {
				dao.createJogador(ControladorDeJogo.id, jogador);
				
				JogadorDAO dao2 = new JogadorDAO();
				jogador.setId(dao2.maxId());
			}else{
				dao.updateJogador(ControladorDeJogo.id, jogador);
			}
			
			this.salvaTerritorios(jogador);
			numeroAtualDeOperacoes++;
		}
	}
	
	private void salvaTerritorios(Jogador jogador) throws SQLException , Exception {
		for (Territorio territorio : jogador.getListaTerritorios()) {
			TerritorioDAO dao = new TerritorioDAO();
			if (territorio.getId() == 0) {
				dao.createTerritorio(jogador , territorio);
				
				TerritorioDAO dao2 = new TerritorioDAO();
				territorio.setId(dao2.maxId());
				
			}else{
				dao.updateTerritorio(jogador , territorio);
			}
			
			this.salvaMilitares(territorio);
			numeroAtualDeOperacoes++;
		}
	}
	
	private void salvaMilitares(Territorio territorio) throws SQLException , Exception {
		for (Militar militar : territorio.getListaMilitares()) {
			MilitarDAO dao = new MilitarDAO();
			if (militar.getId() == 0) {
				dao.createMilitar(territorio , militar);
				
				MilitarDAO dao2 = new MilitarDAO();
				militar.setId(dao2.maxId());
			}else{
				dao.updateMilitar(territorio , militar);
			}
			numeroAtualDeOperacoes++;
		}
	}

	public int getNumeroTotalDeOperacoes() {
		return numeroTotalDeOperacoes;
	}
	
	public int getNumeroAtualDeOperacoes() {
		return numeroAtualDeOperacoes;
	}
}
