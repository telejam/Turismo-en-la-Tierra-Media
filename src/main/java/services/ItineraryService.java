package services;

import java.sql.SQLException;
import java.util.*;

import model.*;
import persistence.commons.DAOFactory;

public class ItineraryService {

	
	public List<Offer> list() throws SQLException {
		List<Offer> offers = DAOFactory.getItineraryDAO().findAll();

		return offers;
	}

}
