package services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Offer;

public class OfferService {
	AttractionService attractionService = new AttractionService();
	PromotionService promotionService = new PromotionService();

	public List<Offer> list() throws SQLException {
		List<Offer> offers = new ArrayList<Offer>();
		
		offers.addAll(promotionService.list());
		offers.addAll(attractionService.list());
    	
		Collections.sort(offers, new OffersComparator());

		return offers;
	}

}
