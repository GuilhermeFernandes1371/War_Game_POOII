package model.dao;

import connection.ConnectionFactory;
import control.ControladorDeJogo;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.bean.master.Jogador;
import model.bean.master.Objetivo;

public class JogadorDAO {
	private Connection con = null;

	public JogadorDAO() throws Exception{
		this.con = ConnectionFactory.getConnection();
	}
	
	private int colorToIdCor(Color cor) throws Exception {
		if (cor.equals(Color.WHITE)) {
			return 1;
		} else if (cor.equals(Color.BLACK)) {
			return 2;
		} else if (cor.equals(Color.YELLOW)) {
			return 3;
		} else if (cor.equals(Color.BLUE)) {
			return 4;
		}
		
		throw new Exception("ERRO EM JogadorDAO:colorToIdCor - Cor nao encontrada");
	}
	
	private Color idCorToColor(int idCor) throws Exception {
		if (idCor == 1) {
			return Color.WHITE;
		} else if (idCor == 2) {
			return Color.BLACK;
		} else if (idCor == 3) {
			return Color.YELLOW;
		} else if (idCor == 4) {
			return Color.BLUE;
		}
		
		throw new Exception("ERRO EM JogadorDAO:idCorToColor - idCor nao encontrado");
	}
	
	public int maxId() throws SQLException , Exception {
		PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT MAX(jogador.id) as lastId FROM jogador";

        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("lastId");
            }

        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        throw new Exception("Erro em JogadorDAO:maxId - Erro em MaxId");
	}

	public void createJogador(int gameId, Jogador jogador) throws SQLException , Exception {

		PreparedStatement state = null;
		String sql = "INSERT INTO jogador ( nome , cor , objetivoId , jogo_id ) VALUES (?,?,?,?)";

		try {
			state = con.prepareStatement(sql);
			state.setString(1, jogador.getNome());
			state.setInt   (2, this.colorToIdCor(jogador.getCor()));
			state.setInt   (3, jogador.getObjetivo().getId());
			state.setInt   (4, gameId);
			state.executeUpdate();
		} finally {
			ConnectionFactory.closeConnection(con, state);
		}
	}

	public void updateJogador(int gameId, Jogador jogador) throws SQLException , Exception {

		PreparedStatement state = null;
		String sql = "UPDATE jogador SET nome = ? , cor = ? , objetivoId = ? , jogo_id = ? WHERE id = ?";

		try {
			state = con.prepareStatement(sql);
			state.setString(1, jogador.getNome());
			state.setInt   (2, this.colorToIdCor(jogador.getCor()));
			state.setInt   (3, jogador.getObjetivo().getId());
			state.setInt   (4, gameId);
			state.setInt   (5, jogador.getId());
			state.executeUpdate();
		} finally {
			ConnectionFactory.closeConnection(con, state);
		}
	}

	public List<Jogador> findAll(int gameId) throws SQLException , Exception {
		PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM jogador WHERE jogador.gameId = ?";

        List<Jogador> lista = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, gameId);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Jogador jogador = new Jogador(rs.getString("nome") , this.idCorToColor(rs.getInt("cor")) , Objetivo.procuraObjetivoPeloId(rs.getInt("objetivoId") , ControladorDeJogo.listaObjetivo));
                jogador.setId(rs.getInt("id"));

                lista.add(jogador);
            }

        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return lista;
	}
}