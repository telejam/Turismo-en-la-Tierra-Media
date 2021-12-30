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

	public BasePromotion findBase(Integer id) throws SQLException {
		return DAOFactory.getPromotionDAO().find(id);
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
		
		if (basePromotion.getType().equals("Porcentual")) {
			
			promotion = new PorcentualPromotion(
					basePromotion.getId(),
					basePromotion.getName(),
					basePromotion.getDescription(), 
					includedAttractions, 
					basePromotion.getValue()
					);
			
		} else if (basePromotion.getType().equals("Absoluta")) {
			
			promotion = new AbsolutePromotion(
					basePromotion.getId(),
					basePromotion.getName(),
					basePromotion.getDescription(), 
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
					basePromotion.getDescription(), 
					includedAttractions, 
					paidForAttractions
					);
			
		}
		
		return promotion;
	}
	

	public BasePromotion create(String name, String description, String type, Double value, String[] included, String[] free) throws SQLException {
		
		BasePromotion promotion = new BasePromotion(-1, name, description, type, value, 0); 
		DAOFactory.getPromotionDAO().insert(promotion);
		int promotionId = DAOFactory.getPromotionDAO().getLastId();

		for (int i = 0; i < included.length; i++) {
			int attractionId = Integer.parseInt(included[i]);
			DAOFactory.getPromotionDAO().insertIdIncluded(promotionId, attractionId);
		} 

		if (type.equals("AxB")) {
			for (int i = 0; i < free.length; i++) {
				int attractionId = Integer.parseInt(free[i]);
				DAOFactory.getPromotionDAO().insertIdFree(promotionId, attractionId);
			} 
		}
		
		return promotion;
	}

	public BasePromotion update(Integer id, String name, String description, String type, Double value, String[] included, String[] free) throws SQLException {
		
		BasePromotion promotion = new BasePromotion(id, name, description, type, value, 0); 
		DAOFactory.getPromotionDAO().update(promotion);
		DAOFactory.getPromotionDAO().deleteIncludedById(id);
		DAOFactory.getPromotionDAO().deleteFreeById(id);
		
		for (int i = 0; i < included.length; i++) {
			int attractionId = Integer.parseInt(included[i]);
			DAOFactory.getPromotionDAO().insertIdIncluded(id, attractionId);
		} 
		
		if (type.equals("AxB")) {
			for (int i = 0; i < free.length; i++) {
				int attractionId = Integer.parseInt(free[i]);
				DAOFactory.getPromotionDAO().insertIdFree(id, attractionId);
			} 
		}
		
		return promotion;
	}
	

	public void delete(Integer id) {
		BasePromotion promotion = new BasePromotion(id, "", "", "", 0, 0);
		DAOFactory.getPromotionDAO().delete(promotion);
	}

	public List<Integer> findIdsIncluded(int id) throws SQLException {
		return DAOFactory.getPromotionDAO().findIdsIncluded(id);
	} 
	
	public List<Integer> findIdsFree(int id) throws SQLException {
		return DAOFactory.getPromotionDAO().findIdsFree(id);
	} 
	
}
