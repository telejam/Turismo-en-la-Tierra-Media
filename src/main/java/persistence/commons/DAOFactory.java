package persistence.commons;

import persistence.AttractionDAO;
import persistence.BasePromotionDAO;
import persistence.UserDAO;
import persistence.impl.AttractionDAOImpl;
import persistence.impl.BasePromotionDAOImpl;
import persistence.impl.UserDAOImpl;

public class DAOFactory {

	public static UserDAO getUserDAO() {
		return new UserDAOImpl();
	}
	
	public static AttractionDAO getAttractionDAO() {
		return new AttractionDAOImpl();
	}
	
	public static BasePromotionDAO getPromotionDAO() {
		return new BasePromotionDAOImpl();
	}
}
