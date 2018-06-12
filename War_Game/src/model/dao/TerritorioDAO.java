package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.bean.master.Jogador;
import model.bean.mundo.Territorio;
import connection.ConnectionFactory;
import control.ControladorDeJogo;

public class TerritorioDAO {
	
	private Connection con = null;

	public TerritorioDAO() throws Exception {
		this.con = ConnectionFactory.getConnection();
	}
	
	public int maxId() throws SQLException , Exception {
		PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT MAX(territorio.id) as lastId FROM territorio";

        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("lastId");
            }

        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        throw new Exception("Erro em TerritorioDAO:maxId - Erro em MaxId");
	}

	public void createTerritorio(Jogador jogador , Territorio territorio) throws SQLException {

		PreparedStatement state = null;
		String sql = "INSERT INTO territorio ( nome , jogador_id ) VALUES (?,?)";

		try {
			state = con.prepareStatement(sql);
			state.setString(1, territorio.getNome());
			state.setInt   (2, jogador.getId());
			state.executeUpdate();
		} finally {
			ConnectionFactory.closeConnection(con, state);
		}
	}

	public void updateTerritorio(Jogador jogador , Territorio territorio) throws SQLException {

		PreparedStatement state = null;
		String sql = "UPDATE territorio SET nome = ? , jogador_id = ? WHERE id = ?";

		try {
			state = con.prepareStatement(sql);
			state.setString(1, territorio.getNome());
			state.setInt   (2, jogador.getId());
			state.setInt   (3, territorio.getId());
			state.executeUpdate();
		} finally {
			ConnectionFactory.closeConnection(con, state);
		}
	}

	public List<Territorio> findAll(Jogador jogador) throws SQLException {
		PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM territorio WHERE territorio.jogadorId = ?";

        List<Territorio> lista = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, jogador.getId());
            rs = stmt.executeQuery();
            
            while (rs.next()) {
            	Territorio territorio = Territorio.getTerritorioPeloNome(ControladorDeJogo.tabuleiro.getListaTerritorios(), rs.getString("nome"));
                territorio.setId(rs.getInt("id"));

                lista.add(territorio);
            }

        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return lista;
	}
	
}
