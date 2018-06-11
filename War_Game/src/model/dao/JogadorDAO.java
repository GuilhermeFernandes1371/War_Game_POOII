package model.dao;

import connection.ConnectionFactory;
import control.InicializadorDeJogo;

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

	public JogadorDAO() {
		this.con = ConnectionFactory.getConnection();
	}
	
	private int colorToIdCor(Color cor) {
		if (cor.equals(Color.WHITE)) {
			return 1;
		}
		else if (cor.equals(Color.BLACK)) {
			return 2;
		}
		else if (cor.equals(Color.YELLOW)) {
			return 3;
		}
		else if (cor.equals(Color.BLUE)) {
			return 4;
		}
		return 0;
	}
	
	private Color idCorToColor(int idCor) {
		if (idCor == 1) {
			return Color.WHITE;
		}
		else if (idCor == 2) {
			return Color.BLACK;
		}
		else if (idCor == 3) {
			return Color.YELLOW;
		}
		else if (idCor == 4) {
			return Color.BLUE;
		}
		return null;
	}

	public void createJogador(int gameId, Jogador jogador) throws SQLException {

		PreparedStatement state = null;
		String sql = "CREATE INTO jogador ( nome , cor , objetivoId , jogo_id ) VALUES (?,?,?,?)";

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

	public void updateJogador(int gameId, Jogador jogador) throws SQLException {

		PreparedStatement state = null;
		String sql = "REPLACE INTO jogador (id , nome , cor , objetivoId , jogo_id ) VALUES (?,?,?,?,?)";

		try {
			state = con.prepareStatement(sql);
			state.setInt   (1, jogador.getId());
			state.setString(2, jogador.getNome());
			state.setInt   (3, this.colorToIdCor(jogador.getCor()));
			state.setInt   (4, jogador.getObjetivo().getId());
			state.setInt   (5, gameId);
			state.executeUpdate();
		} finally {
			ConnectionFactory.closeConnection(con, state);
		}
	}

	public List<Jogador> findAll(int gameId) throws SQLException {
		PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM jogador WHERE jogador.gameId = ?";

        List<Jogador> lista = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, gameId);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Jogador jogador = new Jogador(rs.getString("nome") , this.idCorToColor(rs.getInt("cor")) , Objetivo.procuraObjetivoPeloId(rs.getInt("objetivoId") , InicializadorDeJogo.inicializaObjetivo()));
                jogador.setId(rs.getInt("id"));

                lista.add(jogador);
            }

        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return lista;
	}
}