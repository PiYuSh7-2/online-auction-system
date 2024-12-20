package com.onlineauction.dao;

import com.onlineauction.model.Bid;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BidDAO {
    private final String url = "jdbc:mysql://localhost:3306/online_auction";
    private final String username = "root";
    private final String password = "9142480211";

    public boolean placeBid(Bid bid) {
        String query = "INSERT INTO bids (product_id, user_id, bid_amount, bid_time) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, bid.getProductId());
            stmt.setInt(2, bid.getUserId());
            stmt.setDouble(3, bid.getBidAmount());
            stmt.setTimestamp(4, new Timestamp(bid.getBidTime().getTime()));
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Bid> getBidsForProduct(int productId) {
        List<Bid> bids = new ArrayList<>();
        String query = "SELECT * FROM bids WHERE product_id = ? ORDER BY bid_amount DESC";
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                bids.add(new Bid(
                        rs.getInt("id"),
                        rs.getInt("product_id"),
                        rs.getInt("user_id"),
                        rs.getDouble("bid_amount"),
                        rs.getTimestamp("bid_time")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bids;
    }
}
