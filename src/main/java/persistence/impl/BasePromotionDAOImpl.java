package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.BasePromotion;
import persistence.BasePromotionDAO;
import persistence.commons.ConnectionProvider;
import persistence.commons.MissingDataException;

public class BasePromotionDAOImpl implements BasePromotionDAO {

	@Override
	public List<BasePromotion> findAll() {
		try {
			String sql = "SELECT * FROM PROMOTIONS";
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
			String sql = "SELECT * FROM PROMOTIONS WHERE id = ?";
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
	
	private BasePromotion toPromotion(ResultSet promotionRegister) throws SQLException {
		return new BasePromotion(promotionRegister.getInt(1), promotionRegister.getString(2),
				promotionRegister.getString(3), promotionRegister.getDouble(4), promotionRegister.getInt(5));
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
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int delete(BasePromotion promotion) {
		try {
			String sql = "DELETE FROM PROMOTIONS WHERE ID = ?";
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
			String sql = "SELECT COUNT(1) AS TOTAL FROM PROMOTIONS";
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
		String sql = "SELECT * FROM INCLUDED_ATRACTIONS WHERE PROMOTION_ID = ?";
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


}
