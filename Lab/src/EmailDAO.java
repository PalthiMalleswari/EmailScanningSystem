// package com.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmailDAO {
    public void addEmail(int userId, String content, double suspicionPercentage) {
        String query = "INSERT INTO emails (user_id, content, suspicion_percentage) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setString(2, content);
            stmt.setDouble(3, suspicionPercentage);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Email> getEmails() {
        List<Email> emails = new ArrayList<>();
        String query = "SELECT * FROM emails";
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Email email = new Email(rs.getInt("id"), rs.getInt("user_id"), rs.getString("content"), rs.getDouble("suspicion_percentage"), rs.getTimestamp("scanned_at"));
                emails.add(email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emails;
    }

    public void updateEmail(int id, String content, double suspicionPercentage) {
        String query = "UPDATE emails SET content = ?, suspicion_percentage = ? WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, content);
            stmt.setDouble(2, suspicionPercentage);
            stmt.setInt(3, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEmail(int id) {
        String query = "DELETE FROM emails WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
