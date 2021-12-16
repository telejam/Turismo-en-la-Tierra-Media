package services;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import model.Attraction;
import model.User;
import persistence.AttractionDAO;
import persistence.UserDAO;
import persistence.commons.DAOFactory;

public class BuyAttractionService {

	ItineraryService itineraryService = new ItineraryService();
	AttractionDAO attractionDAO = DAOFactory.getAttractionDAO();
	UserDAO userDAO = DAOFactory.getUserDAO();

	public Map<String, String> buy(Integer userId, Integer attractionId) throws SQLException {
		Map<String, String> errors = new HashMap<String, String>();

		User user = userDAO.find(userId);
		Attraction attraction = attractionDAO.find(attractionId);

		if (!attraction.canHost(1)) {
			errors.put("attraction", "No hay cupo disponible");
		}
		if (!user.canAfford(attraction)) {
			errors.put("user", "No tienes dinero suficiente");
		}
		if (!user.canAttend(attraction)) {
			errors.put("user", "No tienes tiempo suficiente");
		}

		if (errors.isEmpty()) {
			user.addToItinerary(attraction);
			attraction.host(1);

			itineraryService.insert(attraction, user);
			attractionDAO.update(attraction);
			userDAO.update(user);
		}

		return errors;

	}

}