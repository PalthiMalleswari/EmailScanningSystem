
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class KeywordDAO {
    public void addKeyword(String keyword, double weight) {
        keyword = keyword.toLowerCase();
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

    public void updateKeyword(int id, String keyword, double weight) {
        keyword = keyword.toLowerCase();
        String query = "UPDATE keywords SET keyword = ?, weight = ? WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, keyword);
            stmt.setDouble(2, weight);
            stmt.setInt(3, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteKeyword(int id) {
        String query = "DELETE FROM keywords WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
