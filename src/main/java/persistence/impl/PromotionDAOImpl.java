package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.BasePromotion;
import persistence.PromotionDAO;
import persistence.commons.ConnectionProvider;
import persistence.commons.MissingDataException;

public class PromotionDAOImpl implements PromotionDAO {

	@Override
	public List<BasePromotion> findAll() {
		try {
			String sql = "SELECT * FROM PROMOTIONS WHERE DISABLED = 0";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet results = statement.executeQuery();

			List<BasePromotion> promotions = new LinkedList<BasePromotion>();
			while (results.next()) {
				promotions.add(toPromotion(results));
			}

			return promotions;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public BasePromotion find(Integer id) {
		try {
			String sql = "SELECT * FROM PROMOTIONS WHERE DISABLED = 0 AND ID = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			
			ResultSet results = statement.executeQuery();

			BasePromotion promotion = null;
			if (results.next()) {
				promotion = toPromotion(results);
			}

			return promotion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	@Override
	public int getLastId() {
		try {
			String sql = "SELECT MAX(ID) FROM PROMOTIONS";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			
			ResultSet result = statement.executeQuery();
			
			int id = 0;
			if (result.next()) {
				id = result.getInt(1);
			}
			
			return id;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	private BasePromotion toPromotion(ResultSet result) throws SQLException {
		return new BasePromotion
				(
					result.getInt(1), 
					result.getString(2),				
					result.getString(3), 
					result.getDouble(4), 
					result.getInt(5)
				);
	}

	@Override
	public int insert(BasePromotion promotion) {
		try {
			String sql = "INSERT INTO PROMOTIONS (NAME, TYPE, COST) VALUES (?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			int i = 1;
			statement.setString(i++, promotion.getName());
			statement.setString(i++, promotion.getType());
			statement.setDouble(i++, promotion.getValue());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(BasePromotion promotion) {
		try {
			String sql = "UPDATE PROMOTIONS SET NAME = ?, TYPE = ?, COST = ? WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			int i = 1;
			statement.setString(i++, promotion.getName());
			statement.setString(i++, promotion.getType());
			statement.setDouble(i++, promotion.getValue());
			statement.setInt(i++, promotion.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int delete(BasePromotion promotion) {
		try {
			String sql = "UPDATE PROMOTIONS SET DISABLED = 1 WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, promotion.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM PROMOTIONS WHERE DISABLED = 0";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet results = statement.executeQuery();

			results.next();
			int total = results.getInt("TOTAL");

			return total;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public List<Integer> findIdsIncluded(int id) throws SQLException {
		String sql = "SELECT * FROM INCLUDED_ATTRACTIONS WHERE PROMOTION_ID = ?";
		Connection connection = ConnectionProvider.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id); 
		ResultSet results = statement.executeQuery();

		List<Integer> idsIncluded = new ArrayList<Integer>();
		
		while (results.next()) {
			idsIncluded.add(results.getInt("ATTRACTION_ID"));
		}
		return idsIncluded;
		
	}
	
	@Override
	public List<Integer> findIdsFree(int id) throws SQLException {
		String sql = "SELECT * FROM FREE_ATTRACTIONS WHERE PROMOTION_ID = ?";
		Connection connection = ConnectionProvider.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id); 
		ResultSet results = statement.executeQuery();
		
		List<Integer> idsIncluded = new ArrayList<Integer>();
		
		while (results.next()) {
			idsIncluded.add(results.getInt("ATTRACTION_ID"));
		}
		return idsIncluded;
		
	}

	@Override
	public int insertIdIncluded(int promotionId, int attractionId) throws SQLException {
		
		String sql = "INSERT INTO INCLUDED_ATTRACTIONS (PROMOTION_ID, ATTRACTION_ID) VALUES (?, ?)";
		Connection connection = ConnectionProvider.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, promotionId); 
		statement.setInt(2, attractionId); 
		
		int rows = statement.executeUpdate();
		
		return rows;
		
	}
	
	@Override
	public int insertIdFree(int promotionId, int attractionId) throws SQLException {
		
		String sql = "INSERT INTO FREE_ATTRACTIONS (PROMOTION_ID, ATTRACTION_ID) VALUES (?, ?)";
		Connection connection = ConnectionProvider.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, promotionId); 
		statement.setInt(2, attractionId); 
		
		int rows = statement.executeUpdate();
		
		return rows;
		
	}

	@Override
	public int deleteIncludedById(Integer id) throws SQLException {
		
		String sql = "DELETE FROM INCLUDED_ATTRACTIONS WHERE PROMOTION_ID = ?";
		Connection connection = ConnectionProvider.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id); 
		
		int rows = statement.executeUpdate();
		
		return rows;
		
	}
	
	@Override
	public int deleteFreeById(Integer id) throws SQLException {
		
		String sql = "DELETE FROM FREE_ATTRACTIONS WHERE PROMOTION_ID = ?";
		Connection connection = ConnectionProvider.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id); 
		
		int rows = statement.executeUpdate();
		
		return rows;
		
	}
	


}
