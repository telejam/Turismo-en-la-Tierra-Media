package services;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import persistence.AttractionDAO;
import persistence.UserDAO;
import persistence.commons.DAOFactory;

public class BuyOfferService {
	
	ItineraryService itineraryService = new ItineraryService();
	AttractionDAO attractionDAO = DAOFactory.getAttractionDAO();
	UserDAO userDAO = DAOFactory.getUserDAO();

	public Map<String, String> buy(Integer userId, Integer offerId, Integer offerType) {

		Map<String, String> errors = new HashMap<String, String>();

		if (offerType == 1) { 
			BuyAttractionService buyAttractionService = new BuyAttractionService();
			try {
				errors = buyAttractionService.buy(userId, offerId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			BuyPromotionService buyPromotionService = new BuyPromotionService();
			errors = buyPromotionService.buy(userId, offerId);
		}
		
		return errors;

	}

}