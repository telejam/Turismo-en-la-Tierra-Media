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

		for (BasePromotion promotion : basePromotions) {
			promotions.add(find(promotion.getId()));
		}

		return promotions;
	}

	public Promotion find(int id) throws SQLException {
		List<Integer> idsIncludedAttractions;
		List<Integer> idsFreeAttractions;
		List<Attraction> includedAttractions;
		List<Attraction> paidForAttractions;
		List<Attraction> freeAttractions;
		Promotion promotion;
		BasePromotion basePromotion = DAOFactory.getPromotionDAO().find(id);
		
		idsIncludedAttractions = DAOFactory.getPromotionDAO().findIdsIncluded(id);
		
		includedAttractions = new ArrayList<Attraction>();
		for (int idAttraction : idsIncludedAttractions) {
			Attraction attraction = DAOFactory.getAttractionDAO().find(idAttraction); 
			includedAttractions.add(attraction);
		}
		
		if (basePromotion.getType().equals("%")) {
			
			promotion = new PorcentualPromotion(
					basePromotion.getId(),
					basePromotion.getName(),
					includedAttractions, 
					basePromotion.getValue()
					);
			
		} else if (basePromotion.getType().equals("$")) {
			
			promotion = new AbsolutePromotion(
					basePromotion.getId(),
					basePromotion.getName(),
					includedAttractions, 
					basePromotion.getValue()
					);
			
		} else { 
			
			paidForAttractions = new ArrayList<Attraction>();
			paidForAttractions.addAll(includedAttractions);
			
			idsFreeAttractions = DAOFactory.getPromotionDAO().findIdsFree(basePromotion.getId());
			
			freeAttractions = new ArrayList<Attraction>();
			for (int idAttraction : idsFreeAttractions) {
				Attraction attraction = DAOFactory.getAttractionDAO().find(idAttraction); 
				freeAttractions.add(attraction);
			}
			
			includedAttractions.addAll(freeAttractions);
			
			
			promotion = new AxBPromotion(
					basePromotion.getId(),
					basePromotion.getName(),
					includedAttractions, 
					paidForAttractions
					);
			
		}
		
		return promotion;
	}
	
//	public Promotion create(String name, Double cost, Double duration, Integer capacity) {
//
//		Promotion promotion = new Promotion(name, cost, duration, capacity);
//
//		if (promotion.isValid()) {
//			PromotionDAO promotionDAO = DAOFactory.getPromotionDAO();
//			promotionDAO.insert(promotion);
//			// XXX: si no devuelve "1", es que hubo más errores
//		}
//
//		return promotion;
//	}
//
//	public Promotion update(Integer id, String name, Integer cost, Double duration, Integer capacity) {
//
//		PromotionDAO promotionDAO = DAOFactory.getPromotionDAO();
//		Promotion promotion = promotionDAO.find(id);
//
//		promotion.setName(name);
//		promotion.setCost(cost);
//		promotion.setDuration(duration);
//		promotion.setCapacity(capacity);
//
//		if (promotion.isValid()) {
//			promotionDAO.update(promotion);
//			// XXX: si no devuelve "1", es que hubo más errores
//		}
//
//		return promotion;
//	}
//
//	public void delete(Integer id) {
//		Promotion promotion = new Promotion(id, null, null, null, null);
//
//		PromotionDAO promotionDAO = DAOFactory.getPromotionDAO();
//		promotionDAO.delete(promotion);
//	}
//
//	public Promotion find(Integer id) {
//		return DAOFactory.getPromotionDAO().find(id);
//	}

}
