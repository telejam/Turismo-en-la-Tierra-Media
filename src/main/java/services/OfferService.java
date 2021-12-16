package services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Offer;
import model.User;
import persistence.commons.DAOFactory;

public class OfferService {
	PromotionService promotionService = new PromotionService();

	public List<Offer> list(User user) throws SQLException {
		List<Offer> offers = new ArrayList<Offer>();
		List<Offer> offersNotBuyed = new ArrayList<Offer>();
		
		offers.addAll(promotionService.list());
		offers.addAll(DAOFactory.getAttractionDAO().findAll());
    	
		for (Offer offer : offers) {
			if (!user.isLoaded(offer)) {
				offersNotBuyed.add(offer);
			}
		}
		
		Collections.sort(offersNotBuyed, new OffersComparator());

		return offersNotBuyed;
	}

}
