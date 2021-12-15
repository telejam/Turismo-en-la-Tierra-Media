package model;


import java.sql.*;
import java.util.*;

import persistence.commons.DAOFactory;

public class Itinerary {

	private List<Offer> offers;
	private Double time = 0.0;
	private Double coins = 0.0;

	public Itinerary(int id) {
		this.offers = DAOFactory.getItineraryDAO().findByIdUser(id);
		for (Offer offer : offers) {
		    this.time += offer.getDuration();
		    this.coins +=offer.getCost();
		}
	}

	public boolean isLoaded (Offer offer) {
		boolean loaded = false;
		for (Offer item : offers) {
			if (item.equals(offer) || item.getContent().contains(offer)) {
				loaded = true;
			}
		}
		return loaded;
	}

	public void add(Offer offer) throws SQLException {
		offers.add(offer);
	    this.time += offer.getDuration();
	    this.coins +=offer.getCost();
	}
}

