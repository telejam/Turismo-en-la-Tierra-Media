package persistence;

import java.sql.SQLException;
import java.util.List;

import model.BasePromotion;
import persistence.commons.GenericDAO;

public interface PromotionDAO extends GenericDAO<BasePromotion> {

	public abstract List<Integer> findIdsIncluded(int id) throws SQLException;

	public abstract List<Integer> findIdsFree(int id) throws SQLException;

	public abstract int getLastId();

	public int insertIdIncluded(int promotionId, int attractionId) throws SQLException;
	
	public int insertIdFree(int promotionId, int attractionId) throws SQLException;

	public int deleteIncludedById(Integer id) throws SQLException;

	public int deleteFreeById(Integer id) throws SQLException;
}
