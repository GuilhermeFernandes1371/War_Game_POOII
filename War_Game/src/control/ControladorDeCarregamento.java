package control;

import model.bean.master.Jogador;
import model.bean.militares.Militar;
import model.bean.mundo.Territorio;
import model.dao.JogadorDAO;
import model.dao.JogoDAO;
import model.dao.MilitarDAO;
import model.dao.TerritorioDAO;

public class ControladorDeCarregamento {
	
	private int numeroTotalDeOperacoes = 5;
	private int numeroAtualDeOperacoes = 14;
	
	private ControladorDeJogo jogo;
	
	public ControladorDeCarregamento(){
		
	}
	
	private void replicaControladorDeJogo() {
		ControladorDeJogo.id = this.jogo.id;
		ControladorDeJogo.listaJogadores = this.jogo.listaJogadores;
		ControladorDeJogo.listaObjetivo = this.jogo.listaObjetivo;
		ControladorDeJogo.playerInfo = this.jogo.playerInfo;
		ControladorDeJogo.quantidadeJogadores = this.jogo.quantidadeJogadores;
		ControladorDeJogo.rodada = this.jogo.rodada;
		ControladorDeJogo.tabuleiro = this.jogo.tabuleiro;
	}
	
	public void start(int gameId) throws Exception {
		numeroAtualDeOperacoes++;
		this.jogo = new ControladorDeJogo();
		numeroAtualDeOperacoes++;
		this.jogo.id = gameId;
		numeroAtualDeOperacoes++;
		this.carregaGame();
		numeroAtualDeOperacoes = 13;
		this.replicaControladorDeJogo();
		numeroAtualDeOperacoes = 14;
	}
	
	private void carregaGame() throws Exception {
		
		JogoDAO game = new JogoDAO();
		numeroAtualDeOperacoes++;
		if (game.isExist(this.jogo.id)) {
			numeroAtualDeOperacoes++;
			JogoDAO dao = new JogoDAO();
			dao.loadGame(this.jogo.id , this.jogo);
			numeroAtualDeOperacoes++;
			this.carregaJogadores();
		}else{
			numeroAtualDeOperacoes = 14;
			throw new Exception("Este gameId( " + this.jogo.id + ") não existe no BDA");
		}
		
	}
	
	private void carregaJogadores() throws Exception {
		JogadorDAO dao = new JogadorDAO();
		this.jogo.listaJogadores.clear();
		
		numeroAtualDeOperacoes = 7;
		
		for (Jogador jogador : dao.findAll(this.jogo.id)) {
			jogador = this.carregaTerritorios(jogador);
			this.jogo.listaJogadores.add(jogador);
		}
		numeroAtualDeOperacoes = 12;
		
	}
	
	private Jogador carregaTerritorios(Jogador jogador) throws Exception {
		TerritorioDAO dao = new TerritorioDAO();
		
		numeroAtualDeOperacoes = 8;
		
		for (Territorio territorio : dao.findAll(jogador)) {
			territorio = this.carregaMilitares(territorio);
			jogador.addTerritorios(territorio);
		}
		numeroAtualDeOperacoes = 11;
		
		return jogador;
	}
	
	private Territorio carregaMilitares(Territorio territorio) throws Exception {
		MilitarDAO dao = new MilitarDAO();
		numeroAtualDeOperacoes = 9;
		for (Militar militar : dao.findAll(territorio)) {
			territorio.addMilitar(militar);
		}
		numeroAtualDeOperacoes = 10;
		return territorio;
	}

	public int getNumeroTotalDeOperacoes() {
		return numeroTotalDeOperacoes;
	}

	public int getNumeroAtualDeOperacoes() {
		return numeroAtualDeOperacoes;
	}
	
	
}
