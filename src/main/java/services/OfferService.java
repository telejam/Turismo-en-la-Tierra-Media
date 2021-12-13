package services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Offer;
import persistence.commons.DAOFactory;

public class OfferService {

	public List<Offer> list() {
		List<Offer> offers = new ArrayList<Offer>();
		offers.addAll(DAOFactory.getAttractionDAO().findAll());
		offers.addAll(DAOFactory.getAttractionDAO().findAll());
    	Collections.sort(offers, new OffersComparator());

		return offers;
	}

}
