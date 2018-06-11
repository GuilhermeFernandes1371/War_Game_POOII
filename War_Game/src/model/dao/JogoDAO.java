package model.dao;

import connection.ConnectionFactory;
import control.ControladorDeJogo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class JogoDAO {
	private Connection con = null;
    
    public JogoDAO(){
        this.con = ConnectionFactory.getConnection();
    }
    public void createGame(int gameId , ControladorDeJogo jogo) throws SQLException{
        
        PreparedStatement state = null;
        String sql = "REPLACE INTO jogo (id ,  quantidadeJogadores , rodada) VALUES (?,?,?)";
        
        try {
            state = con.prepareStatement(sql);
            state.setInt(1, gameId);
            state.setInt(2, jogo.quantidadeJogadores);
            state.setInt(3, jogo.rodada);
            state.executeUpdate();
        }finally{
            ConnectionFactory.closeConnection(con, state);
        } 
    }
    
    public ControladorDeJogo loadGame(int gameId , ControladorDeJogo jogo) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT quantidadeJogadores , rodada FROM jogo WHERE jogo.id = gameId";
        
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            if (rs.next()) {
                jogo.quantidadeJogadores = rs.getInt("quantidadeJogadores");
                jogo.rodada = rs.getInt("rodada");
                return jogo;
            }
        }finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return null;
    }
}
