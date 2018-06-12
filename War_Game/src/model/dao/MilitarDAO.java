package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.bean.militares.Cabo;
import model.bean.militares.Militar;
import model.bean.militares.Sargento;
import model.bean.militares.Soldado;
import model.bean.militares.Tenente;
import model.bean.mundo.Territorio;
import connection.ConnectionFactory;

public class MilitarDAO {
	private Connection con = null;

	public MilitarDAO() throws Exception {
		this.con = ConnectionFactory.getConnection();
	}
	
	public int maxId() throws SQLException , Exception {
		PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT MAX(militar.id) as lastId FROM militar";

        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("lastId");
            }

        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        throw new Exception("Erro em MilitarDAO:maxId - Erro em MaxId");
	}

	public void createMilitar(Territorio territorio , Militar militar) throws SQLException {

		PreparedStatement state = null;
		String sql = "INSERT INTO militar ( tipo , territorio_id ) VALUES (?,?)";

		try {
			state = con.prepareStatement(sql);
			state.setString(1, militar.toString());
			state.setInt   (2, territorio.getId());
			state.executeUpdate();
		} finally {
			ConnectionFactory.closeConnection(con, state);
		}
	}

	public void updateMilitar(Territorio territorio , Militar militar) throws SQLException {

		PreparedStatement state = null;
		String sql = "UPDATE militar SET tipo = ? , territorio_id = ? WHERE id = ?";

		try {
			state = con.prepareStatement(sql);
			state.setString(1, militar.toString());
			state.setInt   (2, territorio.getId());
			state.setInt   (3, militar.getId());
			state.executeUpdate();
		} finally {
			ConnectionFactory.closeConnection(con, state);
		}
	}

	public List<Militar> findAll(Territorio territorio) throws Exception {
		
		PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM militar WHERE militar.territorioId = ?";

        List<Militar> lista = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, territorio.getId());
            rs = stmt.executeQuery();
            
            while (rs.next()) {
            	
            	if (rs.getString("tipo").equals("Cabo")) {
        			Cabo cabo = new Cabo();
        			cabo.setId(rs.getInt("id"));
        			
        			lista.add(cabo);
        		} else if (rs.getString("tipo").equals("Sargento")) {
        			Sargento sargento = new Sargento();
        			sargento.setId(rs.getInt("id"));
        			
        			lista.add(sargento);
        		} else if (rs.getString("tipo").equals("Soldado")) {
        			Soldado soldado = new Soldado();
        			soldado.setId(rs.getInt("id"));
        			
        			lista.add(soldado);
        		} else if (rs.getString("tipo").equals("Tenente")) {
        			Tenente tenente = new Tenente();
        			tenente.setId(rs.getInt("id"));
        			
        			lista.add(tenente);
        		} else {
        			throw new Exception("ERRO EM MilitarDAO:findAll - Militar nao encontrado");
        		}
            	
            }

        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return lista;
	}
}
