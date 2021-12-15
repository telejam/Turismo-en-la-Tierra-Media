package persistence.impl;


import java.sql.*;
import java.util.*;

import model.*;
import persistence.ItineraryDAO;
import persistence.commons.ConnectionProvider;
import persistence.commons.MissingDataException;
import services.*;

public class ItineraryDAOImpl implements ItineraryDAO  {


	@Override
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

	
	@Override
	public List<Offer> findAll() {
		List<Offer> offers = new LinkedList<Offer>();
		Connection connection;
		String sql = "SELECT * FROM ITINERARY";
		try {
			connection =  ConnectionProvider.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();

			while (results.next()) {
				offers.add(toOffer(results));
			}

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
		return offers;
	}
	
	@Override
	public List<Offer> findByIdUser(int id_user) {
		List<Offer> offers = new LinkedList<Offer>();
		Connection connection;
		String sql = "SELECT * FROM itinerary  WHERE id_user = ?";
		try {
			connection = ConnectionProvider.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id_user);

			ResultSet results = statement.executeQuery();

			while (results.next()) {
				offers.add(toOffer(results));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return offers;
	}

	private Offer toOffer(ResultSet results) {
		PromotionService promotionService = new PromotionService();
		AttractionService attractionService = new AttractionService();
		Offer offer = null; 
		try {
			if(results.getString("offer_type") == "P") {
				offer = promotionService.find(results.getInt("offer_id"));
			}else  {
				offer = attractionService.find(results.getInt("offer_id"));				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		return offer;
	}
	
}