package services;

import java.sql.SQLException;
import java.util.*;

import model.*;
import persistence.commons.DAOFactory;

public class ItineraryService {

	
	public List<Offer> list() throws SQLException {
		return DAOFactory.getItineraryDAO().findAll();
	}
	
	
	public void insert (Offer offer, User user) throws SQLException {
		String type_offer;

		if (offer.getContent().size()>1) {
			type_offer = "P";
		}  else { 
			type_offer = "A";
		}
		DAOFactory.getItineraryDAO().insert(offer.getId(), type_offer, user.getId());

	}


	public List<Offer> findByIdUser(Integer id) {
		return DAOFactory.getItineraryDAO().findByIdUser(id);
	}
}

