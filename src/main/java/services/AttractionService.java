package services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Attraction;
import model.BasePromotion;
import model.Promotion;
import persistence.AttractionDAO;
import persistence.PromotionDAO;
import persistence.commons.DAOFactory;

public class AttractionService {

	public List<Attraction> list() {
		return DAOFactory.getAttractionDAO().findAll();
	}

	public Attraction create(String name, Double cost, Double duration, Integer capacity) {

		Attraction attraction = new Attraction(name, cost, duration, capacity);

		if (attraction.isValid()) {
			AttractionDAO attractionDAO = DAOFactory.getAttractionDAO();
			attractionDAO.insert(attraction);
			
		}

		return attraction;
	}

	public Attraction update(Integer id, String name, Double cost, Double duration, Integer capacity) {

		AttractionDAO attractionDAO = DAOFactory.getAttractionDAO();
		Attraction attraction = attractionDAO.find(id);

		attraction.setName(name);
		attraction.setCost(cost);
		attraction.setDuration(duration);
		attraction.setCapacity(capacity);

		if (attraction.isValid()) {
			attractionDAO.update(attraction);
			
		}

		return attraction;
	}

	public void delete(Integer id) throws SQLException {
		Attraction attraction = new Attraction(id, null, null, null, null);
		PromotionService promotionService = new PromotionService();
		List<Promotion> promotions = new ArrayList<Promotion>();
		List<Attraction> attractionsIncluded = new ArrayList<Attraction>();
		
		AttractionDAO attractionDAO = DAOFactory.getAttractionDAO();
			
		promotions =  promotionService.list();
		
		for (Promotion promotion : promotions) {
			attractionsIncluded = promotion.getContent();
			for (Attraction attractionIncluded : attractionsIncluded) {
				if(id == attractionIncluded.getId()) {
					promotionService.delete(promotion.getId());
				}
			}
		}
		
		attractionDAO.delete(attraction);
		
		
	}

	public Attraction find(Integer id) {
		return DAOFactory.getAttractionDAO().find(id);
	}

}