package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Attraction;
import persistence.AttractionDAO;
import persistence.commons.ConnectionProvider;
import persistence.commons.MissingDataException;

public class AttractionDAOImpl implements AttractionDAO {

	@Override
	public List<Attraction> findAll() {
		try {
			String sql = "SELECT * FROM ATTRACTIONS WHERE DISABLED = 0";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet results = statement.executeQuery();

			List<Attraction> attractions = new LinkedList<Attraction>();
			while (results.next()) {
				attractions.add(toAttraction(results));
			}

			return attractions;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Attraction find (Integer id){
		Connection conn = null;
		Attraction attraction = null; 
		try {
			String sql = "SELECT * FROM ATTRACTIONS WHERE DISABLED = 0 AND ID = ?";
			conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();

			if (result.next()) {
				attraction = toAttraction(result);
			}
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
		return attraction;
	}
	
	
	
	private Attraction toAttraction(ResultSet result) throws SQLException {
		return new Attraction
				(
						result.getInt(1),
						result.getString(2),
						result.getDouble(3),
						result.getDouble(4),
						result.getInt(5)
				);
	}
    
	
	@Override
	public int insert(Attraction attraction) {
		try {
			String sql = "INSERT INTO ATTRACTIONS (NAME, COST, DURATION, CAPACITY) VALUES (?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			int i = 1;
			statement.setString(i++, attraction.getName());
			statement.setDouble(i++, attraction.getCost());
			statement.setDouble(i++, attraction.getDuration());
			statement.setInt(i++, attraction.getCapacity());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(Attraction attraction) {
		try {
			String sql = "UPDATE ATTRACTIONS SET NAME = ?, COST = ?, DURATION = ?, CAPACITY = ? WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			int i = 1;
			statement.setString(i++, attraction.getName());
			statement.setDouble(i++, attraction.getCost());
			statement.setDouble(i++, attraction.getDuration());
			statement.setInt(i++, attraction.getCapacity());
		    statement.setInt(i++, attraction.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int delete(Attraction attraction) {
		try {
			String sql = "UPDATE ATTRACTIONS SET DISABLED = 1 WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, attraction.getId());
			
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}


	

	@Override
	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM ATTRACTIONS WHERE DISABLED = 0";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			resultados.next();
			int total = resultados.getInt("TOTAL");

			return total;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	



}
