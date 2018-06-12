package model.dao;

import connection.ConnectionFactory;
import control.ControladorDeJogo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.bean.master.Jogador;


public class JogoDAO {
	private Connection con = null;
    
    public JogoDAO() throws Exception{
        this.con = ConnectionFactory.getConnection();
    }
    
    public int maxId() throws SQLException , Exception {
		PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT MAX(jogo.id) as lastId FROM jogo";

        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("lastId");
            }

        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return 0;
	}
    
    public void createGame(int quantidadeJogadores , int rodada) throws SQLException{
        
        PreparedStatement state = null;
        String sql = "INSERT INTO jogo (quantidadeJogadores , rodada) VALUES (?,?)";
        
        try {
            state = con.prepareStatement(sql);
            state.setInt(1, quantidadeJogadores);
            state.setInt(2, rodada);
            state.executeUpdate();
        }finally{
            ConnectionFactory.closeConnection(con, state);
        } 
    }
    
    public void updateGame(int gameId, int quantidadeJogadores , int rodada) throws SQLException , Exception {

		PreparedStatement state = null;
		String sql = "UPDATE jogo SET quantidadeJogadores = ? , rodada = ? WHERE id = ?";

		try {
			state = con.prepareStatement(sql);
            state.setInt(1, quantidadeJogadores);
            state.setInt(2, rodada);
			state.setInt(3, gameId);
			state.executeUpdate();
		} finally {
			ConnectionFactory.closeConnection(con, state);
		}
	}
    
    public void loadGame(int gameId , ControladorDeJogo game) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT quantidadeJogadores , rodada FROM jogo WHERE jogo.id = ?";
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, gameId);
            rs = stmt.executeQuery();
            if (rs.next()) {
            	game.id                  = gameId;
                game.quantidadeJogadores = rs.getInt("quantidadeJogadores");
                game.rodada              = rs.getInt("rodada");
            }
        }finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    
    public boolean isExist(int gameId) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT id FROM jogo WHERE jogo.id = ?";
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, gameId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }else{
            	return false;
            }
        }finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
}
