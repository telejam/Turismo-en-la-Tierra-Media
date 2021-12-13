package persistence.commons;

import persistence.*;
import persistence.UserDAO;
import persistence.impl.*;


public class DAOFactory {

	public static UserDAO getUserDAO() {
		return new UserDAOImpl();
	}
	
	public static AttractionDAO getAttractionDAO() {
		return new AttractionDAOImpl();
	}
	/*
	public static PromotionDAO getPromotionDAO() {
		return new PromotionDAOImpl();
	}
	*/
}
