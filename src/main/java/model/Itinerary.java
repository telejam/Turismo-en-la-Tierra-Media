package model;


import java.sql.*;
import java.text.*;
import java.util.*;


public class Itinerary {


	private ArrayList<Offer> offers = new ArrayList<Offer>();
	private User user;
	private double time = 0;
	private double coins = 0;

	public Itinerary(User user) throws SQLException {
		this.user = user;
		itinerary = DAOFactory.getItinerarioDAO().findByIdUser(user.getId());
	}




	public boolean isLoaded (Offer offer) {
		boolean loaded = false;
		for (Offer offer : offer.getContent()) {
			if (offer.contains(offer)) {
				loaded = true;
			}
		}
		return loaded;
	}

 
	

	
/*
	public void add(Offer offer) throws SQLException {
		String tipo;

		if (offer.getContent().size()>1) {
			tipo = "P";
		}  else { 
			tipo = "A";
		}
		DAOFactory.getItinerarioDAO().insert(offer.getId(), tipo, user.getId());

		for (Attraction attraction : offer.getContent()) {
			attractiones.add(attraction);
	    }

	    this.time += offer.getDuration();
	    this.coins +=offer.getCost();

		
	}
   */
}

