package persistence.impl;


import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import model.Attraction;
import persistence.commons.ConnectionProvider;

public class ItineraryDAOImpl {


	public int insert(int id_offer, String type_offer, int id_user) throws SQLException {
		Connection connection = ConnectionProvider.getConnection();
		String sql = "INSERT INTO itinerary  (id_offer, type_offer,  id_user) VALUES (?, ?, ?)";
		PreparedStatement itinerario = connection.prepareStatement(sql);
		itinerario.setInt(1, id_offer);
		itinerario.setString(2, type_offer);
		itinerario.setInt(3, id_user);

		int rows = itinerario.executeUpdate();
		
		return rows;
	}

	public ArrayList<Attraction> findByIdUsuario(int id_user) throws SQLException {
		Connection connection = ConnectionProvider.getConnection();
		String sql = "SELECT * FROM itinerary  WHERE id_user = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id_user);

		ResultSet result = statement.executeQuery();
		
		ArrayList<Attraction> atracciones = new ArrayList<Attraction>();

		while (result.next()) {
			attractions.add(toAttraction(result));
		}

		return atracciones;
	}

	private Attraction toAttraction(ResultSet result) throws SQLException {

	    int id = result.getInt("id");
		String name = result.getString("name");
		double cost = result.getDouble("cost");
		double duration = result.getDouble("duration");
		int capacity = result.getInt("capacity");




		return new Attraction(id, name, cost, duration, capacity);
	}
}