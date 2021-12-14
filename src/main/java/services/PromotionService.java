package services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.AbsolutePromotion;
import model.Attraction;
import model.AxBPromotion;
import model.BasePromotion;
import model.PorcentualPromotion;
import model.Promotion;
import persistence.commons.DAOFactory;

public class PromotionService {

	public List<Promotion> list() throws SQLException {
		List<Promotion> promotions = new ArrayList<Promotion>();
		List<BasePromotion> basePromotions = DAOFactory.getPromotionDAO().findAll();

		List<Integer> idsIncludedAttractions;
		List<Integer> idsFreeAttractions;
		List<Attraction> includedAttractions;
		List<Attraction> paidForAttractions;
		List<Attraction> freeAttractions;

		for (BasePromotion promotion : basePromotions) {

			idsIncludedAttractions = DAOFactory.getPromotionDAO().findIdsIncluded(promotion.getId());
			
			includedAttractions = new ArrayList<Attraction>();
			for (int id : idsIncludedAttractions) {
				Attraction attraction = DAOFactory.getAttractionDAO().find(id); 
				includedAttractions.add(attraction);
			}
			
			if (promotion.getType().equals("%")) {

				promotions.add(new PorcentualPromotion(
						promotion.getId(),
						promotion.getName(),
						includedAttractions, 
						promotion.getValue()
				));

			} else if (promotion.getType().equals("$")) {

				promotions.add(new AbsolutePromotion(
					promotion.getId(),
					promotion.getName(),
					includedAttractions, 
					promotion.getValue()
				));

			} else { 

				paidForAttractions = new ArrayList<Attraction>();
				paidForAttractions.addAll(includedAttractions);
				
				idsFreeAttractions = DAOFactory.getPromotionDAO().findIdsFree(promotion.getId());
				
				freeAttractions = new ArrayList<Attraction>();
				for (int id : idsFreeAttractions) {
					Attraction attraction = DAOFactory.getAttractionDAO().find(id); 
					freeAttractions.add(attraction);
				}

				includedAttractions.addAll(freeAttractions);

				
				promotions.add(new AxBPromotion(
						promotion.getId(),
						promotion.getName(),
						includedAttractions, 
						paidForAttractions
				));

			}

		}

		return promotions;
	}

	public Promotion create(String name, Double cost, Double duration, Integer capacity) {

		Promotion promotion = new Promotion(name, cost, duration, capacity);

		if (promotion.isValid()) {
			PromotionDAO promotionDAO = DAOFactory.getPromotionDAO();
			promotionDAO.insert(promotion);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return promotion;
	}

	public Promotion update(Integer id, String name, Integer cost, Double duration, Integer capacity) {

		PromotionDAO promotionDAO = DAOFactory.getPromotionDAO();
		Promotion promotion = promotionDAO.find(id);

		promotion.setName(name);
		promotion.setCost(cost);
		promotion.setDuration(duration);
		promotion.setCapacity(capacity);

		if (promotion.isValid()) {
			promotionDAO.update(promotion);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return promotion;
	}

	public void delete(Integer id) {
		Promotion promotion = new Promotion(id, null, null, null, null);

		PromotionDAO promotionDAO = DAOFactory.getPromotionDAO();
		promotionDAO.delete(promotion);
	}

	public Promotion find(Integer id) {
		return DAOFactory.getPromotionDAO().find(id);
	}

}
