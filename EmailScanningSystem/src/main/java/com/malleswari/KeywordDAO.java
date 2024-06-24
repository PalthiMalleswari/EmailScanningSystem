package com.malleswari;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class KeywordDAO {
	 public void addKeyword(String keyword, double weight) {
	        String query = "INSERT INTO keywords (keyword, weight) VALUES (?, ?)";
	        try (Connection conn = DatabaseUtil.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {
	            stmt.setString(1, keyword);
	            stmt.setDouble(2, weight);
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public ArrayList<Keyword> getKeywords() {
	        ArrayList<Keyword> keywords = new ArrayList<>();
	        String query = "SELECT * FROM keywords";
	        try (Connection conn = DatabaseUtil.getConnection();
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(query)) {
	            while (rs.next()) {
	                Keyword k = new Keyword(rs.getInt("id"), rs.getString("keyword"), rs.getDouble("weight"));
	                keywords.add(k);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return keywords;
	    }

//	    public Map<String, Double> getKeywordWeights() {
//	        Map<String, Double> keywordWeights = new HashMap<>();
//	        String query = "SELECT keyword, weight FROM keywords";
//	        try (Connection conn = DatabaseUtil.getConnection();
//	             Statement stmt = conn.createStatement();
//	             ResultSet rs = stmt.executeQuery(query)) {
//	            while (rs.next()) {
//	                keywordWeights.put(rs.getString("keyword"), rs.getDouble("weight"));
//	            }
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        }
//	        return keywordWeights;
//	    }

	    public void updateKeyword(String keyword, double weight) {
	        String query = "UPDATE keywords SET weight = ? WHERE keyword = ?";
	        try (Connection conn = DatabaseUtil.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {
	            stmt.setDouble(1, weight);
	            stmt.setString(2, keyword);
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public void deleteKeyword(String keyword) {
	        String query = "DELETE FROM keywords WHERE keyword = ?";
	        try (Connection conn = DatabaseUtil.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {
	            stmt.setString(1, keyword);
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public Map<String, Double> getKeywordWeights() {
	        Map<String, Double> keywordWeights = new HashMap<>();
	        String query = "SELECT keyword, weight FROM keywords";
	        try (Connection conn = DatabaseUtil.getConnection();
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(query)) {
	            while (rs.next()) {
	                keywordWeights.put(rs.getString("keyword"), rs.getDouble("weight"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return keywordWeights;
	    }
}
