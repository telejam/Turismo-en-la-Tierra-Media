package services;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import model.Attraction;
import model.Promotion;
import model.User;
import persistence.UserDAO;
import persistence.commons.DAOFactory;

public class BuyPromotionService {

	private ItineraryService itineraryService = new ItineraryService();
	private PromotionService promotionService = new PromotionService();
	private UserDAO userDAO = DAOFactory.getUserDAO();

	public Map<String, String> buy(Integer userId, Integer promotionId) {
		Map<String, String> errors = new HashMap<String, String>();

		User user = userDAO.find(userId);
		Promotion promotion = null;
		try {
			promotion = promotionService.find(promotionId);

			if (!promotion.canHost(1)) {
				errors.put("promotion", "No hay cupo disponible");
			}
			if (!user.canAfford(promotion)) {
				errors.put("user", "No tienes dinero suficiente");
			}
			if (!user.canAttend(promotion)) {
				errors.put("user", "No tienes tiempo suficiente");
			}

			if (errors.isEmpty()) {
				user.addToItinerary(promotion);
				promotion.host(1);
				
				itineraryService.insert(promotion, user);
				for (Attraction attraction : promotion.getContent()) {
					DAOFactory.getAttractionDAO().update(attraction);
				}
				userDAO.update(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return errors;
	}

}
