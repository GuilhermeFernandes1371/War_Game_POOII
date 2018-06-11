package model.dao;

import connection.ConnectionFactory;
import control.ControladorDeJogo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


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
    
    public void createGame(int gameId , int quantidadeJogadores , int rodada) throws SQLException{
        
        PreparedStatement state = null;
        String sql = "REPLACE INTO jogo (id , quantidadeJogadores , rodada) VALUES (?,?,?)";
        
        try {
            state = con.prepareStatement(sql);
            state.setInt(1, gameId);
            state.setInt(2, quantidadeJogadores);
            state.setInt(3, rodada);
            state.executeUpdate();
        }finally{
            ConnectionFactory.closeConnection(con, state);
        } 
    }
    
    public void loadGame(int gameId) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT quantidadeJogadores , rodada FROM jogo WHERE jogo.id = gameId";
        
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            if (rs.next()) {
                ControladorDeJogo.quantidadeJogadores = rs.getInt("quantidadeJogadores");
                ControladorDeJogo.rodada              = rs.getInt("rodada");
            }
        }finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
}
