package persistence.impl;


import java.sql.*;
import java.util.*;

import model.*;
import persistence.ItineraryDAO;
import persistence.commons.ConnectionProvider;
import persistence.commons.MissingDataException;
import services.*;

public class ItineraryDAOImpl implements ItineraryDAO  {


	public int insert(int id_offer, String type_offer, int id_user) {
		int rows = 0;
		try {
			Connection connection;

			connection = ConnectionProvider.getConnection();
			String sql = "INSERT INTO itinerary  (offer_id, offer_type,  user_id) VALUES (?, ?, ?)";
			PreparedStatement itinerary = connection.prepareStatement(sql);
			itinerary.setInt(1, id_offer);
			itinerary.setString(2, type_offer);
			itinerary.setInt(3, id_user);
			 rows = itinerary.executeUpdate();

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rows;


	}

	
	public List<Offer> findAll() {
		try {
			String sql = "SELECT * FROM ITINERARY";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet results = statement.executeQuery();

			List<Offer> offers = new LinkedList<Offer>();
			while (results.next()) {
				offers.add(toOffers(results));
			}

			return offers;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	public ArrayList<Offer> findByIdUser(int id_user) {
		ArrayList<Offer> offers = null;
		try {
			Connection connection;
			connection = ConnectionProvider.getConnection();
			String sql = "SELECT * FROM itinerary  WHERE id_user = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id_user);

			ResultSet result = statement.executeQuery();
			PromotionService promotionService = new PromotionService();
			
			//AttractionService attractionService = new AttractionService();
			
			 offers = new ArrayList<Offer>();

			while (result.next()) {
				if(result.getString("offer_type") == "P") {
					offers.add(promotionService.find(result.getInt("offer_id")));
				}else  {
					// offers.add(attractionService.find(result.getInt("offer_id")));				
				}			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return offers;
	}
/*
	private Attraction toAttraction(ResultSet result) throws SQLException {

	    int id = result.getInt("id");
		String name = result.getString("name");
		double cost = result.getDouble("cost");
		double duration = result.getDouble("duration");
		int capacity = result.getInt("capacity");




		return new Attraction(id, name, cost, duration, capacity);
	}
	*/
}